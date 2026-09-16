package com.compraya.asignacion6.model;

public class Producto extends Item {
    public Producto() { super(); }
    public Producto(int id, String nombre, String descripcion, double precio, int stock, Categoria categoria) {
        super(id, nombre, descripcion, precio, stock, categoria);
    }

    @Override
    public void mostrarDetalle() {
        System.out.printf("Producto[ID: %d | %s | $%.2f | Stock: %d]\n", getId(), getNombre(), getPrecio(), getStock());
    }

    @Override
    public boolean requiereEnvioFisico() { return true; }

    @Override
    public String obtenerDetallesEspecificos() { return "Producto General"; }
}
