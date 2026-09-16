package com.compraya.asignacion5.model;

/**
 * Clase Producto (Asignación 5) que extiende la clase abstracta Item.
 * Implementa la abstracción base para productos de catálogo y refuerza el encapsulamiento.
 */
public class Producto extends Item {

    public Producto() {
        super();
    }

    public Producto(int id, String nombre, String descripcion, double precio, int stock, Categoria categoria) {
        super(id, nombre, descripcion, precio, stock, categoria);
    }

    @Override
    public void mostrarDetalle() {
        System.out.println("-------------------------------------------------------");
        System.out.println(" FICHA TÉCNICA DE PRODUCTO DE CATÁLOGICO (ITEM)        ");
        System.out.println("-------------------------------------------------------");
        System.out.println("ID:          " + getId());
        System.out.println("Nombre:      " + getNombre());
        System.out.println("Descripción: " + getDescripcion());
        System.out.printf("Precio:      $%.2f\n", getPrecio());
        System.out.println("Stock:       " + getStock() + " unidades");
        System.out.println("Categoría:   " + (getCategoria() != null ? getCategoria().getNombre() : "N/A"));
    }

    @Override
    public boolean requiereEnvioFisico() {
        return true;
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Producto General de Catálogo";
    }
}
