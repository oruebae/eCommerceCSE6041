package com.compraya;

import com.compraya.model.*;
import com.compraya.service.EcommerceService;

import java.util.List;
import java.util.Optional;

/**
 * Clase principal que demuestra el funcionamiento integral del sistema CompraYa
 * y ejecuta pruebas unitarias/integradas de las clases Producto, Usuario y Carrito.
 */
public class Main {

    public static void main(String[] args) {
        imprimirEncabezado();

        EcommerceService service = new EcommerceService();

        // -------------------------------------------------------------
        // DEMOSTRACIÓN 1: Verificación de la Clase Producto
        // -------------------------------------------------------------
        System.out.println("\n=======================================================");
        System.out.println("  1. DEMOSTRACIÓN Y PRUEBA DE LA CLASE 'PRODUCTO'");
        System.out.println("=======================================================");

        Categoria catTecno = service.getCategorias().get(0);
        Producto p1 = new Producto(101, "Laptop Gamer Ultra", "Intel i9, 32GB RAM, RTX 4080", 6500000.0, 10, catTecno);

        System.out.println("[CREACIÓN] Objeto Producto inicializado exitosamente:");
        System.out.println("   -> " + p1);

        System.out.println("\n[PROPIEDADES] Invocación de Getters:");
        System.out.println("   - ID: " + p1.getId());
        System.out.println("   - Nombre: " + p1.getNombre());
        System.out.println("   - Descripción: " + p1.getDescripcion());
        System.out.println("   - Precio: $" + String.format("%.2f", p1.getPrecio()));
        System.out.println("   - Stock disponible: " + p1.getStock());

        System.out.println("\n[MÉTODOS DE NEGOCIO]");
        System.out.println("   * Aplicando 10% de descuento...");
        p1.aplicarDescuento(10.0);
        System.out.println("   -> Nuevo Precio tras descuento: $" + String.format("%.2f", p1.getPrecio()));

        System.out.println("   * Actualizando stock (-2 unidades vendidas)...");
        p1.actualizarStock(-2);
        System.out.println("   -> Nuevo Stock en inventario: " + p1.getStock());


        // -------------------------------------------------------------
        // DEMOSTRACIÓN 2: Verificación de la Clase Usuario (y Herencia)
        // -------------------------------------------------------------
        System.out.println("\n=======================================================");
        System.out.println("  2. DEMOSTRACIÓN Y PRUEBA DE LA CLASE 'USUARIO'");
        System.out.println("=======================================================");

        Usuario usrGeneral = new Usuario(201, "Carlos Ramírez", "carlos.ramirez@mail.com", "passKey2026");
        System.out.println("[CREACIÓN] Objeto Usuario inicializado:");
        System.out.println("   -> " + usrGeneral);

        System.out.println("\n[AUTENTICACIÓN] Prueba de inicio de sesión:");
        boolean autValida = usrGeneral.iniciarSesion("passKey2026");
        boolean autInvalida = usrGeneral.iniciarSesion("claveErronea");
        System.out.println("   - ¿Autenticación con clave correcta?: " + (autValida ? "ÉXITO" : "FALLO"));
        System.out.println("   - ¿Autenticación con clave errónea?:  " + (autInvalida ? "ÉXITO" : "FALLO (Esperado)"));

        System.out.println("\n[GESTIÓN DE PERFIL] Actualizando nombre y correo...");
        usrGeneral.actualizarPerfil("Carlos A. Ramírez", "carlos.actualizado@mail.com");
        System.out.println("   -> Perfil Actualizado: " + usrGeneral.getNombre() + " (" + usrGeneral.getEmail() + ")");

        System.out.println("\n[HERENCIA] Creando Cliente y Administrador:");
        Cliente clientePrueba = (Cliente) service.autenticarUsuario("alberto.lopez@example.com", "pass1234").orElseThrow();
        Administrador adminPrueba = (Administrador) service.autenticarUsuario("admin.jose@compraya.com", "admin2026").orElseThrow();

        System.out.println("   - " + clientePrueba);
        System.out.println("   - " + adminPrueba);

        System.out.println("\n[DESTRUCTOR/LIMPIEZA LOGICA] Ejecutando destruirSesion():");
        usrGeneral.destruirSesion();
        System.out.println("   -> Estado activo tras destrucción: " + usrGeneral.isActivo());


        // -------------------------------------------------------------
        // DEMOSTRACIÓN 3: Verificación de la Clase Carrito de Compras
        // -------------------------------------------------------------
        System.out.println("\n=======================================================");
        System.out.println("  3. DEMOSTRACIÓN Y PRUEBA DE LA CLASE 'CARRITO'");
        System.out.println("=======================================================");

        Carrito carrito = clientePrueba.getCarrito();
        System.out.println("[ESTADO INICIAL]");
        System.out.println(carrito);

        List<Producto> catalogo = service.getProductos();
        Producto pAudifonos = catalogo.get(1); // Audífonos ($250,000)
        Producto pCafetera  = catalogo.get(2); // Cafetera ($450,000)
        Producto pZapatillas= catalogo.get(5); // Zapatillas ($220,000)

        System.out.println("[AÑADIR PRODUCTOS]");
        System.out.println("   * Agregando 2 Audífonos Bluetooth...");
        carrito.agregarProducto(pAudifonos, 2);
        System.out.println("   * Agregando 1 Cafetera Express...");
        carrito.agregarProducto(pCafetera, 1);
        System.out.println("   * Agregando 1 Zapatillas Deportivas...");
        carrito.agregarProducto(pZapatillas, 1);

        System.out.println("\n[CARRITO TRAS ADICIONES]");
        System.out.println(carrito);

        System.out.println("[MODIFICAR CANTIDAD]");
        System.out.println("   * Modificando cantidad de Audífonos a 3...");
        carrito.modificarCantidad(pAudifonos.getId(), 3);
        System.out.println("   -> Total recalculado: $" + String.format("%.2f", carrito.calcularTotal()));

        System.out.println("\n[REMOVER PRODUCTO]");
        System.out.println("   * Removiendo Zapatillas Deportivas (ID " + pZapatillas.getId() + ")...");
        boolean removido = carrito.removerProducto(pZapatillas.getId());
        System.out.println("   -> ¿Producto removido con éxito?: " + removido);

        System.out.println("\n[CARRITO ACTUALIZADO]");
        System.out.println(carrito);


        // -------------------------------------------------------------
        // DEMOSTRACIÓN 4: Flujo Completo de Transacción y Generación de Pedido
        // -------------------------------------------------------------
        System.out.println("\n=======================================================");
        System.out.println("  4. FLUJO COMPLETO: CHECKOUT, IMPUESTOS Y PAGO");
        System.out.println("=======================================================");

        DireccionEnvio direccion = new DireccionEnvio("Carrera 7 #45-12", "Bogotá", "Cundinamarca", "110111", "Colombia");
        System.out.println("[DIRECCIÓN DE ENVÍO] " + direccion);

        System.out.println("\n[PROCESANDO CHECKOUT Y PASARELA DE PAGO]");
        Pedido pedidoGenerado = service.realizarCheckout(clientePrueba, direccion, "TARJETA_CREDITO");

        if (pedidoGenerado != null) {
            System.out.println("\n[PEDIDO GENERADO EXITOSAMENTE]");
            System.out.println(pedidoGenerado);
        }

        System.out.println("\n[VERIFICACIÓN DE IMPACTO EN INVENTARIO Y CARRITO]");
        System.out.println("   - Stock restante de Audífonos (Inicial: 30): " + pAudifonos.getStock());
        System.out.println("   - Stock restante de Cafetera  (Inicial: 10): " + pCafetera.getStock());
        System.out.println("   - Estado del carrito del cliente: " + (clientePrueba.getCarrito().getItems().isEmpty() ? "Vacío (Correcto)" : "No vacío"));


        // -------------------------------------------------------------
        // DEMOSTRACIÓN 5: Gestión por el Administrador y Reporte
        // -------------------------------------------------------------
        System.out.println("\n=======================================================");
        System.out.println("  5. MÓDULO DE ADMINISTRACIÓN Y REPORTES DE VENTAS");
        System.out.println("=======================================================");

        adminPrueba.gestionarInventario(p1, 25);
        double totalVentas = service.calcularTotalVentasPlataforma();
        adminPrueba.generarReporte(totalVentas, service.getPedidos().size());

        System.out.println("\n=======================================================");
        System.out.println("  ¡TODAS LAS PRUEBAS Y REQUERIMIENTOS FUERON EJECUTADOS!");
        System.out.println("=======================================================\n");
    }

    private static void imprimirEncabezado() {
        System.out.println("====================================================================");
        System.out.println("               PLATAFORMA E-COMMERCE \"COMPRAYA\"                     ");
        System.out.println("        Demostración de Requerimientos POO (Curso CSE6041)          ");
        System.out.println("====================================================================");
        System.out.println("Estudiante: Alberto Alfonso López Pereira");
        System.out.println("Docente:    Dr. José Ignacio Requeno Jarabo");
        System.out.println("Institución: Broward International University (BIU)");
        System.out.println("====================================================================");
    }
}
