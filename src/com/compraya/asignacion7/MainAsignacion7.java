package com.compraya.asignacion7;

import com.compraya.asignacion7.config.ConfiguracionSistema;
import com.compraya.asignacion7.factory.FabricaEntidades;
import com.compraya.asignacion7.model.*;
import com.compraya.asignacion7.observer.*;
import com.compraya.asignacion7.service.EcommerceServiceAsignacion7;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Aplicación de prueba específica para la ASIGNACIÓN NO. 7.
 * Demuestra la implementación de los tres patrones de diseño del GoF:
 * 1. SINGLETON (ConfiguracionSistema - Instancia única global hilo-segura)
 * 2. FACTORY (FabricaEntidades - Creación desacoplada de Productos y Usuarios)
 * 3. OBSERVER (GestorNotificaciones - Sistema de publicación y suscripción de eventos)
 */
public class MainAsignacion7 {

    public static void main(String[] args) {
        imprimirEncabezado();

        // =========================================================================
        // 1. DEMOSTRACIÓN DEL PATRÓN SINGLETON (ConfiguracionSistema)
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 1. DEMOSTRACIÓN DEL PATRÓN SINGLETON (ConfiguracionSistema)        ");
        System.out.println("====================================================================");

        ConfiguracionSistema config1 = ConfiguracionSistema.getInstancia();
        ConfiguracionSistema config2 = ConfiguracionSistema.getInstancia();

        config1.mostrarConfiguracionGlobal();

        System.out.println("\n[VERIFICACIÓN SINGLETON]");
        System.out.println("   - Hash de la referencia config1: " + System.identityHashCode(config1));
        System.out.println("   - Hash de la referencia config2: " + System.identityHashCode(config2));
        boolean esMismaInstancia = (config1 == config2);
        System.out.println("   -> ¿(config1 == config2) es EXACTAMENTE la misma instancia?: " + (esMismaInstancia ? "SI (Singleton Garantizado)" : "NO"));


        // =========================================================================
        // 2. DEMOSTRACIÓN DEL PATRÓN FACTORY (FabricaEntidades)
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 2. DEMOSTRACIÓN DEL PATRÓN FACTORY (FabricaEntidades)             ");
        System.out.println("====================================================================");

        Categoria catAccesorios = new Categoria(5, "Gadgets & Accesorios", "Nuevas tecnologías");

        Map<String, Object> paramsTablet = new HashMap<>();
        paramsTablet.put("pesoKg", 0.65);
        paramsTablet.put("dimensiones", "25x17x0.6 cm");
        paramsTablet.put("costoEnvioBase", 14000.0);

        Item tabletFisica = FabricaEntidades.crearProducto("FISICO", 10, "Tablet Pro 11", "Pantalla 120Hz", 1800000.0, 8, catAccesorios, paramsTablet);

        Map<String, Object> paramsAudioBook = new HashMap<>();
        paramsAudioBook.put("formatoArchivo", "MP3/M4B");
        paramsAudioBook.put("tamanoMB", 450.0);
        paramsAudioBook.put("urlDescarga", "https://cdn.compraya.com/audio/clean-code");
        paramsAudioBook.put("licencia", "LIC-AUDIO-2026");

        Item audioBookDigital = FabricaEntidades.crearProducto("DIGITAL", 11, "Audiobook Clean Code", "Narración profesional", 65000.0, 999, catAccesorios, paramsAudioBook);

        System.out.println("\n[PRODUCTOS CREADOS DESDE LA FÁBRICA]");
        tabletFisica.mostrarDetalle();
        audioBookDigital.mostrarDetalle();


        // =========================================================================
        // 3. DEMOSTRACIÓN DEL PATRÓN OBSERVER (GestorNotificaciones)
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 3. DEMOSTRACIÓN DEL PATRÓN OBSERVER (Notificaciones de Eventos)   ");
        System.out.println("====================================================================");

        EcommerceServiceAsignacion7 service = new EcommerceServiceAsignacion7();
        Cliente cliente = (Cliente) service.autenticarUsuario("alberto.lopez@example.com", "pass1234").orElseThrow();

        Carrito carrito = cliente.getCarrito();
        carrito.agregarProducto(service.getProductos().get(0), 1);
        carrito.agregarProducto(service.getProductos().get(1), 1);

        System.out.println(carrito);

        DireccionEnvio dir = new DireccionEnvio("Carrera 7 #71-21", "Bogotá", "Cundinamarca", "110231", "Colombia");

        System.out.println("[REALIZANDO CHECKOUT - SE ACTIVARÁ LA NOTIFICACIÓN DEL OBSERVER]");
        Pedido pedidoNotificado = service.realizarCheckout(cliente, dir);

        if (pedidoNotificado != null) {
            System.out.println("\n" + pedidoNotificado);
        }

        System.out.println("\n[DISPARANDO EVENTO ADICIONAL DE STOCK BAJO MEDIANTE OBSERVER]");
        service.getGestorNotificaciones().notificarObservadores("ALERTA_STOCK_BAJO", tabletFisica);

        System.out.println("\n====================================================================");
        System.out.println("  ¡DEMOSTRACIÓN DE PATRONES DE DISEÑO COMPLETADA EXITOSAMENTE!      ");
        System.out.println("====================================================================\n");
    }

    private static void imprimirEncabezado() {
        System.out.println("====================================================================");
        System.out.println("          PLATAFORMA E-COMMERCE \"COMPRAYA\" - ASIGNACIÓN 7          ");
        System.out.println("        Implementación de Patrones Singleton, Factory y Observer    ");
        System.out.println("====================================================================");
        System.out.println("Estudiante: Alberto Alfonso López Pereira");
        System.out.println("Docente:    Dr. José Ignacio Requeno Jarabo");
        System.out.println("Curso:      CSE6041 – Object-Oriented Programming (BIU)");
        System.out.println("====================================================================");
    }
}
