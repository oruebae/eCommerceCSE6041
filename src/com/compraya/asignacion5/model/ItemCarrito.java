package com.compraya.asignacion5.model;

public class ItemCarrito {
    private Item item;
    private int cantidad;
    private double subtotal;

    public ItemCarrito() {
    }

    public ItemCarrito(Item item, int cantidad) {
        if (item == null) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El item del carrito no puede ser nulo.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] La cantidad debe ser mayor a cero.");
        }
        this.item = item;
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public double calcularSubtotal() {
        if (item != null) {
            this.subtotal = item.getPrecio() * cantidad;
        } else {
            this.subtotal = 0.0;
        }
        return this.subtotal;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El item no puede ser nulo.");
        }
        this.item = item;
        calcularSubtotal();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] La cantidad debe ser mayor a cero.");
        }
        this.cantidad = cantidad;
        calcularSubtotal();
    }

    public double getSubtotal() {
        return calcularSubtotal();
    }

    @Override
    public String toString() {
        return String.format("%s x%d -> Subtotal: $%.2f", item.getNombre(), cantidad, getSubtotal());
    }
}
