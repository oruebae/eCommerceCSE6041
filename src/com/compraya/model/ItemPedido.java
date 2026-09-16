package com.compraya.model;

/**
 * Representa la snapshot inmutable de un producto y su precio en el momento exacto
 * en que se confirma un pedido en CompraYa.
 */
public class ItemPedido {
    private int productoId;
    private String nombreProducto;
    private double precioUnitario;
    private int cantidad;
    private double subtotal;

    public ItemPedido() {
    }

    public ItemPedido(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        this.productoId = producto.getId();
        this.nombreProducto = producto.getNombre();
        this.precioUnitario = producto.getPrecio();
        this.cantidad = cantidad;
        this.subtotal = this.precioUnitario * cantidad;
    }

    public int getProductoId() {
        return productoId;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return subtotal;
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %d) x%d @ $%.2f = $%.2f",
                nombreProducto, productoId, cantidad, precioUnitario, subtotal);
    }
}
