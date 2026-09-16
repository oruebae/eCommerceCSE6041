package com.compraya.asignacion7.observer;

/**
 * Interface SujetoNotificable (Patrón Observer - Asignación 7).
 * Contrato para el sujeto que administra la lista de suscriptores y notifica eventos.
 */
public interface SujetoNotificable {
    void suscribir(ObservadorEvento observador);
    void desuscribir(ObservadorEvento observador);
    void notificarObservadores(String tipoEvento, Object datos);
}
