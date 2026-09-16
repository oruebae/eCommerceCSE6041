package com.compraya.asignacion5.model;

/**
 * Interface Licenciable (Abstracción - Asignación 5).
 * Contrato para aquellos bienes digitales que requieren generación de enlace seguro y licenciamiento.
 */
public interface Licenciable {
    String generarEnlaceDescarga();
    String getLicencia();
    String getFormatoArchivo();
}
