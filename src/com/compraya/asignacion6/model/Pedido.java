package com.compraya.asignacion6.model;

import com.compraya.asignacion6.payment.ProcesoPago;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDateTime fecha;
    private String estado;
    private List<ItemPedido> items;
    private double subtotal;
    private double impuestos;
    private double total;
    private DireccionEnvio direccionEnvio;
    private String metodoPagoUsado;

    public static final double TASA_IVA = 0.19;

    public Pedido(int id, Cliente cliente, DireccionEnvio direccionEnvio) {
        this.id = id;
        this.cliente = cliente;
        this.direccionEnvio = direccionEnvio;
        this.items = new ArrayList<>();
        this.estado = "PENDIENTE";
        this.fecha = LocalDateTime.now();
    }

    public static Pedido crearDesdeCarrito(int id, Cliente cliente, DireccionEnvio direccionEnvio) {
        Carrito carrito = cliente.getCarrito();
        if (carrito == null || carrito.getItems().isEmpty()) {
            return null;
        }

        Pedido pedido = new Pedido(id, cliente, direccionEnvio);
        double subtotalAcumulado = 0.0;

        for (ItemCarrito itemCart : carrito.getItems()) {
            Item item = itemCart.getItem();
            int cant = itemCart.getCantidad();
            item.actualizarStock(-cant);
            ItemPedido itemPed = new ItemPedido(item, cant);
            pedido.items.add(itemPed);
            subtotalAcumulado += itemPed.getSubtotal();
        }

        pedido.subtotal = subtotalAcumulado;
        pedido.impuestos = subtotalAcumulado * TASA_IVA;
        pedido.total = pedido.subtotal + pedido.impuestos;

        carrito.vaciarCarrito();
        return pedido;
    }

    /**
     * Procesa y confirma el pago utilizando la INTERFAZ ProcesoPago.
     */
    public boolean procesarYConfirmarPago(ProcesoPago pasarela) {
        if (pasarela == null) return false;

        this.metodoPagoUsado = pasarela.getNombreMetodo();
        String ref = "PED-" + id;
        String txnId = "TXN-" + System.currentTimeMillis();

        boolean iniciada = pasarela.iniciarPago(this.total, ref);
        if (iniciada) {
            boolean verificada = pasarela.verificarPago(txnId);
            if (verificada) {
                boolean confirmada = pasarela.confirmarPago(txnId);
                if (confirmada) {
                    this.estado = "PAGADO";
                    System.out.printf("[PEDIDO #%d] ¡Pago completado mediante %s! Total: $%.2f\n",
                            id, pasarela.getNombreMetodo(), total);
                    return true;
                }
            }
        }
        this.estado = "RECHAZADO";
        return false;
    }

    public int getId() { return id; }
    public String getEstado() { return estado; }
    public double getTotal() { return total; }
    public List<ItemPedido> getItems() { return Collections.unmodifiableList(items); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("==========================================")).append("\n");
        sb.append(String.format("     DETALLE DE PEDIDO #%d (ASIG. 6)      \n", id));
        sb.append(String.format("==========================================")).append("\n");
        sb.append(String.format("Cliente: %s (%s)\n", cliente.getNombre(), cliente.getEmail()));
        sb.append(String.format("Estado: %s | Método: %s\n", estado, metodoPagoUsado != null ? metodoPagoUsado : "Pendiente"));
        sb.append(String.format("Dirección: %s\n", direccionEnvio != null ? direccionEnvio.toString() : "N/A"));
        sb.append("Ítems Adquiridos:\n");
        for (ItemPedido item : items) {
            sb.append("  • ").append(item.toString()).append("\n");
        }
        sb.append(String.format("------------------------------------------\n"));
        sb.append(String.format(" Subtotal:  $%.2f\n", subtotal));
        sb.append(String.format(" IVA (19%%): $%.2f\n", impuestos));
        sb.append(String.format(" TOTAL:     $%.2f\n", total));
        sb.append(String.format("=========================================="));
        return sb.toString();
    }
}
