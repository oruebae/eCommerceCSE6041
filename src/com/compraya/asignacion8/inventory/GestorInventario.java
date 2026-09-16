package com.compraya.asignacion8.inventory;

import com.compraya.asignacion8.exception.InventarioInsuficienteException;
import com.compraya.asignacion8.exception.ProductoNoEncontradoException;
import com.compraya.asignacion8.exception.StockInvalidoException;
import com.compraya.asignacion8.model.Producto;

import java.util.HashMap;
import java.util.Map;

/**
 * Gestor de Inventario centralizado con comprobación de límites y manejo de excepciones.
 */
public class GestorInventario {
    private final Map<Integer, Producto> catalogo;

    public GestorInventario() {
        this.catalogo = new HashMap<>();
    }

    public void registrarProducto(Producto producto) {
        if (producto == null) {
            throw new IllegalArgumentException("No se puede registrar un producto nulo en inventario.");
        }
        catalogo.put(producto.getId(), producto);
    }

    public Producto buscarProductoPorId(int productoId) {
        Producto p = catalogo.get(productoId);
        if (p == null) {
            throw new ProductoNoEncontradoException(productoId);
        }
        return p;
    }

    public void descontarStock(int productoId, int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad a descontar debe ser un entero positivo.");
        }
        Producto p = buscarProductoPorId(productoId);
        if (p.getStock() < cantidad) {
            throw new InventarioInsuficienteException(productoId, cantidad, p.getStock());
        }
        p.setStock(p.getStock() - cantidad);
    }

    public void actualizarStock(int productoId, int nuevoStock) {
        if (nuevoStock < 0) {
            throw new StockInvalidoException(nuevoStock);
        }
        Producto p = buscarProductoPorId(productoId);
        p.setStock(nuevoStock);
    }

    public int getStockActual(int productoId) {
        Producto p = buscarProductoPorId(productoId);
        return p.getStock();
    }

    public int getCantidadTotalProductos() {
        return catalogo.size();
    }
}
