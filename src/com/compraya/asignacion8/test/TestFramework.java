package com.compraya.asignacion8.test;

/**
 * Framework ligero de pruebas unitarias independiente (estilo JUnit).
 * Proporciona métodos de aserción y reporte automatizado de ejecución.
 * 
 * @author Alberto Alfonso López Pereira
 */
public class TestFramework {
    private static int totalPruebas = 0;
    private static int pruebasExitosas = 0;
    private static int pruebasFallidas = 0;

    public static void reiniciarEstadisticas() {
        totalPruebas = 0;
        pruebasExitosas = 0;
        pruebasFallidas = 0;
    }

    public static void assertTrue(boolean condicion, String mensaje) {
        totalPruebas++;
        if (condicion) {
            pruebasExitosas++;
            System.out.printf("   ✅ PASÓ: %s%n", mensaje);
        } else {
            pruebasFallidas++;
            System.err.printf("   ❌ FALLÓ: %s (Se esperaba true pero fue false)%n", mensaje);
        }
    }

    public static void assertFalse(boolean condicion, String mensaje) {
        assertTrue(!condicion, mensaje);
    }

    public static void assertEquals(Object esperado, Object actual, String mensaje) {
        totalPruebas++;
        boolean iguales = (esperado == null && actual == null) || (esperado != null && esperado.equals(actual));
        if (iguales) {
            pruebasExitosas++;
            System.out.printf("   ✅ PASÓ: %s%n", mensaje);
        } else {
            pruebasFallidas++;
            System.err.printf("   ❌ FALLÓ: %s (Esperado: '%s', Actual: '%s')%n", mensaje, esperado, actual);
        }
    }

    public static void assertEquals(double esperado, double actual, double delta, String mensaje) {
        totalPruebas++;
        if (Math.abs(esperado - actual) <= delta) {
            pruebasExitosas++;
            System.out.printf("   ✅ PASÓ: %s%n", mensaje);
        } else {
            pruebasFallidas++;
            System.err.printf("   ❌ FALLÓ: %s (Esperado: %.4f, Actual: %.4f)%n", mensaje, esperado, actual);
        }
    }

    public static void assertNotNull(Object objeto, String mensaje) {
        assertTrue(objeto != null, mensaje);
    }

    public static <T extends Throwable> void assertThrows(Class<T> tipoExcepcionEsperada, Runnable accion, String mensaje) {
        totalPruebas++;
        try {
            accion.run();
            pruebasFallidas++;
            System.err.printf("   ❌ FALLÓ: %s (Se esperaba excepción '%s' pero no se lanzó ninguna)%n", 
                    mensaje, tipoExcepcionEsperada.getSimpleName());
        } catch (Throwable t) {
            if (tipoExcepcionEsperada.isInstance(t)) {
                pruebasExitosas++;
                System.out.printf("   ✅ PASÓ: %s (Excepción capturada correctamente: %s)%n", 
                        mensaje, t.getClass().getSimpleName());
            } else {
                pruebasFallidas++;
                System.err.printf("   ❌ FALLÓ: %s (Se esperaba '%s' pero se capturó '%s')%n", 
                        mensaje, tipoExcepcionEsperada.getSimpleName(), t.getClass().getSimpleName());
            }
        }
    }

    public static void fail(String mensaje) {
        totalPruebas++;
        pruebasFallidas++;
        System.err.printf("   ❌ FALLÓ EXPLICITAMENTE: %s%n", mensaje);
    }

    public static int getTotalPruebas() {
        return totalPruebas;
    }

    public static int getPruebasExitosas() {
        return pruebasExitosas;
    }

    public static int getPruebasFallidas() {
        return pruebasFallidas;
    }
}
