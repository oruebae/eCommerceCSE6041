package com.compraya.asignacion8.test;

import com.compraya.asignacion8.exception.InventarioInsuficienteException;
import com.compraya.asignacion8.exception.ProductoNoEncontradoException;
import com.compraya.asignacion8.exception.StockInvalidoException;
import com.compraya.asignacion8.inventory.GestorInventario;
import com.compraya.asignacion8.model.ProductoFisico;

public class InventarioTest {

    public static void ejecutarPruebas() {
        System.out.println("\n--- [SUITE] Pruebas Unitarias para Gestor de Inventario ---");

        GestorInventario gestor = new GestorInventario();
        ProductoFisico monitor = new ProductoFisico(301, "Monitor 27 pulgadas", "4K IPS", 1200000.0, 8, 4.5, "60x40 cm");

        // 1. Registro e inventariado
        gestor.registrarProducto(monitor);
        TestFramework.assertEquals(1, gestor.getCantidadTotalProductos(), "Producto registrado exitosamente en catálogo");
        TestFramework.assertEquals(8, gestor.getStockActual(301), "Stock registrado correctamente");

        // 2. Descuento de stock
        gestor.descontarStock(301, 3);
        TestFramework.assertEquals(5, gestor.getStockActual(301), "Stock descontado correctamente de 8 a 5");

        // 3. Excepción por Producto No Encontrado (ProductoNoEncontradoException)
        TestFramework.assertThrows(ProductoNoEncontradoException.class, () -> {
            gestor.buscarProductoPorId(9999);
        }, "Debe lanzar ProductoNoEncontradoException para ID inexistente");

        // 4. Excepción por Descontar Más del Stock Existente (InventarioInsuficienteException)
        TestFramework.assertThrows(InventarioInsuficienteException.class, () -> {
            gestor.descontarStock(301, 10);
        }, "Debe lanzar InventarioInsuficienteException si se intenta descontar 10 cuando solo quedan 5");

        // 5. Excepción por Actualizar Stock a Valor Negativo (StockInvalidoException)
        TestFramework.assertThrows(StockInvalidoException.class, () -> {
            gestor.actualizarStock(301, -20);
        }, "Debe lanzar StockInvalidoException si se asigna stock negativo");
    }
}
