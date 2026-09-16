package com.compraya.asignacion6.inventory;

import com.compraya.asignacion6.model.Item;

/**
 * Clase Abstracta GestorInventario (Asignación 6).
 * Abstrae y define el contrato general para la gestión de inventarios en CompraYa,
 * obligando a las subclases físicas y digitales a implementar la lógica concreta de alta, baja, actualización y reporte.
 */
public abstract class GestorInventario {
    private String tipoInventario;
    private int totalArticulosProcesados;

    public GestorInventario(String tipoInventario) {
        this.tipoInventario = tipoInventario;
        this.totalArticulosProcesados = 0;
    }

    // --- Métodos Abstractos Obligatorios (Requerimiento Asignación 6) ---

    /**
     * Registra un nuevo producto/ítem dentro del sistema de inventario.
     * 
     * @param item Producto a añadir.
     * @return true si la adición fue exitosa.
     */
    public abstract boolean añadirProducto(Item item);

    /**
     * Remueve un producto del sistema de inventario por su ID.
     * 
     * @param id Identificador único del producto.
     * @return true si el producto fue removido.
     */
    public abstract boolean eliminarProducto(int id);

    /**
     * Actualiza la cantidad de stock disponible para un producto.
     * 
     * @param id         ID del producto.
     * @param nuevoStock Nuevo valor de stock (mayor o igual a 0).
     * @return true si se actualizó exitosamente.
     */
    public abstract boolean actualizarStock(int id, int nuevoStock);

    /**
     * Genera un reporte detallado en consola sobre las existencias y estado del inventario.
     */
    public abstract void generarReporteInventario();

    // --- Métodos Generales Encapsulados ---

    public String getTipoInventario() {
        return tipoInventario;
    }

    public int getTotalArticulosProcesados() {
        return totalArticulosProcesados;
    }

    protected void incrementarArticulosProcesados() {
        this.totalArticulosProcesados++;
    }
}
