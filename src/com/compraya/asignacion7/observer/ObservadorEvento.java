package com.compraya.asignacion7.observer;

/**
 * Interface ObservadorEvento (Patrón Observer - Asignación 7).
 * Contrato para los suscriptores que desean recibir notificaciones automáticas de eventos del sistema.
 */
public interface ObservadorEvento {
    /**
     * Reacciona ante una notificación enviada por el Sujeto.
     * 
     * @param tipoEvento Nombre/Identificador del evento (ej: "CAMBIO_ESTADO_PEDIDO", "STOCK_BAJO").
     * @param datos      Datos o contexto asociado al evento.
     */
    void enNotificacionEvento(String tipoEvento, Object datos);
}
