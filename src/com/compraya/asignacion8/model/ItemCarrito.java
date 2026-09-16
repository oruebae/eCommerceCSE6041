package com.compraya.asignacion8.model;

/**
 * Representa un ítem dentro del carrito de compras.
 */
public class ItemCarrito {
    private final Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto del ítem de carrito no puede ser nulo.");
        }
        this.producto = producto;
        setCantidad(cantidad);
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.cantidad = cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}
