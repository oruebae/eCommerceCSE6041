package com.compraya.asignacion6.payment;

/**
 * Clase Concreta PagoTarjeta (Asignación 6).
 * Implementa ProcesoPago para transacciones con tarjeta de crédito o débito (Visa/MasterCard/Amex).
 */
public class PagoTarjeta implements ProcesoPago {
    private String numeroTarjetaEnmascarado;
    private String titular;
    private String franquicia;
    private String estadoTransaccion;
    private double montoProcesado;

    public PagoTarjeta(String numeroTarjeta, String titular, String franquicia) {
        this.numeroTarjetaEnmascarado = enmascararTarjeta(numeroTarjeta);
        this.titular = titular;
        this.franquicia = franquicia;
        this.estadoTransaccion = "NO_INICIADO";
    }

    private String enmascararTarjeta(String num) {
        if (num != null && num.length() >= 4) {
            String ultimos4 = num.substring(num.length() - 4);
            return "**** **** **** " + ultimos4;
        }
        return "**** **** **** 0000";
    }

    @Override
    public boolean iniciarPago(double monto, String referencia) {
        this.montoProcesado = monto;
        System.out.printf("[PAGO TARJETA - %s] Conectando con pasarela bancaria. Solicitud $%.2f para ref '%s' con tarjeta %s (%s)...\n",
                franquicia, monto, referencia, numeroTarjetaEnmascarado, titular);
        this.estadoTransaccion = "INICIADO";
        return true;
    }

    @Override
    public boolean verificarPago(String idTransaccion) {
        System.out.printf("[PAGO TARJETA - %s] Verificando código CVC y autorización bancaria para TXN: %s...\n", franquicia, idTransaccion);
        this.estadoTransaccion = "VERIFICADO";
        return true;
    }

    @Override
    public boolean confirmarPago(String idTransaccion) {
        if ("VERIFICADO".equals(estadoTransaccion) || "INICIADO".equals(estadoTransaccion)) {
            this.estadoTransaccion = "APROBADO_Y_LIQUIDADO";
            System.out.printf("[PAGO TARJETA - %s] ¡Fondos capturados exitosamente! TXN: %s por $%.2f\n", franquicia, idTransaccion, montoProcesado);
            return true;
        }
        return false;
    }

    @Override
    public String getNombreMetodo() {
        return "Tarjeta de Crédito/Débito (" + franquicia + ")";
    }
}
