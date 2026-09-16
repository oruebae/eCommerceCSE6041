package com.compraya.asignacion7.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Sujeto Concreto GestorNotificaciones (Patrón Observer - Asignación 7).
 * Administra la lista de observadores y transmite eventos del ciclo de vida de pedidos e inventario.
 */
public class GestorNotificaciones implements SujetoNotificable {
    private List<ObservadorEvento> observadores;

    public GestorNotificaciones() {
        this.observadores = new ArrayList<>();
    }

    @Override
    public void suscribir(ObservadorEvento observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
            System.out.println("[PATRÓN OBSERVER] Nuevo observador suscrito: " + observador.getClass().getSimpleName());
        }
    }

    @Override
    public void desuscribir(ObservadorEvento observador) {
        if (observador != null && observadores.remove(observador)) {
            System.out.println("[PATRÓN OBSERVER] Observador removido: " + observador.getClass().getSimpleName());
        }
    }

    @Override
    public void notificarObservadores(String tipoEvento, Object datos) {
        System.out.printf("[PATRÓN OBSERVER] Disparando evento '%s' a %d observadores...\n", tipoEvento, observadores.size());
        for (ObservadorEvento obs : observadores) {
            obs.enNotificacionEvento(tipoEvento, datos);
        }
    }
}
