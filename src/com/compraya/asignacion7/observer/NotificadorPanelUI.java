package com.compraya.asignacion7.observer;

/**
 * Observador Concreto 3 (Patrón Observer - Asignación 7).
 * Refleja eventos del sistema en el panel de control o consola de supervisión del administrador en tiempo real.
 */
public class NotificadorPanelUI implements ObservadorEvento {

    @Override
    public void enNotificacionEvento(String tipoEvento, Object datos) {
        System.out.printf("   🖥️ [OBSERVER - PANEL ADMINISTRACIÓN UI] Evento recibido: '%s' -> Actualizando métricas del dashboard en vivo.\n",
                tipoEvento);
    }
}
