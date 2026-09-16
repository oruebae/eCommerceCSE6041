package com.compraya.asignacion8.main;

import com.compraya.asignacion8.exception.*;
import com.compraya.asignacion8.model.*;
import com.compraya.asignacion8.payment.PagoPayPal;
import com.compraya.asignacion8.payment.PagoTarjeta;
import com.compraya.asignacion8.service.EcommerceServiceAsignacion8;
import com.compraya.asignacion8.test.TestRunnerAsignacion8;

/**
 * Clase ejecutable principal para la Asignación No. 8.
 * Demuestra la suite de pruebas unitarias y el manejo de excepciones en tiempo de ejecución.
 * 
 * @author Alberto Alfonso López Pereira
 * @version 8.0.2026
 */
public class MainAsignacion8 {

    public static void main(String[] args) {
        System.out.println("====================================================================");
        System.out.println("          PLATAFORMA E-COMMERCE \"COMPRAYA\" - ASIGNACIÓN 8          ");
        System.out.println("     Manejo de Excepciones Personalizadas y Pruebas Unitarias     ");
        System.out.println("====================================================================");
        System.out.println("Estudiante: Alberto Alfonso López Pereira");
        System.out.println("Docente:    Dr. José Ignacio Requeno Jarabo");
        System.out.println("Curso:      CSE6041 – Object-Oriented Programming (BIU)");
        System.out.println("====================================================================\n");

        // STEP 1: Ejecutar Suite de Pruebas Unitarias
        boolean todasLasPruebasPasaron = TestRunnerAsignacion8.ejecutarTodasLasPruebas();
        if (!todasLasPruebasPasaron) {
            System.err.println("⚠️ ALERTA: Algunas pruebas unitarias fallaron. Revise el log.");
        }

        // STEP 2: Demostración Interactiva de Manejo Gracioso de Excepciones
        System.out.println("====================================================================");
        System.out.println(" 2. DEMOSTRACIÓN EN VIVO DE MANEJO Y RECUPERACIÓN DE EXCEPCIONES    ");
        System.out.println("====================================================================");

        EcommerceServiceAsignacion8 servicio = new EcommerceServiceAsignacion8();

        // Registrar productos en inventario
        ProductoFisico tv = new ProductoFisico(501, "Smart TV OLED 55\"", "4K UHD 120Hz", 4500000.0, 3, 14.5, "123x71 cm");
        ProductoDigital curso = new ProductoDigital(502, "Curso Testing & Clean Code", "Video Masterclass", 99000.0, 50, "MP4/HD", 3200.0);
        servicio.getInventario().registrarProducto(tv);
        servicio.getInventario().registrarProducto(curso);

        Cliente cliente = new Cliente(10, "Alberto López", "alberto.lopez@example.com", "Password2026", "Av. El Dorado #68-90");
        Cliente clienteSinPermiso = new Cliente(11, "Carlos Cliente", "carlos@example.com", "UserPass123", "Calle 10 #20-30");
        Administrador admin = new Administrador(1, "Ana Admin", "ana.admin@compraya.com", "AdminPass2026", "SUPERADMIN");

        Carrito carrito = new Carrito();

        // Caso A: Buscar producto inexistente en inventario (ProductoNoEncontradoException)
        System.out.println("\n[ESCENARIO A] Intentando agregar producto inexistente (ID: 9999):");
        servicio.agregarAlCarritoSeguro(carrito, 9999, 1);

        // Caso B: Solicitar más stock del disponible (InventarioInsuficienteException)
        System.out.println("\n[ESCENARIO B] Intentando agregar 10 unidades de Smart TV (Stock disponible: 3):");
        servicio.agregarAlCarritoSeguro(carrito, 501, 10);

        // Caso C: Agregar producto válido dentro del stock
        System.out.println("\n[ESCENARIO C] Agregando 2 unidades de Smart TV y 1 de Curso:");
        servicio.agregarAlCarritoSeguro(carrito, 501, 2);
        servicio.agregarAlCarritoSeguro(carrito, 502, 1);

        // Caso D: Fallo en pasarela principal con recuperación exitosa en pasarela secundaria (PagoFallidoException -> Fallback)
        System.out.println("\n[ESCENARIO D] Checkout con tarjeta sin cupo suficiente (Fallback a PayPal):");
        PagoTarjeta tarjetaSinCupo = new PagoTarjeta("4000123456789010", "Alberto López", 1000000.0); // Cupo 1M, compra vale > 9M
        PagoPayPal paypalConSaldo = new PagoPayPal("alberto@paypal.com", true);

        try {
            Pedido pedidoProcesado = servicio.procesarCompraConFallback(cliente, carrito, tarjetaSinCupo, paypalConSaldo);
            System.out.printf("   🎉 [COMPRA COMPLETADA CON ÉXITO] Pedido #%d estado: %s%n", 
                    pedidoProcesado.getId(), pedidoProcesado.getEstado());
        } catch (EcommerceException e) {
            System.err.printf("   ❌ Error crítico en checkout: %s%n", e.getMessage());
        }

        // Caso E: Intento de checkout con carrito vacío (CarritoVacioException)
        System.out.println("\n[ESCENARIO E] Intentando checkout con carrito recién vaciado:");
        try {
            servicio.procesarCompraConFallback(cliente, carrito, tarjetaSinCupo, paypalConSaldo);
        } catch (CarritoVacioException e) {
            System.err.printf("   [EXCEPCIÓN CAPTURADA CORRECTAMENTE] %s%n", e.toString());
        }

        // Caso F: Violación de Seguridad y Permisos (UsuarioNoAutorizadoException)
        System.out.println("\n[ESCENARIO F] Intento de actualización de inventario por parte de un Cliente:");
        try {
            servicio.modificarInventarioComoUsuario(clienteSinPermiso, 501, 100);
        } catch (UsuarioNoAutorizadoException e) {
            System.err.printf("   [ACCESO RECHAZADO] %s%n", e.toString());
        }

        System.out.println("\n[ESCENARIO G] Intentando la misma actualización con perfil Administrador:");
        servicio.modificarInventarioComoUsuario(admin, 501, 100);

        System.out.println("\n====================================================================");
        System.out.println("  ¡DEMOSTRACIÓN Y PRUEBAS DE ASIGNACIÓN 8 FINALIZADAS CON ÉXITO!    ");
        System.out.println("====================================================================\n");
    }
}
