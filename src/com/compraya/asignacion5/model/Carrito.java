package com.compraya.asignacion5.model;

import com.compraya.asignacion5.service.EcommerceServiceAsignacion5;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase Carrito (Asignación 5).
 * Encapsulamiento estricto: Protege los atributos internos impidiendo modificación directa de la lista de ítems.
 */
public class Carrito {
    private int id;
    private List<ItemCarrito> items;
    private double total;
    private LocalDateTime fechaCreacion;

    public Carrito() {
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.fechaCreacion = LocalDateTime.now();
    }

    public Carrito(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.fechaCreacion = LocalDateTime.now();
    }

    // --- Métodos Sobrecargados con Encapsulamiento Protegido ---

    public boolean agregarProducto(Item item) {
        return agregarProducto(item, 1);
    }

    public boolean agregarProducto(Item item, int cantidad) {
        if (item == null || cantidad <= 0) {
            System.out.println("[ENCAPSULAMIENTO] Error: Ítem no válido o cantidad menor o igual a cero.");
            return false;
        }

        if (item.getStock() < cantidad) {
            System.out.printf("[ENCAPSULAMIENTO] Error: Stock insuficiente para '%s'. Disponible: %d, Solicitado: %d\n",
                    item.getNombre(), item.getStock(), cantidad);
            return false;
        }

        for (ItemCarrito itemCart : items) {
            if (itemCart.getItem().getId() == item.getId()) {
                int nuevaCantidad = itemCart.getCantidad() + cantidad;
                if (item.getStock() < nuevaCantidad) {
                    System.out.printf("[ENCAPSULAMIENTO] Error: Supera el stock disponible (%d unidades).\n", item.getStock());
                    return false;
                }
                itemCart.setCantidad(nuevaCantidad);
                calcularTotal();
                return true;
            }
        }

        items.add(new ItemCarrito(item, cantidad));
        calcularTotal();
        return true;
    }

    public boolean agregarProducto(int productoId, EcommerceServiceAsignacion5 service) {
        return agregarProducto(productoId, 1, service);
    }

    public boolean agregarProducto(int productoId, int cantidad, EcommerceServiceAsignacion5 service) {
        if (service == null) return false;
        Item item = service.buscarProductoPorId(productoId).orElse(null);
        if (item == null) return false;
        return agregarProducto(item, cantidad);
    }

    public boolean agregarProducto(String nombre, double precio, int cantidad, Categoria categoria) {
        int idTemp = (int) (System.currentTimeMillis() % 100000);
        Producto prodGenerico = new Producto(idTemp, nombre, "Producto Rápido Generado", precio, cantidad + 10, categoria);
        return agregarProducto(prodGenerico, cantidad);
    }

    public boolean removerProducto(int productoId) {
        boolean removido = items.removeIf(itemCart -> itemCart.getItem().getId() == productoId);
        if (removido) {
            calcularTotal();
        }
        return removido;
    }

    public boolean removerProducto(Item item) {
        if (item == null) return false;
        return removerProducto(item.getId());
    }

    public boolean modificarCantidad(int productoId, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            return removerProducto(productoId);
        }

        for (ItemCarrito itemCart : items) {
            if (itemCart.getItem().getId() == productoId) {
                if (itemCart.getItem().getStock() < nuevaCantidad) {
                    return false;
                }
                itemCart.setCantidad(nuevaCantidad);
                calcularTotal();
                return true;
            }
        }
        return false;
    }

    public double calcularTotal() {
        this.total = 0.0;
        for (ItemCarrito itemCart : items) {
            this.total += itemCart.getSubtotal();
        }
        return this.total;
    }

    public void vaciarCarrito() {
        items.clear();
        this.total = 0.0;
    }

    // --- Getters Encapsulados (Vista Inmodificable) ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * Retorna una vista inmodificable de la lista interna para preservar el Encapsulamiento.
     */
    public List<ItemCarrito> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getTotal() {
        return calcularTotal();
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=== Carrito de Compras Encapsulado (Total: $%.2f) ===\n", getTotal()));
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
