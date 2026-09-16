package com.compraya.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase Carrito (o CarritoDeCompras) que gestiona los productos seleccionados por el usuario
 * y mantiene el cálculo en tiempo real del total acumulado.
 */
public class Carrito {
    private int id;
    private List<ItemCarrito> items;
    private double total;
    private LocalDateTime fechaCreacion;

    /**
     * Constructor para inicializar el objeto Carrito.
     */
    public Carrito() {
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Constructor con ID para inicializar el Carrito de Compras.
     * 
     * @param id Identificador único del carrito.
     */
    public Carrito(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.fechaCreacion = LocalDateTime.now();
    }

    // --- Métodos de Negocio Exigidos ---

    /**
     * Añade un producto al carrito con la cantidad especificada.
     * Si el producto ya existía en el carrito, incrementa la cantidad existente.
     * 
     * @param producto Producto a añadir.
     * @param cantidad Cantidad a adquirir.
     * @return true si se pudo agregar (hay suficiente stock disponible), false en caso contrario.
     */
    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            System.out.println("Error: Producto no válido o cantidad menor o igual a cero.");
            return false;
        }

        if (producto.getStock() < cantidad) {
            System.out.printf("Error: Stock insuficiente para '%s'. Disponible: %d, Solicitado: %d\n",
                    producto.getNombre(), producto.getStock(), cantidad);
            return false;
        }

        // Buscar si ya existe el producto en los ítems
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                int nuevaCantidad = item.getCantidad() + cantidad;
                if (producto.getStock() < nuevaCantidad) {
                    System.out.printf("Error: Supera el stock disponible (%d unidades).\n", producto.getStock());
                    return false;
                }
                item.setCantidad(nuevaCantidad);
                calcularTotal();
                return true;
            }
        }

        // Si no existe, crea un nuevo ItemCarrito
        items.add(new ItemCarrito(producto, cantidad));
        calcularTotal();
        return true;
    }

    /**
     * Remueve un producto del carrito por su ID de producto.
     * 
     * @param productoId ID del producto a eliminar del carrito.
     * @return true si se removió exitosamente, false si no se encontró en el carrito.
     */
    public boolean removerProducto(int productoId) {
        boolean removido = items.removeIf(item -> item.getProducto().getId() == productoId);
        if (removido) {
            calcularTotal();
        }
        return removido;
    }

    /**
     * Modifica la cantidad asignada a un producto en el carrito.
     * 
     * @param productoId    ID del producto.
     * @param nuevaCantidad Nueva cantidad requerida.
     * @return true si se actualizó exitosamente.
     */
    public boolean modificarCantidad(int productoId, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            return removerProducto(productoId);
        }

        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == productoId) {
                if (item.getProducto().getStock() < nuevaCantidad) {
                    System.out.printf("Error: Stock insuficiente (%d unidades en stock).\n", item.getProducto().getStock());
                    return false;
                }
                item.setCantidad(nuevaCantidad);
                calcularTotal();
                return true;
            }
        }
        return false;
    }

    /**
     * Recalcula el total del carrito sumando el subtotal de cada ítem en la lista.
     * 
     * @return El monto total acumulado en el carrito.
     */
    public double calcularTotal() {
        this.total = 0.0;
        for (ItemCarrito item : items) {
            this.total += item.getSubtotal();
        }
        return this.total;
    }

    /**
     * Vacía todos los productos del carrito y reinicia el total a cero.
     */
    public void vaciarCarrito() {
        items.clear();
        this.total = 0.0;
    }

    // --- Getters y Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemCarrito> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getTotal() {
        return calcularTotal();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public int getCantidadTotalProductos() {
        int suma = 0;
        for (ItemCarrito item : items) {
            suma += item.getCantidad();
        }
        return suma;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=== Carrito de Compras (Total: $%.2f) ===\n", getTotal()));
        if (items.isEmpty()) {
            sb.append(" [El carrito está vacío]\n");
        } else {
            for (ItemCarrito item : items) {
                sb.append(" - ").append(item.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
