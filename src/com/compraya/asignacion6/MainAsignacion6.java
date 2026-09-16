package com.compraya.asignacion6;

import com.compraya.asignacion6.inventory.GestorInventarioDigital;
import com.compraya.asignacion6.inventory.GestorInventarioFisico;
import com.compraya.asignacion6.model.*;
import com.compraya.asignacion6.payment.*;
import com.compraya.asignacion6.service.EcommerceServiceAsignacion6;

import java.util.List;

/**
 * Aplicación de prueba específica para la ASIGNACIÓN NO. 6.
 * Demuestra la IMPLEMENTACIÓN DE CLASES ABSTRACTAS PARA LA GESTIÓN DE INVENTARIO (GestorInventarioFisico y GestorInventarioDigital)
 * y el USO DE INTERFACES PARA PROCESOS DE PAGO (ProcesoPago con PagoTarjeta, PagoPayPal y PagoPSE).
 */
public class MainAsignacion6 {

    public static void main(String[] args) {
        imprimirEncabezado();

        EcommerceServiceAsignacion6 service = new EcommerceServiceAsignacion6();
        List<Item> catalogo = service.getProductos();

        // =========================================================================
        // 1. DEMOSTRACIÓN DE CLASES ABSTRACTAS EN GESTIÓN DE INVENTARIO
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 1. GESTIÓN ABSTRACTA DE INVENTARIOS (GestorInventario)             ");
        System.out.println("====================================================================");

        GestorInventarioFisico gFisico = service.getGestorFisico();
        GestorInventarioDigital gDigital = service.getGestorDigital();

        System.out.println("[INVENTARIO FÍSICO BODEGA]");
        gFisico.actualizarStock(1, 20); // Actualizar stock de Smartphone
        gFisico.generarReporteInventario();

        System.out.println("\n[INVENTARIO DIGITAL LICENCIAS NUBE]");
        gDigital.actualizarStock(3, 1500); // Actualizar cupo de licencias
        gDigital.generarReporteInventario();


        // =========================================================================
        // 2. DEMOSTRACIÓN DE INTERFAZ PROCESO PAGO (PagoTarjeta, PagoPayPal, PagoPSE)
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 2. PROCESOS DE PAGO POLIMÓRFICOS MEDIANTE LA INTERFAZ ProcesoPago  ");
        System.out.println("====================================================================");

        ProcesoPago pagoTarjeta = new PagoTarjeta("4532112233445566", "Alberto López", "VISA");
        ProcesoPago pagoPayPal  = new PagoPayPal("alberto.lopez@paypal.com");
        ProcesoPago pagoPSE     = new PagoPSE("Bancolombia", "PERSONA_NATURAL", "1018293847");

        System.out.println("[PRUEBA PASARELA 1: INTERFAZ ProcesoPago -> PagoTarjeta]");
        pagoTarjeta.iniciarPago(150000.0, "REF-TEST-001");
        pagoTarjeta.verificarPago("TXN-CARD-991");
        pagoTarjeta.confirmarPago("TXN-CARD-991");

        System.out.println("\n[PRUEBA PASARELA 2: INTERFAZ ProcesoPago -> PagoPayPal]");
        pagoPayPal.iniciarPago(85000.0, "REF-TEST-002");
        pagoPayPal.verificarPago("TXN-PAYPAL-882");
        pagoPayPal.confirmarPago("TXN-PAYPAL-882");

        System.out.println("\n[PRUEBA PASARELA 3: INTERFAZ ProcesoPago -> PagoPSE]");
        pagoPSE.iniciarPago(220000.0, "REF-TEST-003");
        pagoPSE.verificarPago("TXN-PSE-773");
        pagoPSE.confirmarPago("TXN-PSE-773");


        // =========================================================================
        // 3. FLUJO COMPLETO DE COMPRA Y FACTURACIÓN (ASIGNACIÓN 6)
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 3. COMPRA EN CARRITO Y CHECKOUT CON PASARELA POLIMÓRFICA           ");
        System.out.println("====================================================================");

        Cliente cliente = (Cliente) service.autenticarUsuario("alberto.lopez@example.com", "pass1234").orElseThrow();
        Carrito carrito = cliente.getCarrito();

        carrito.agregarProducto(catalogo.get(0), 1); // Smartphone X Pro ($1.500.000)
        carrito.agregarProducto(catalogo.get(2), 1); // Curso Java OOP ($120.000)

        System.out.println(carrito);

        DireccionEnvio direccion = new DireccionEnvio("Carrera 15 #93-60", "Bogotá", "Cundinamarca", "110221", "Colombia");

        System.out.println("[EJECUTANDO CHECKOUT USANDO INTERFAZ ProcesoPago (PagoPSE)]");
        Pedido pedidoConfirmado = service.realizarCheckout(cliente, direccion, pagoPSE);

        if (pedidoConfirmado != null) {
            System.out.println("\n" + pedidoConfirmado);
        }

        System.out.println("\n====================================================================");
        System.out.println("  ¡DEMOSTRACIÓN DE LA ASIGNACIÓN NO. 6 COMPLETADA EXITOSAMENTE!    ");
        System.out.println("====================================================================\n");
    }

    private static void imprimirEncabezado() {
        System.out.println("====================================================================");
        System.out.println("          PLATAFORMA E-COMMERCE \"COMPRAYA\" - ASIGNACIÓN 6          ");
        System.out.println("       Implementación de Interfaces y Clases Abstractas en Java     ");
        System.out.println("====================================================================");
        System.out.println("Estudiante: Alberto Alfonso López Pereira");
        System.out.println("Docente:    Dr. José Ignacio Requeno Jarabo");
        System.out.println("Curso:      CSE6041 – Object-Oriented Programming (BIU)");
        System.out.println("====================================================================");
    }
}
