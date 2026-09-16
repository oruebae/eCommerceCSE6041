package com.compraya.asignacion8.test;

import com.compraya.asignacion8.exception.StockInvalidoException;
import com.compraya.asignacion8.model.ProductoDigital;
import com.compraya.asignacion8.model.ProductoFisico;

public class ProductoTest {

    public static void ejecutarPruebas() {
        System.out.println("\n--- [SUITE] Pruebas Unitarias para Producto ---");

        // 1. Creación exitosa de ProductoFísico
        ProductoFisico laptop = new ProductoFisico(101, "Laptop Pro", "Intel i7 16GB", 3500000.0, 10, 2.1, "35x25x2 cm");
        TestFramework.assertEquals(101, laptop.getId(), "ID del producto correcto");
        TestFramework.assertEquals("Laptop Pro", laptop.getNombre(), "Nombre del producto correcto");
        TestFramework.assertEquals(3500000.0, laptop.getPrecio(), 0.001, "Precio del producto correcto");
        TestFramework.assertEquals(10, laptop.getStock(), "Stock inicial del producto correcto");

        // 2. Creación exitosa de ProductoDigital
        ProductoDigital ebook = new ProductoDigital(102, "Ebook Clean Code", "PDF Técnico", 45000.0, 999, "PDF", 15.5);
        TestFramework.assertEquals("ProductoDigital", ebook.getTipoProducto(), "Tipo de producto digital correcto");

        // 3. Excepción por Stock Negativo (StockInvalidoException)
        TestFramework.assertThrows(StockInvalidoException.class, () -> {
            laptop.setStock(-5);
        }, "Debe lanzar StockInvalidoException si el stock asignado es negativo");

        // 4. Excepción por Precio Negativo (IllegalArgumentException)
        TestFramework.assertThrows(IllegalArgumentException.class, () -> {
            laptop.setPrecio(-100.0);
        }, "Debe lanzar IllegalArgumentException si el precio es negativo");

        // 5. Excepción por Nombre Vacío (IllegalArgumentException)
        TestFramework.assertThrows(IllegalArgumentException.class, () -> {
            new ProductoFisico(103, "   ", "Desc", 1000.0, 5, 1.0, "10x10");
        }, "Debe lanzar IllegalArgumentException si el nombre está en blanco");
    }
}
