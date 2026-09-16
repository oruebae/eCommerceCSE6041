package com.compraya.asignacion5.model;

/**
 * Interface Despachable (Abstracción - Asignación 5).
 * Contrato para aquellos productos que requieren cálculo de logística de despacho físico.
 */
public interface Despachable {
    double calcularCostoEnvio(String departamento);
    double getPesoKg();
    String getDimensiones();
}
