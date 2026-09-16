package com.compraya.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase Cliente que extiende Usuario (Herencia).
 * Añade historial de compras y carrito de compras del cliente.
 */
public class Cliente extends Usuario {
    private List<Pedido> historialCompras;
    private Carrito carrito;

    public Cliente() {
        super();
        this.historialCompras = new ArrayList<>();
        this.carrito = new Carrito();
    }

    public Cliente(int id, String nombre, String email, String password) {
        super(id, nombre, email, password);
        this.historialCompras = new ArrayList<>();
        this.carrito = new Carrito();
    }

    public void agregarPedidoAlHistorial(Pedido pedido) {
        if (pedido != null) {
            this.historialCompras.add(pedido);
        }
    }

    public List<Pedido> getHistorialCompras() {
        return historialCompras;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    @Override
    public String toString() {
        return String.format("Cliente[ID: %d | Nombre: %s | Email: %s | Compras: %d]",
                getId(), getNombre(), getEmail(), historialCompras.size());
    }
}
