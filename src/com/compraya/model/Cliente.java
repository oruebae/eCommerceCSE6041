package com.compraya.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Clase Cliente que extiende de Usuario (Herencia).
 * Especializa las funcionalidades del usuario comprador agregando historial de compras,
 * preferencias de categorías y dirección predeterminada de despacho.
 */
public class Cliente extends Usuario {
    private List<Pedido> historialCompras;
    private Carrito carrito;
    private List<String> preferencias;
    private DireccionEnvio direccionPredeterminada;

    public Cliente() {
        super();
        this.historialCompras = new ArrayList<>();
        this.carrito = new Carrito();
        this.preferencias = new ArrayList<>();
    }

    public Cliente(int id, String nombre, String email, String password) {
        super(id, nombre, email, password);
        this.historialCompras = new ArrayList<>();
        this.carrito = new Carrito();
        this.preferencias = new ArrayList<>();
    }

    // --- Métodos de Especialización ---

    public void agregarPreferencia(String preferencia) {
        if (preferencia != null && !preferencia.trim().isEmpty() && !preferencias.contains(preferencia)) {
            this.preferencias.add(preferencia);
            System.out.printf("[CLIENTE %s] Nueva preferencia agregada: %s\n", getNombre(), preferencia);
        }
    }

    public List<Producto> obtenerRecomendaciones(List<Producto> catalogo) {
        if (preferencias.isEmpty() || catalogo == null) {
            return catalogo != null ? catalogo.stream().limit(3).collect(Collectors.toList()) : new ArrayList<>();
        }

        return catalogo.stream()
                .filter(p -> p.getCategoria() != null && preferencias.stream()
                        .anyMatch(pref -> pref.equalsIgnoreCase(p.getCategoria().getNombre())))
                .collect(Collectors.toList());
    }

    public void agregarPedidoAlHistorial(Pedido pedido) {
        if (pedido != null) {
            this.historialCompras.add(pedido);
        }
    }

    // --- Getters y Setters ---

    public List<Pedido> getHistorialCompras() {
        return historialCompras;
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }

    public List<String> getPreferencias() {
        return preferencias;
    }

    public DireccionEnvio getDireccionPredeterminada() {
        return direccionPredeterminada;
    }

    public void setDireccionPredeterminada(DireccionEnvio direccionPredeterminada) {
        this.direccionPredeterminada = direccionPredeterminada;
    }

    @Override
    public String toString() {
        return String.format("Cliente[ID: %d | Nombre: %s | Email: %s | Preferencias: %s | Compras: %d]",
                getId(), getNombre(), getEmail(), preferencias.toString(), historialCompras.size());
    }
}
