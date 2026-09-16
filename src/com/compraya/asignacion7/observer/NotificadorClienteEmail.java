package com.compraya.asignacion7.observer;

import com.compraya.asignacion7.model.Pedido;

/**
 * Observador Concreto 1 (Patrón Observer - Asignación 7).
 * Envía notificaciones de correo electrónico al cliente cuando cambia el estado de su pedido.
 */
public class NotificadorClienteEmail implements ObservadorEvento {

    @Override
    public void enNotificacionEvento(String tipoEvento, Object datos) {
        if ("CAMBIO_ESTADO_PEDIDO".equalsIgnoreCase(tipoEvento) && datos instanceof Pedido) {
            Pedido p = (Pedido) datos;
            System.out.printf("   📧 [OBSERVER - EMAIL CLIENTE] Enviando correo a '%s' (%s): Su pedido #%d ahora está en estado '%s'.\n",
                    p.getCliente().getNombre(), p.getCliente().getEmail(), p.getId(), p.getEstado());
        }
    }
}
