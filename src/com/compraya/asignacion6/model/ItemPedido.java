package com.compraya.asignacion6.model;

public class ItemPedido {
    private int productoId;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;
    private double subtotal;

    public ItemPedido() {}

    public ItemPedido(Item item, int cantidad) {
        this.productoId = item.getId();
        this.nombreProducto = item.getNombre();
        this.precioUnitario = item.getPrecio();
        this.cantidad = cantidad;
        this.subtotal = this.precioUnitario * cantidad;
    }

    public int getProductoId() { return productoId; }
    public String getNombreProducto() { return nombreProducto; }
    public double getPrecioUnitario() { return precioUnitario; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return String.format("%s (ID: %d) x%d @ $%.2f = $%.2f", nombreProducto, productoId, cantidad, precioUnitario, subtotal);
    }
}
