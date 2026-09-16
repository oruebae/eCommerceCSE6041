package com.compraya.asignacion5.service;

import com.compraya.asignacion5.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EcommerceServiceAsignacion5 {
    private List<Categoria> categorias;
    private List<Item> productos;
    private List<Usuario> usuarios;
    private List<Pedido> pedidos;
    private int contadorProductoId = 1;
    private int contadorUsuarioId = 1;
    private int contadorPedidoId = 1001;
    private int contadorPagoId = 5001;

    public EcommerceServiceAsignacion5() {
        this.categorias = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        inicializarDatosDemo();
    }

    private void inicializarDatosDemo() {
        Categoria elec = new Categoria(1, "Electrónica", "Dispositivos tecnológicos");
        Categoria hogar = new Categoria(2, "Hogar", "Artículos para el hogar");
        Categoria software = new Categoria(3, "Software y Libros Digitales", "Contenidos descargables");
        categorias.add(elec);
        categorias.add(hogar);
        categorias.add(software);

        agregarProducto(new ProductoFisico(contadorProductoId++, "Smartphone X Pro", "Teléfono 128GB OLED", 1500000.0, 15, elec, 0.45, "15x7x0.8 cm", 12000.0));
        agregarProducto(new ProductoFisico(contadorProductoId++, "Cafetera Express", "Cafetera de presión 15 bares", 450000.0, 10, hogar, 4.2, "35x25x30 cm", 18000.0));
        agregarProducto(new ProductoDigital(contadorProductoId++, "Curso Java OOP Avanzado", "Masterclass interactiva POO 2026", 120000.0, 999, software, "MP4/ZIP", 2450.0, "https://cdn.compraya.com/cursos/java-oop", "LIC-JAVA-2026-BIU"));
        agregarProducto(new ProductoDigital(contadorProductoId++, "E-Book Arquitectura Software", "Guía práctica de patrones", 45000.0, 999, software, "PDF/EPUB", 18.5, "https://cdn.compraya.com/ebooks/arch-design", "LIC-EBOOK-SINGLE"));

        Cliente clienteDemo = new Cliente(contadorUsuarioId++, "Alberto López", "alberto.lopez@example.com", "pass1234");
        clienteDemo.agregarPreferencia("Software y Libros Digitales");
        clienteDemo.agregarPreferencia("Electrónica");

        Administrador adminDemo = new Administrador(contadorUsuarioId++, "José Requeno", "admin.jose@compraya.com", "admin2026", "SUPERADMIN", "GESTION_CATALOGO");

        usuarios.add(clienteDemo);
        usuarios.add(adminDemo);
    }

    public void agregarProducto(Item item) {
        productos.add(item);
    }

    public List<Item> getProductos() {
        return productos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public Optional<Item> buscarProductoPorId(int id) {
        return productos.stream().filter(p -> p.getId() == id).findFirst();
    }

    public Usuario registrarUsuario(String nombre, String email, String password, boolean esAdmin) {
        Usuario usuario;
        if (esAdmin) {
            usuario = new Administrador(contadorUsuarioId++, nombre, email, password, "ADMINISTRADOR", "GENERAL");
        } else {
            usuario = new Cliente(contadorUsuarioId++, nombre, email, password);
        }
        usuarios.add(usuario);
        return usuario;
    }

    public Optional<Usuario> autenticarUsuario(String email, String password) {
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email) && u.iniciarSesion(password)) {
                return Optional.of(u);
            }
        }
        return Optional.empty();
    }

    public Pedido realizarCheckout(Cliente cliente, DireccionEnvio direccion, String metodoPago) {
        Pedido nuevoPedido = Pedido.crearDesdeCarrito(contadorPedidoId++, cliente, direccion);
        if (nuevoPedido == null) {
            return null;
        }

        Pago pago = new Pago(contadorPagoId++, nuevoPedido.getTotal(), metodoPago);
        boolean exitoPago = pago.procesarPago();

        if (exitoPago) {
            nuevoPedido.confirmarPedido(pago);
            cliente.agregarPedidoAlHistorial(nuevoPedido);
            pedidos.add(nuevoPedido);
            return nuevoPedido;
        } else {
            nuevoPedido.cancelarPedido();
            return null;
        }
    }

    public double calcularTotalVentasPlataforma() {
        return pedidos.stream()
                .filter(p -> "PAGADO".equals(p.getEstado()) || "ENVIADO".equals(p.getEstado()) || "ENTREGADO".equals(p.getEstado()))
                .mapToDouble(Pedido::getTotal)
                .sum();
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
