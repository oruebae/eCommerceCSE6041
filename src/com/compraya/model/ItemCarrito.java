package com.compraya.model;

/**
 * Representa la relación de composición entre el Carrito de Compras y un Producto,
 * indicando la cantidad seleccionada y calculando el subtotal.
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;
    private double subtotal;

    public ItemCarrito() {
    }

    public ItemCarrito(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.producto = producto;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    /**
     * Calcula el subtotal multiplicando la cantidad por el precio actual del producto.
     * 
     * @return El subtotal calculado.
     */
    public double calcularSubtotal() {
        if (producto != null) {
            this.subtotal = producto.getPrecio() * cantidad;
        } else {
            this.subtotal = 0.0;
        }
        return this.subtotal;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
        calcularSubtotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        }
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public double getSubtotal() {
        return calcularSubtotal();
    }

    @Override
    public String toString() {
        return String.format("%s x%d -> Subtotal: $%.2f",
                producto.getNombre(), cantidad, getSubtotal());
    }
}
