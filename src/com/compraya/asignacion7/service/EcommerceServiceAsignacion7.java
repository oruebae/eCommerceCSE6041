package com.compraya.asignacion7.service;

import com.compraya.asignacion7.config.ConfiguracionSistema;
import com.compraya.asignacion7.factory.FabricaEntidades;
import com.compraya.asignacion7.model.*;
import com.compraya.asignacion7.observer.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class EcommerceServiceAsignacion7 {
    private ConfiguracionSistema configSingleton;
    private GestorNotificaciones gestorNotificaciones;
    private List<Item> productos;
    private List<Usuario> usuarios;
    private List<Pedido> pedidos;
    private int contadorPedidoId = 1001;

    public EcommerceServiceAsignacion7() {
        this.configSingleton = ConfiguracionSistema.getInstancia();
        this.gestorNotificaciones = new GestorNotificaciones();
        this.productos = new ArrayList<>();
        this.usuarios = new ArrayList<>();
        this.pedidos = new ArrayList<>();

        inicializarObservadores();
        inicializarDatosConFabrica();
    }

    private void inicializarObservadores() {
        // Suscribir los tres observadores al sujeto de notificaciones
        gestorNotificaciones.suscribir(new NotificadorClienteEmail());
        gestorNotificaciones.suscribir(new NotificadorModuloInventario());
        gestorNotificaciones.suscribir(new NotificadorPanelUI());
    }

    private void inicializarDatosConFabrica() {
        Categoria elec = new Categoria(1, "Electrónica", "Dispositivos");
        Categoria software = new Categoria(2, "Software y Libros", "Descargas");

        // USO DEL PATRÓN FACTORY (FabricaEntidades) PARA CREAR PRODUCTOS
        Map<String, Object> paramsFisico = new HashMap<>();
        paramsFisico.put("pesoKg", 0.45);
        paramsFisico.put("dimensiones", "15x7x0.8 cm");
        paramsFisico.put("costoEnvioBase", 12000.0);

        Item pFisico = FabricaEntidades.crearProducto("FISICO", 1, "Smartphone X Pro", "Teléfono OLED", 1500000.0, 15, elec, paramsFisico);

        Map<String, Object> paramsDigital = new HashMap<>();
        paramsDigital.put("formatoArchivo", "MP4/ZIP");
        paramsDigital.put("tamanoMB", 2450.0);
        paramsDigital.put("urlDescarga", "https://cdn.compraya.com/cursos/java-oop");
        paramsDigital.put("licencia", "LIC-JAVA-2026-BIU");

        Item pDigital = FabricaEntidades.crearProducto("DIGITAL", 2, "Curso Java OOP Avanzado", "Masterclass POO", 120000.0, 999, software, paramsDigital);

        productos.add(pFisico);
        productos.add(pDigital);

        // USO DEL PATRÓN FACTORY PARA CREAR USUARIOS
        Usuario clienteDemo = FabricaEntidades.crearUsuario("CLIENTE", 1, "Alberto López", "alberto.lopez@example.com", "pass1234", null);

        Map<String, Object> paramsAdmin = new HashMap<>();
        paramsAdmin.put("nivelAcceso", "SUPERADMIN");
        paramsAdmin.put("departamento", "TECNOLOGIA");

        Usuario adminDemo = FabricaEntidades.crearUsuario("ADMINISTRADOR", 2, "José Requeno", "admin.jose@compraya.com", "admin2026", paramsAdmin);

        usuarios.add(clienteDemo);
        usuarios.add(adminDemo);
    }

    public Optional<Usuario> autenticarUsuario(String email, String pass) {
        return usuarios.stream().filter(u -> u.getEmail().equalsIgnoreCase(email) && u.iniciarSesion(pass)).findFirst();
    }

    public Pedido realizarCheckout(Cliente cliente, DireccionEnvio direccion) {
        Pedido pedido = Pedido.crearDesdeCarrito(contadorPedidoId++, cliente, direccion, gestorNotificaciones);
        if (pedido != null) {
            pedidos.add(pedido);
            // Cambiar estado a PAGADO y notificar a los observadores automáticos
            pedido.cambiarEstado("PAGADO", gestorNotificaciones);
            return pedido;
        }
        return null;
    }

    public ConfiguracionSistema getConfigSingleton() { return configSingleton; }
    public GestorNotificaciones getGestorNotificaciones() { return gestorNotificaciones; }
    public List<Item> getProductos() { return productos; }
    public List<Pedido> getPedidos() { return pedidos; }
}
