package com.compraya.asignacion8.test;

import com.compraya.asignacion8.exception.CarritoVacioException;
import com.compraya.asignacion8.exception.InventarioInsuficienteException;
import com.compraya.asignacion8.model.Carrito;
import com.compraya.asignacion8.model.ProductoFisico;

public class CarritoTest {

    public static void ejecutarPruebas() {
        System.out.println("\n--- [SUITE] Pruebas Unitarias para Carrito de Compras ---");

        ProductoFisico p1 = new ProductoFisico(201, "Mouse Gamer", "Mouse óptico", 150000.0, 5, 0.2, "12x6 cm");
        ProductoFisico p2 = new ProductoFisico(202, "Teclado Mecánico", "RGB Switch Red", 300000.0, 2, 0.8, "45x15 cm");

        // 1. Carrito vacío inicialmente
        Carrito carrito = new Carrito();
        TestFramework.assertTrue(carrito.estaVacio(), "El carrito recién creado debe estar vacío");
        TestFramework.assertEquals(0.0, carrito.calcularTotal(), 0.001, "El total de un carrito vacío debe ser 0.0");

        // 2. Agregar productos y calcular total
        carrito.agregarProducto(p1, 2);
        TestFramework.assertEquals(2, carrito.getCantidadTotalItems(), "Cantidad de ítems agregada correctamente");
        TestFramework.assertEquals(300000.0, carrito.calcularTotal(), 0.001, "Subtotal del carrito calculado correctamente ($300.000)");

        // 3. Excepción por superar stock disponible (InventarioInsuficienteException)
        TestFramework.assertThrows(InventarioInsuficienteException.class, () -> {
            carrito.agregarProducto(p2, 10); // p2 solo tiene stock 2
        }, "Debe lanzar InventarioInsuficienteException al intentar agregar 10 unidades teniendo solo stock 2");

        // 4. Remover producto
        carrito.removerProducto(p1.getId());
        TestFramework.assertTrue(carrito.estaVacio(), "El carrito vuelve a estar vacío tras remover el único ítem");

        // 5. Excepción al remover de carrito vacío (CarritoVacioException)
        TestFramework.assertThrows(CarritoVacioException.class, () -> {
            carrito.removerProducto(999);
        }, "Debe lanzar CarritoVacioException al intentar remover un producto de un carrito vacío");
    }
}
