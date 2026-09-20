package com.compraya;

import com.compraya.model.*;
import com.compraya.service.EcommerceService;

import java.util.List;

/**
 * Aplicación de prueba específica para la ASIGNACIÓN NO. 4.
 * Demuestra POLIMORFISMO DE INVOCACIÓN, SOBRECARGA DE MÉTODOS (Method Overloading)
 * y SOBREESCRITURA DE MÉTODOS (Method Overriding) en la plataforma CompraYa.
 */
public class MainAsignacion4 {

    public static void main(String[] args) {
        imprimirEncabezado();

        EcommerceService service = new EcommerceService();
        List<Producto> catalogo = service.getProductos();

        // =========================================================================
        // 1. DEMOSTRACIÓN DE SOBREESCRITURA DE MÉTODOS (METHOD OVERRIDING)
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 1. DEMOSTRACIÓN DE SOBREESCRITURA DE MÉTODOS (mostrarDetalle())  ");
        System.out.println("====================================================================");
        System.out.println("Se invoca el método sobrescrito mostrarDetalle() en diferentes tipos de productos:");

        // Invocación polimórfica de mostrarDetalle()
        for (Producto prod : catalogo) {
            prod.mostrarDetalle(); // Ejecuta la versión sobrescrita específica de cada subclase
            System.out.println();
        }

        // =========================================================================
        // 2. DEMOSTRACIÓN DE SOBRECARGA DE MÉTODOS (METHOD OVERLOADING) EN CARRITO
        // =========================================================================
        System.out.println("====================================================================");
        System.out.println(" 2. DEMOSTRACIÓN DE SOBRECARGA DE MÉTODOS (agregarProducto())     ");
        System.out.println("====================================================================");

        Cliente cliente = (Cliente) service.autenticarUsuario("alberto.lopez@example.com", "pass1234").orElseThrow();
        Carrito carrito = cliente.getCarrito();
        carrito.vaciarCarrito(); // Iniciar con carrito limpio

        System.out.println("[VARIACIÓN 1 - Overloading] agregarProducto(Producto p)");
        Producto pFisico1 = catalogo.get(0); // Smartphone X Pro
        boolean v1 = carrito.agregarProducto(pFisico1); // Agrega 1 unidad por defecto
        System.out.println("   -> ¿Agregado 1 unidad de '" + pFisico1.getNombre() + "'?: " + v1);

        System.out.println("\n[VARIACIÓN 2 - Overloading] agregarProducto(Producto p, int cantidad)");
        Producto pDigital1 = service.buscarProductosPorNombre("Curso Java").stream().findFirst()
                .orElseGet(() -> catalogo.stream().filter(p -> p instanceof ProductoDigital).findFirst().orElse(catalogo.get(0)));
        boolean v2 = carrito.agregarProducto(pDigital1, 2); // Agrega 2 unidades explícitas
        System.out.println("   -> ¿Agregadas 2 unidades de '" + pDigital1.getNombre() + "'?: " + v2);

        System.out.println("\n[VARIACIÓN 3 - Overloading] agregarProducto(int productoId, EcommerceService service)");
        boolean v3 = carrito.agregarProducto(3, service); // Búsqueda por ID 3 (Cafetera Express) -> 1 unidad por defecto
        System.out.println("   -> ¿Agregada 1 unidad por ID 3 (Cafetera Express)?: " + v3);

        System.out.println("\n[VARIACIÓN 4 - Overloading] agregarProducto(int productoId, int cantidad, EcommerceService service)");
        boolean v4 = carrito.agregarProducto(5, 3, service); // Búsqueda por ID 5 (E-Book) -> 3 unidades
        System.out.println("   -> ¿Agregadas 3 unidades por ID 5 (E-Book)?: " + v4);

        System.out.println("\n[VARIACIÓN 5 - Overloading] agregarProducto(String nombre, double precio, int cantidad, Categoria cat)");
        Categoria catModa = new Categoria(4, "Accesorios", "Artículos genéricos");
        boolean v5 = carrito.agregarProducto("Soporte Ergonómico Laptop", 85000.0, 1, catModa);
        System.out.println("   -> ¿Agregado producto dinámico por nombre y precio?: " + v5);

        System.out.println("\n[ESTADO DEL CARRITO TRAS SOBRECARGA]");
        System.out.println(carrito);

        System.out.println("[DEMOSTRACIÓN SOBRECARGA - removerProducto]");
        System.out.println("   * Removiendo por ID de producto (removerProducto(int id)...)");
        carrito.removerProducto(3); // Remueve Cafetera por ID

        System.out.println("   * Removiendo pasando objeto Producto (removerProducto(Producto p)...)");
        carrito.removerProducto(pFisico1); // Remueve Smartphone pasando el objeto

        System.out.println("\n[CARRITO TRAS REMOCIONES SOBRECARGADAS]");
        System.out.println(carrito);

        // =========================================================================
        // 3. TRATAMIENTO POLIMÓRFICO UNIFICADO (PROCESAMIENTO HETEROGÉNEO)
        // =========================================================================
        System.out.println("====================================================================");
        System.out.println(" 3. TRATAMIENTO POLIMÓRFICO DE OBJETOS DERIVADOS                  ");
        System.out.println("====================================================================");
        System.out.println("Procesando lista heterogénea de productos como objetos de la superclase Producto:");

        procesarProductosPolimorficos(catalogo);

        // =========================================================================
        // 4. CHECKOUT Y FINALIZACIÓN
        // =========================================================================
        System.out.println("====================================================================");
        System.out.println(" 4. CHECKOUT Y FACTURACIÓN CON IMPUESTOS (ASIGNACIÓN 4)            ");
        System.out.println("====================================================================");

        DireccionEnvio dir = new DireccionEnvio("Av. El Dorado #68-90", "Bogotá", "Cundinamarca", "110931", "Colombia");
        Pedido pedidoFinal = service.realizarCheckout(cliente, dir, "TARJETA_CREDITO");

        if (pedidoFinal != null) {
            System.out.println("\n" + pedidoFinal);
        }

        Administrador admin = (Administrador) service.autenticarUsuario("admin.jose@compraya.com", "admin2026").orElseThrow();
        admin.generarReporteVentas(service.calcularTotalVentasPlataforma(), service.getPedidos().size());

        System.out.println("\n====================================================================");
        System.out.println("  ¡PRUEBA DE POLIMORFISMO Y SOBRECARGA COMPLETADA EXITOSAMENTE!    ");
        System.out.println("====================================================================\n");
    }

    /**
     * Método procesador polimórfico que recibe objetos derivados (ProductoFisico, ProductoDigital)
     * tratándolos uniformemente como la clase base Producto.
     * 
     * @param productos Lista heterogénea de objetos Producto.
     */
    private static void procesarProductosPolimorficos(List<Producto> productos) {
        double valorTotalCatalogo = 0.0;
        int totalFisicos = 0;
        int totalDigitales = 0;

        for (Producto p : productos) {
            valorTotalCatalogo += p.getPrecio() * p.getStock();

            // Invocación polimórfica sin acoplamiento a subclase concreta
            if (p.requiereEnvioFisico()) {
                totalFisicos++;
            } else {
                totalDigitales++;
            }
            System.out.printf("  -> [PROCESADO POLIMÓRFICO] ID: %d | %-25s | Precio: $%.2f | EnvioFisico: %b\n",
                    p.getId(), p.getNombre(), p.getPrecio(), p.requiereEnvioFisico());
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.printf(" Resumen Polimórfico -> Físicos: %d | Digitales: %d | Valor Inventario: $%.2f\n",
                totalFisicos, totalDigitales, valorTotalCatalogo);
        System.out.println("--------------------------------------------------------------------");
    }

    private static void imprimirEncabezado() {
        System.out.println("====================================================================");
        System.out.println("          PLATAFORMA E-COMMERCE \"COMPRAYA\" - ASIGNACIÓN 4          ");
        System.out.println("         Demostración de Polimorfismo, Sobrecarga y Sobreescritura  ");
        System.out.println("====================================================================");
        System.out.println("Estudiante: Alberto Alfonso López Pereira");
        System.out.println("Docente:    Dr. José Ignacio Requeno Jarabo");
        System.out.println("Curso:      CSE6041 – Object-Oriented Programming (BIU)");
        System.out.println("====================================================================");
    }
}
