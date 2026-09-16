package com.compraya.asignacion8.model;

import com.compraya.asignacion8.exception.CarritoVacioException;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Pedido para la Asignación 8.
 * Representa una orden de compra generada a partir de un Carrito.
 */
public class Pedido {
    private final int id;
    private final Cliente cliente;
    private final List<ItemCarrito> items;
    private final double subtotal;
    private final double iva;
    private final double total;
    private String estado;

    public Pedido(int id, Cliente cliente, Carrito carrito, double tasaIva) {
        if (id <= 0) {
            throw new IllegalArgumentException("El ID del pedido debe ser positivo.");
        }
        if (cliente == null) {
            throw new IllegalArgumentException("El pedido requiere un cliente válido.");
        }
        if (carrito == null || carrito.estaVacio()) {
            throw new CarritoVacioException();
        }

        this.id = id;
        this.cliente = cliente;
        this.items = new ArrayList<>(carrito.getItems());
        this.subtotal = carrito.calcularTotal();
        this.iva = this.subtotal * tasaIva;
        this.total = this.subtotal + this.iva;
        this.estado = "CREADO";
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemCarrito> getItems() {
        return items;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public double getIva() {
        return iva;
    }

    public double getTotal() {
        return total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        if (estado == null || estado.trim().isEmpty()) {
            throw new IllegalArgumentException("El estado del pedido no puede estar vacío.");
        }
        this.estado = estado.trim().toUpperCase();
    }
}
