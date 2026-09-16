package com.compraya.asignacion8.test;

/**
 * Runner principal que ejecuta la batería completa de pruebas unitarias.
 */
public class TestRunnerAsignacion8 {

    public static boolean ejecutarTodasLasPruebas() {
        System.out.println("====================================================================");
        System.out.println("    EJECUCIÓN DE SUITE DE PRUEBAS UNITARIAS - ASIGNACIÓN NO. 8     ");
        System.out.println("====================================================================");

        TestFramework.reiniciarEstadisticas();

        // Ejecutar suites de prueba
        ProductoTest.ejecutarPruebas();
        UsuarioTest.ejecutarPruebas();
        CarritoTest.ejecutarPruebas();
        InventarioTest.ejecutarPruebas();
        PagoTest.ejecutarPruebas();

        // Mostrar resumen general
        int total = TestFramework.getTotalPruebas();
        int exitosas = TestFramework.getPruebasExitosas();
        int fallidas = TestFramework.getPruebasFallidas();
        double porcentajePase = total > 0 ? ((double) exitosas / total) * 100.0 : 0.0;

        System.out.println("\n====================================================================");
        System.out.println("               RESUMEN DE RESULTADOS DE LAS PRUEBAS                ");
        System.out.println("====================================================================");
        System.out.printf(" Total de Pruebas Ejecutadas : %d%n", total);
        System.out.printf(" Pruebas Exitosas (PASÓ)     : %d%n", exitosas);
        System.out.printf(" Pruebas Fallidas (FALLÓ)    : %d%n", fallidas);
        System.out.printf(" Tasa de Éxito / Cobertura   : %.2f%%%n", porcentajePase);
        System.out.println("====================================================================");

        if (fallidas == 0) {
            System.out.println("  🎉 ¡TODAS LAS PRUEBAS UNITARIAS PASARON EXITOSAMENTE (100%)!");
            System.out.println("====================================================================\n");
            return true;
        } else {
            System.err.printf("  ⚠️ ATENCIÓN: Se detectaron %d fallos en las pruebas unitarias.%n", fallidas);
            System.out.println("====================================================================\n");
            return false;
        }
    }
}
