package com.compraya.model;

import java.util.Objects;

/**
 * Clase Producto que representa un artículo en el catálogo de CompraYa.
 * Contiene información de identificación, detalles, precio, stock disponible
 * y su categoría asociada.
 */
public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int stock;
    private Categoria categoria;

    /**
     * Constructor por defecto.
     */
    public Producto() {
    }

    /**
     * Constructor principal para inicializar objetos de la clase Producto.
     * 
     * @param id          Identificador único del producto.
     * @param nombre      Nombre comercial del producto.
     * @param descripcion Descripción detallada de características.
     * @param precio      Precio unitario del producto (debe ser mayor o igual a 0).
     * @param stock       Cantidad disponible en inventario (mayor o igual a 0).
     * @param categoria   Categoría a la que pertenece el producto.
     */
    public Producto(int id, String nombre, String descripcion, double precio, int stock, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        setPrecio(precio);
        setStock(stock);
        this.categoria = categoria;
    }

    // --- Métodos de Negocio / Modificación de Estado ---

    /**
     * Actualiza la cantidad disponible en el stock.
     * Puede incrementar (valor positivo) o decrementar (valor negativo) el inventario.
     * 
     * @param cantidad Variación de stock.
     * @return true si la actualización fue exitosa, false si el resultado fuera un stock negativo.
     */
    public boolean actualizarStock(int cantidad) {
        if (this.stock + cantidad < 0) {
            return false; // No hay suficiente stock disponible
        }
        this.stock += cantidad;
        return true;
    }

    /**
     * Aplica un porcentaje de descuento al precio actual del producto.
     * 
     * @param porcentaje Porcentaje de descuento entre 0 y 100.
     */
    public void aplicarDescuento(double porcentaje) {
        if (porcentaje > 0 && porcentaje <= 100) {
            double descuento = this.precio * (porcentaje / 100.0);
            setPrecio(this.precio - descuento);
        }
    }

    // --- Getters y Setters (Propiedades) ---

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
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        if (precio < 0) {
            throw new IllegalArgumentException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Producto producto = (Producto) o;
        return id == producto.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Producto[ID: %d | Nombre: %s | Precio: $%.2f | Stock: %d | Categ: %s]",
                id, nombre, precio, stock, (categoria != null ? categoria.getNombre() : "Sin Categoria"));
    }
}
