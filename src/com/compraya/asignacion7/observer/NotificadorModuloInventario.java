package com.compraya.asignacion7.observer;

import com.compraya.asignacion7.model.Pedido;

/**
 * Observador Concreto 2 (Patrón Observer - Asignación 7).
 * Reacciona ante confirmaciones de pedidos para sincronizar el módulo de existencias de bodega.
 */
public class NotificadorModuloInventario implements ObservadorEvento {

    @Override
    public void enNotificacionEvento(String tipoEvento, Object datos) {
        if ("CAMBIO_ESTADO_PEDIDO".equalsIgnoreCase(tipoEvento) && datos instanceof Pedido) {
            Pedido p = (Pedido) datos;
            if ("PAGADO".equalsIgnoreCase(p.getEstado())) {
                System.out.printf("   📦 [OBSERVER - MÓDULO INVENTARIO] Pedido #%d PAGADO -> Reservando físicamente %d ítems en la bodega y generando orden de empaque.\n",
                        p.getId(), p.getItems().size());
            }
        } else if ("ALERTA_STOCK_BAJO".equalsIgnoreCase(tipoEvento)) {
            System.out.println("   ⚠️ [OBSERVER - MÓDULO INVENTARIO] Alerta de stock bajo recibida: Generando orden de reposición automática a proveedores.");
        }
    }
}
