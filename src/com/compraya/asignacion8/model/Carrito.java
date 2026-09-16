package com.compraya.asignacion8.model;

import com.compraya.asignacion8.exception.CarritoVacioException;
import com.compraya.asignacion8.exception.InventarioInsuficienteException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase Carrito de Compras para la Asignación 8.
 * Implementa métodos sobrecargados de adición y maneja excepciones personalizadas.
 */
public class Carrito {
    private final List<ItemCarrito> items;

    public Carrito() {
        this.items = new ArrayList<>();
    }

    /**
     * Añade un producto verificando stock disponible.
     * 
     * @throws InventarioInsuficienteException si la cantidad solicitada supera el stock.
     */
    public void agregarProducto(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("No se puede agregar un producto nulo al carrito.");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a agregar debe ser un entero positivo mayor que cero.");
        }

        // Buscar si ya está en el carrito
        int cantidadExistente = 0;
        ItemCarrito itemExistente = null;
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                itemExistente = item;
                cantidadExistente = item.getCantidad();
                break;
            }
        }

        int cantidadTotalDeseada = cantidadExistente + cantidad;
        if (cantidadTotalDeseada > producto.getStock()) {
            throw new InventarioInsuficienteException(producto.getId(), cantidadTotalDeseada, producto.getStock());
        }

        if (itemExistente != null) {
            itemExistente.setCantidad(cantidadTotalDeseada);
        } else {
            items.add(new ItemCarrito(producto, cantidad));
        }
    }

    public void agregarProducto(Producto producto) {
        agregarProducto(producto, 1);
    }

    public void removerProducto(int productoId) {
        if (items.isEmpty()) {
            throw new CarritoVacioException();
        }
        boolean removido = items.removeIf(item -> item.getProducto().getId() == productoId);
        if (!removido) {
            throw new IllegalArgumentException("El producto con ID " + productoId + " no existe en el carrito.");
        }
    }

    public double calcularTotal() {
        if (items.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (ItemCarrito item : items) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void vaciarCarrito() {
        items.clear();
    }

    public int getCantidadTotalItems() {
        int total = 0;
        for (ItemCarrito item : items) {
            total += item.getCantidad();
        }
        return total;
    }

    public List<ItemCarrito> getItems() {
        return Collections.unmodifiableList(items);
    }

    public boolean estaVacio() {
        return items.isEmpty();
    }
}
