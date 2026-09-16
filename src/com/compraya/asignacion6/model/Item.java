package com.compraya.asignacion6.model;

import java.util.Objects;

public abstract class Item {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private Categoria categoria;

    public Item() {
    }

    public Item(int id, String nombre, String descripcion, double precio, int stock, Categoria categoria) {
        this.id = id;
        setNombre(nombre);
        this.descripcion = descripcion;
        setPrecio(precio);
        setStock(stock);
        this.categoria = categoria;
    }

    public abstract void mostrarDetalle();
    public abstract boolean requiereEnvioFisico();
    public abstract String obtenerDetallesEspecificos();

    public boolean actualizarStock(int cantidad) {
        if (this.stock + cantidad < 0) return false;
        this.stock += cantidad;
        return true;
    }

    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 100) {
            double descuento = this.precio * (porcentaje / 100.0);
            setPrecio(this.precio - descuento);
        }
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public Categoria getCategoria() { return categoria; }
    public void setCategoria(Categoria categoria) { this.categoria = categoria; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id;
    }

    @Override
    public int hashCode() { return Objects.hash(id); }

    @Override
    public String toString() {
        return String.format("Item[ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d]", id, nombre, precio, stock);
    }
}
