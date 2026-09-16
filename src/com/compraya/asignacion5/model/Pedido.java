package com.compraya.asignacion5.model;

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
    private Pago pago;

    public static final double TASA_IVA = 0.19;

    public Pedido() {
        this.items = new ArrayList<>();
        this.estado = "PENDIENTE";
        this.fecha = LocalDateTime.now();
    }

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
            System.out.println("Error: El carrito de compras está vacío.");
            return null;
        }

        Pedido pedido = new Pedido(id, cliente, direccionEnvio);
        double subtotalAcumulado = 0.0;

        for (ItemCarrito itemCart : carrito.getItems()) {
            Item item = itemCart.getItem();
            int cant = itemCart.getCantidad();

            boolean stockActualizado = item.actualizarStock(-cant);
            if (!stockActualizado) {
                System.out.printf("Error crítico: Stock insuficiente para '%s'.\n", item.getNombre());
                return null;
            }

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

    public boolean confirmarPedido(Pago pagoProcesado) {
        if (pagoProcesado != null && pagoProcesado.verificarPago()) {
            this.pago = pagoProcesado;
            this.estado = "PAGADO";
            System.out.printf("[PEDIDO #%d] Confirmado y pagado con éxito. Total: $%.2f\n", id, total);
            return true;
        }
        System.out.printf("[PEDIDO #%d] No se pudo confirmar: Pago no aprobado.\n", id);
        return false;
    }

    public void cancelarPedido() {
        if ("ENTREGADO".equals(estado)) {
            System.out.println("No se puede cancelar un pedido entregado.");
            return;
        }
        this.estado = "CANCELADO";
        System.out.printf("[PEDIDO #%d] Ha sido cancelado.\n", id);
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.printf("[PEDIDO #%d] Estado actualizado a: %s\n", id, nuevoEstado);
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public List<ItemPedido> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public double getTotal() {
        return total;
    }

    public DireccionEnvio getDireccionEnvio() {
        return direccionEnvio;
    }

    public Pago getPago() {
        return pago;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("==========================================")).append("\n");
        sb.append(String.format("     DETALLE DE PEDIDO #%d (ASIG. 5)      \n", id));
        sb.append(String.format("==========================================")).append("\n");
        sb.append(String.format("Cliente: %s (%s)\n", cliente.getNombre(), cliente.getEmail()));
        sb.append(String.format("Fecha: %s\n", fecha.toString()));
        sb.append(String.format("Estado: %s\n", estado));
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
