package com.compraya.asignacion5;

import com.compraya.asignacion5.model.*;
import com.compraya.asignacion5.service.EcommerceServiceAsignacion5;

import java.util.List;

/**
 * Aplicación de prueba específica para la ASIGNACIÓN NO. 5.
 * Demuestra ABSTRACCIÓN AVANZADA (Clase Abstracta Item e Interfaces)
 * y ENCAPSULAMIENTO ESTRICTO CON VALIDACIÓN DE DATOS EN MUTADORES (Setters).
 */
public class MainAsignacion5 {

    public static void main(String[] args) {
        imprimirEncabezado();

        EcommerceServiceAsignacion5 service = new EcommerceServiceAsignacion5();
        List<Item> catalogo = service.getProductos();

        // =========================================================================
        // 1. DEMOSTRACIÓN DE ABSTRACCIÓN AVANZADA (Clase Base Abstracta Item)
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 1. ABSTRACCIÓN AVANZADA: CLASE BASE ABSTRACTA 'ITEM' E INTERFACES  ");
        System.out.println("====================================================================");

        System.out.println("[CATÁLOGO PROCESADO MEDIANTE ABSTRACCIÓN DE ITEM]");
        for (Item item : catalogo) {
            item.mostrarDetalle(); // Invocación del método abstracto obligatorio implementado por subclases

            if (item instanceof Despachable) {
                Despachable d = (Despachable) item;
                System.out.printf("   └ [INTERFACE Despachable] Peso: %.2f kg | Flete Bogotá: $%.2f\n",
                        d.getPesoKg(), d.calcularCostoEnvio("Cundinamarca"));
            }
            if (item instanceof Licenciable) {
                Licenciable l = (Licenciable) item;
                System.out.println("   └ [INTERFACE Licenciable] Licencia: " + l.getLicencia() + " | Token: " + l.generarEnlaceDescarga());
            }
            System.out.println();
        }

        // =========================================================================
        // 2. DEMOSTRACIÓN DE ENCAPSULAMIENTO ESTRICTO Y VALIDACIONES EN SETTERS
        // =========================================================================
        System.out.println("====================================================================");
        System.out.println(" 2. ENCAPSULAMIENTO ESTRICTO Y VALIDACIÓN DE DATOS EN MUTADORES     ");
        System.out.println("====================================================================");

        Producto pTest = (Producto) catalogo.get(0);
        Usuario uTest = new Usuario();

        System.out.println("[PRUEBA 2.1] Asignación de precio y stock válidos:");
        try {
            pTest.setPrecio(1450000.0);
            pTest.setStock(25);
            System.out.println("   ✓ Precio actualizado a: $" + String.format("%.2f", pTest.getPrecio()));
            System.out.println("   ✓ Stock actualizado a: " + pTest.getStock() + " unidades");
        } catch (IllegalArgumentException e) {
            System.out.println("   ❌ Error inesperado: " + e.getMessage());
        }

        System.out.println("\n[PRUEBA 2.2 - CAPTURA DE EXCEPCIÓN] Asignación de precio negativo (-500.0):");
        try {
            pTest.setPrecio(-500.0);
        } catch (IllegalArgumentException e) {
            System.out.println("   ⚠️ Excepción capturada exitosamente: " + e.getMessage());
        }

        System.out.println("\n[PRUEBA 2.3 - CAPTURA DE EXCEPCIÓN] Asignación de stock negativo (-10):");
        try {
            pTest.setStock(-10);
        } catch (IllegalArgumentException e) {
            System.out.println("   ⚠️ Excepción capturada exitosamente: " + e.getMessage());
        }

        System.out.println("\n[PRUEBA 2.4 - CAPTURA DE EXCEPCIÓN] Asignación de correo electrónico inválido ('correo-sin-arroba'):");
        try {
            uTest.setEmail("correo-sin-arroba");
        } catch (IllegalArgumentException e) {
            System.out.println("   ⚠️ Excepción capturada exitosamente: " + e.getMessage());
        }

        System.out.println("\n[PRUEBA 2.5 - CAPTURA DE EXCEPCIÓN] Asignación de contraseña débil ('123'):");
        try {
            uTest.setPassword("123");
        } catch (IllegalArgumentException e) {
            System.out.println("   ⚠️ Excepción capturada exitosamente: " + e.getMessage());
        }

        System.out.println("\n[PRUEBA 2.6] Asignación exitosa de credenciales válidas:");
        try {
            uTest.setNombre("Carlos Alberto López");
            uTest.setEmail("carlos.lopez.2026@empresa.com");
            uTest.setPassword("ClaveSegura2026!");
            System.out.println("   ✓ Nombre:  " + uTest.getNombre());
            System.out.println("   ✓ Email:   " + uTest.getEmail());
            System.out.println("   ✓ Estado:  Usuario encapsulado y validado correctamente");
        } catch (IllegalArgumentException e) {
            System.out.println("   ❌ Error inesperado: " + e.getMessage());
        }

        // =========================================================================
        // 3. CARRITO ENCAPSULADO Y CHECKOUT FINAL
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 3. OPERACIÓN DE CARRITO ENCAPSULADO Y CHECKOUT (ASIGNACIÓN 5)     ");
        System.out.println("====================================================================");

        Cliente cliente = (Cliente) service.autenticarUsuario("alberto.lopez@example.com", "pass1234").orElseThrow();
        Carrito cart = cliente.getCarrito();

        cart.agregarProducto(catalogo.get(0), 1); // Smartphone
        cart.agregarProducto(catalogo.get(2), 2); // Curso Java

        System.out.println(cart);

        DireccionEnvio dir = new DireccionEnvio("Calle 127 #19-45", "Bogotá", "Cundinamarca", "110121", "Colombia");
        Pedido pedido = service.realizarCheckout(cliente, dir, "TARJETA_CREDITO");

        if (pedido != null) {
            System.out.println("\n" + pedido);
        }

        Administrador admin = (Administrador) service.autenticarUsuario("admin.jose@compraya.com", "admin2026").orElseThrow();
        admin.generarReporteVentas(service.calcularTotalVentasPlataforma(), service.getPedidos().size());

        System.out.println("\n====================================================================");
        System.out.println("  ¡PRUEBA DE ENCAPSULAMIENTO Y ABSTRACCIÓN COMPLETADA CON ÉXITO!   ");
        System.out.println("====================================================================\n");
    }

    private static void imprimirEncabezado() {
        System.out.println("====================================================================");
        System.out.println("          PLATAFORMA E-COMMERCE \"COMPRAYA\" - ASIGNACIÓN 5          ");
        System.out.println("         Aplicación de Encapsulamiento Estricto y Abstracción       ");
        System.out.println("====================================================================");
        System.out.println("Estudiante: Alberto Alfonso López Pereira");
        System.out.println("Docente:    Dr. José Ignacio Requeno Jarabo");
        System.out.println("Curso:      CSE6041 – Object-Oriented Programming (BIU)");
        System.out.println("====================================================================");
    }
}
