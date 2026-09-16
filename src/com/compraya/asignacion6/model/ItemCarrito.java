package com.compraya.asignacion6.model;

public class ItemCarrito {
    private Item item;
    private int cantidad;
    private double subtotal;

    public ItemCarrito() {}

    public ItemCarrito(Item item, int cantidad) {
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

    public Item getItem() { return item; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; calcularSubtotal(); }
    public double getSubtotal() { return calcularSubtotal(); }

    @Override
    public String toString() {
        return String.format("%s x%d -> Subtotal: $%.2f", item.getNombre(), cantidad, getSubtotal());
    }
}
