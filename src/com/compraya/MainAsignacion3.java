package com.compraya;

import com.compraya.model.*;
import com.compraya.service.EcommerceService;

import java.util.List;

/**
 * Aplicación de prueba específica para la ASIGNACIÓN NO. 3.
 * Demuestra de forma exhaustiva la HERENCIA y ESPECIALIZACIÓN en Producto (Físico vs Digital)
 * y Usuario (Cliente vs Administrador).
 */
public class MainAsignacion3 {

    public static void main(String[] args) {
        imprimirEncabezado();

        EcommerceService service = new EcommerceService();

        // =========================================================================
        // 1. DEMOSTRACIÓN DE HERENCIA Y POLIMORFISMO EN 'PRODUCTO'
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 1. ESPECIALIZACIÓN DE PRODUCTO (ProductoFisico y ProductoDigital) ");
        System.out.println("====================================================================");

        List<Producto> catalogo = service.getProductos();

        System.out.println("[CATÁLOGO DIVERSISFICADO CON HERENCIA]");
        for (Producto prod : catalogo) {
            System.out.println("  • " + prod.getNombre() + " -> $" + String.format("%.2f", prod.getPrecio()));
            System.out.println("    └ Detalles Polimórficos: " + prod.obtenerDetallesEspecificos());

            if (prod instanceof ProductoFisico) {
                ProductoFisico pf = (ProductoFisico) prod;
                double fleteBogota = pf.calcularCostoEnvio("Cundinamarca");
                double fleteSanAndres = pf.calcularCostoEnvio("San Andrés");
                System.out.printf("    └ [FÍSICO] Flete Bogotá: $%.2f | Flete San Andrés: $%.2f\n", fleteBogota, fleteSanAndres);
            } else if (prod instanceof ProductoDigital) {
                ProductoDigital pd = (ProductoDigital) prod;
                System.out.println("    └ [DIGITAL] Enlace de descarga generado: " + pd.generarEnlaceDescarga());
            }
            System.out.println();
        }

        // =========================================================================
        // 2. DEMOSTRACIÓN DE HERENCIA Y ESPECIALIZACIÓN EN 'USUARIO'
        // =========================================================================
        System.out.println("====================================================================");
        System.out.println(" 2. ESPECIALIZACIÓN DE USUARIO (Cliente vs Administrador)          ");
        System.out.println("====================================================================");

        Cliente cliente = (Cliente) service.autenticarUsuario("alberto.lopez@example.com", "pass1234").orElseThrow();
        Administrador admin = (Administrador) service.autenticarUsuario("admin.jose@compraya.com", "admin2026").orElseThrow();

        System.out.println("[CLIENTE ESPECIALIZADO]");
        System.out.println("  • " + cliente);

        System.out.println("\n[RECOMENDACIONES BASADAS EN PREFERENCIAS DEL CLIENTE]");
        List<Producto> recomendados = cliente.obtenerRecomendaciones(catalogo);
        for (Producto rec : recomendados) {
            System.out.println("  -> Recomendación para " + cliente.getNombre() + ": " + rec.getNombre() + " (" + rec.getCategoria().getNombre() + ")");
        }

        System.out.println("\n[ADMINISTRADOR ESPECIALIZADO - GESTIÓN DE PROMOCIONES]");
        System.out.println("  • " + admin);

        Producto prodCurso = service.buscarProductosPorNombre("Curso Java").stream().findFirst()
                .orElseGet(() -> catalogo.stream().filter(p -> p instanceof ProductoDigital).findFirst().orElse(catalogo.get(0)));
        admin.establecerPromocion(prodCurso, 15.0); // 15% Descuento por el Admin
        admin.gestionarInventario(catalogo.get(0), 20); // Actualiza stock del Smartphone


        // =========================================================================
        // 3. CARRITO MEZCLADO Y FLUJO DE TRANSACCIÓN COMPLETO
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 3. COMPRA MIXTA EN CARRITO (PRODUCTOS FÍSICOS + DIGITALES)        ");
        System.out.println("====================================================================");

        Carrito cart = cliente.getCarrito();

        ProductoFisico pFisico = (ProductoFisico) catalogo.get(0);
        ProductoDigital pDigital = (ProductoDigital) prodCurso;

        System.out.println("[AÑADIENDO PRODUCTO FÍSICO Y DIGITAL AL CARRITO]");
        cart.agregarProducto(pFisico, 1);
        cart.agregarProducto(pDigital, 1);

        System.out.println(cart);

        DireccionEnvio dir = new DireccionEnvio("Calle 100 #15-30", "Bogotá", "Cundinamarca", "110221", "Colombia");
        System.out.println("[DIRECCIÓN REGISTRADA] " + dir);

        System.out.println("\n[PROCESANDO CHECKOUT]");
        Pedido pedido = service.realizarCheckout(cliente, dir, "PSE");

        if (pedido != null) {
            System.out.println("\n" + pedido);
            System.out.println("\n[ENTREGA Y LOGÍSTICA DIFERENCIADA EN EL PEDIDO]");
            for (ItemPedido item : pedido.getItems()) {
                Producto prodOriginal = service.buscarProductoPorId(item.getProductoId()).orElse(null);
                if (prodOriginal != null && !prodOriginal.requiereEnvioFisico()) {
                    ProductoDigital pd = (ProductoDigital) prodOriginal;
                    System.out.println("  ✓ Producto Digital '" + pd.getNombre() + "' entregado de inmediato -> URL: " + pd.generarEnlaceDescarga());
                } else {
                    System.out.println("  🚚 Producto Físico '" + item.getNombreProducto() + "' despachado a la transportadora hacia: " + dir.getCiudad());
                }
            }
        }


        // =========================================================================
        // 4. REPORTE FINAL DE ADMINISTRACIÓN
        // =========================================================================
        System.out.println("\n====================================================================");
        System.out.println(" 4. REPORTE DE VENTAS GENERADO POR EL ADMINISTRADOR                 ");
        System.out.println("====================================================================");

        admin.generarReporteVentas(service.calcularTotalVentasPlataforma(), service.getPedidos().size());

        System.out.println("\n====================================================================");
        System.out.println("  ¡PRUEBA DE HERENCIA Y ESPECIALIZACIÓN COMPLETADA CON ÉXITO!      ");
        System.out.println("====================================================================\n");
    }

    private static void imprimirEncabezado() {
        System.out.println("====================================================================");
        System.out.println("          PLATAFORMA E-COMMERCE \"COMPRAYA\" - ASIGNACIÓN 3          ");
        System.out.println("        Especialización mediante Herencia de Clases en Java         ");
        System.out.println("====================================================================");
        System.out.println("Estudiante: Alberto Alfonso López Pereira");
        System.out.println("Docente:    Dr. José Ignacio Requeno Jarabo");
        System.out.println("Curso:      CSE6041 – Object-Oriented Programming (BIU)");
        System.out.println("====================================================================");
    }
}
