package com.compraya.asignacion7.model;

import com.compraya.asignacion7.config.ConfiguracionSistema;
import com.compraya.asignacion7.observer.GestorNotificaciones;

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

    public Pedido(int id, Cliente cliente, DireccionEnvio direccionEnvio) {
        this.id = id;
        this.cliente = cliente;
        this.direccionEnvio = direccionEnvio;
        this.items = new ArrayList<>();
        this.estado = "PENDIENTE";
        this.fecha = LocalDateTime.now();
    }

    public static Pedido crearDesdeCarrito(int id, Cliente cliente, DireccionEnvio direccionEnvio, GestorNotificaciones gestorNotif) {
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

        double tasaIVA = ConfiguracionSistema.getInstancia().getTasaIVA();
        pedido.subtotal = subtotalAcumulado;
        pedido.impuestos = subtotalAcumulado * tasaIVA;
        pedido.total = pedido.subtotal + pedido.impuestos;

        carrito.vaciarCarrito();
        return pedido;
    }

    public void cambiarEstado(String nuevoEstado, GestorNotificaciones gestorNotif) {
        this.estado = nuevoEstado;
        System.out.printf("[PEDIDO #%d] Estado actualizado a: '%s'\n", id, nuevoEstado);
        if (gestorNotif != null) {
            gestorNotif.notificarObservadores("CAMBIO_ESTADO_PEDIDO", this);
        }
    }

    public int getId() { return id; }
    public Cliente getCliente() { return cliente; }
    public String getEstado() { return estado; }
    public double getTotal() { return total; }
    public List<ItemPedido> getItems() { return Collections.unmodifiableList(items); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("==========================================")).append("\n");
        sb.append(String.format("     DETALLE DE PEDIDO #%d (ASIG. 7)      \n", id));
        sb.append(String.format("==========================================")).append("\n");
        sb.append(String.format("Cliente: %s (%s)\n", cliente.getNombre(), cliente.getEmail()));
        sb.append(String.format("Estado: %s\n", estado));
        sb.append(String.format("Dirección: %s\n", direccionEnvio != null ? direccionEnvio.toString() : "N/A"));
        sb.append("Ítems Adquiridos:\n");
        for (ItemPedido item : items) {
            sb.append("  • ").append(item.toString()).append("\n");
        }
        sb.append(String.format("------------------------------------------\n"));
        sb.append(String.format(" Subtotal:  $%.2f\n", subtotal));
        sb.append(String.format(" IVA (%.0f%%): $%.2f\n", ConfiguracionSistema.getInstancia().getTasaIVA() * 100, impuestos));
        sb.append(String.format(" TOTAL:     $%.2f\n", total));
        sb.append(String.format("=========================================="));
        return sb.toString();
    }
}
