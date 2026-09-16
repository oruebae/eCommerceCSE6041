package com.compraya.asignacion7.model;

import java.util.ArrayList;
import java.util.List;

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

    public List<Pedido> getHistorialCompras() { return historialCompras; }
    public Carrito getCarrito() { return carrito; }
}
