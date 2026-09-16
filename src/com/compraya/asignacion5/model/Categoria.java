package com.compraya.asignacion5.model;

import java.util.Objects;

public class Categoria {
    private int id;
    private String nombre;
    private String descripcion;

    public Categoria() {
    }

    public Categoria(int id, String nombre, String descripcion) {
        this.id = id;
        setNombre(nombre);
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("[VALIDACIÓN ENCAPSULADA] El nombre de la categoría no puede estar vacío.");
        }
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s (ID: %d)", nombre, id);
    }
}
