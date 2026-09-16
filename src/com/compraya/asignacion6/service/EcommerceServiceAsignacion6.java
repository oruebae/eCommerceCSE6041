package com.compraya.asignacion6.service;

import com.compraya.asignacion6.inventory.GestorInventarioDigital;
import com.compraya.asignacion6.inventory.GestorInventarioFisico;
import com.compraya.asignacion6.model.*;
import com.compraya.asignacion6.payment.ProcesoPago;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EcommerceServiceAsignacion6 {
    private GestorInventarioFisico gestorFisico;
    private GestorInventarioDigital gestorDigital;
    private List<Item> productos;
    private List<Usuario> usuarios;
    private List<Pedido> pedidos;
    private int contadorPedidoId = 1001;

    public EcommerceServiceAsignacion6() {
        this.gestorFisico = new GestorInventarioFisico();
        this.gestorDigital = new GestorInventarioDigital();
        this.productos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        inicializarDatosDemo();
    }

    private void inicializarDatosDemo() {
        Categoria elec = new Categoria(1, "Electrónica", "Tecnología");
        Categoria hogar = new Categoria(2, "Hogar", "Artículos de hogar");
        Categoria software = new Categoria(3, "Software", "Contenidos digitales");

        ProductoFisico pf1 = new ProductoFisico(1, "Smartphone X Pro", "Teléfono OLED", 1500000.0, 15, elec, 0.45, "15x7x0.8 cm", 12000.0);
        ProductoFisico pf2 = new ProductoFisico(2, "Cafetera Express", "Cafetera 15 bares", 450000.0, 10, hogar, 4.2, "35x25x30 cm", 18000.0);

        ProductoDigital pd1 = new ProductoDigital(3, "Curso Java OOP Avanzado", "Masterclass POO 2026", 120000.0, 999, software, "MP4/ZIP", 2450.0, "https://cdn.compraya.com/cursos/java-oop", "LIC-JAVA-2026");
        ProductoDigital pd2 = new ProductoDigital(4, "E-Book Arquitectura Software", "Guía de patrones", 45000.0, 999, software, "PDF/EPUB", 18.5, "https://cdn.compraya.com/ebooks/arch", "LIC-EBOOK-SINGLE");

        // Registrar en los Gestores de Inventario Abstractos
        gestorFisico.añadirProducto(pf1);
        gestorFisico.añadirProducto(pf2);

        gestorDigital.añadirProducto(pd1);
        gestorDigital.añadirProducto(pd2);

        productos.add(pf1);
        productos.add(pf2);
        productos.add(pd1);
        productos.add(pd2);

        Cliente clienteDemo = new Cliente(1, "Alberto López", "alberto.lopez@example.com", "pass1234");
        usuarios.add(clienteDemo);
    }

    public Optional<Usuario> autenticarUsuario(String email, String pass) {
        return usuarios.stream().filter(u -> u.getEmail().equalsIgnoreCase(email) && u.iniciarSesion(pass)).findFirst();
    }

    public Pedido realizarCheckout(Cliente cliente, DireccionEnvio direccion, ProcesoPago pasarelaPago) {
        Pedido pedido = Pedido.crearDesdeCarrito(contadorPedidoId++, cliente, direccion);
        if (pedido == null) return null;

        boolean exitoPago = pedido.procesarYConfirmarPago(pasarelaPago);
        if (exitoPago) {
            cliente.agregarPedidoAlHistorial(pedido);
            pedidos.add(pedido);
            return pedido;
        }
        return null;
    }

    public GestorInventarioFisico getGestorFisico() { return gestorFisico; }
    public GestorInventarioDigital getGestorDigital() { return gestorDigital; }
    public List<Item> getProductos() { return productos; }
    public List<Pedido> getPedidos() { return pedidos; }
}
