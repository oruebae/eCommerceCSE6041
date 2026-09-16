package com.compraya.asignacion7.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Carrito {
    private int id;
    private List<ItemCarrito> items;
    private double total;

    public Carrito() {
        this.items = new ArrayList<>();
        this.total = 0.0;
    }

    public boolean agregarProducto(Item item, int cantidad) {
        if (item == null || cantidad <= 0) return false;
        for (ItemCarrito ic : items) {
            if (ic.getItem().getId() == item.getId()) {
                ic.setCantidad(ic.getCantidad() + cantidad);
                calcularTotal();
                return true;
            }
        }
        items.add(new ItemCarrito(item, cantidad));
        calcularTotal();
        return true;
    }

    public double calcularTotal() {
        this.total = 0.0;
        for (ItemCarrito ic : items) {
            this.total += ic.getSubtotal();
        }
        return this.total;
    }

    public void vaciarCarrito() {
        items.clear();
        this.total = 0.0;
    }

    public List<ItemCarrito> getItems() { return Collections.unmodifiableList(items); }
    public double getTotal() { return calcularTotal(); }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=== Carrito Asignación 7 (Total: $%.2f) ===\n", getTotal()));
        for (ItemCarrito ic : items) {
            sb.append(" - ").append(ic.toString()).append("\n");
        }
        return sb.toString();
    }
}
