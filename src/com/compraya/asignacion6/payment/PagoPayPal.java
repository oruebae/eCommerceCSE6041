package com.compraya.asignacion6.payment;

/**
 * Clase Concreta PagoPayPal (Asignación 6).
 * Implementa ProcesoPago para pagos digitales globales vía PayPal.
 */
public class PagoPayPal implements ProcesoPago {
    private String emailCuentaPayPal;
    private String estadoTransaccion;
    private double montoProcesado;

    public PagoPayPal(String emailCuentaPayPal) {
        this.emailCuentaPayPal = emailCuentaPayPal;
        this.estadoTransaccion = "NO_INICIADO";
    }

    @Override
    public boolean iniciarPago(double monto, String referencia) {
        this.montoProcesado = monto;
        System.out.printf("[PAGO PAYPAL] Redirigiendo a API REST PayPal OAuth2. Solicitante: '%s' | Monto: $%.2f | Ref: '%s'...\n",
                emailCuentaPayPal, monto, referencia);
        this.estadoTransaccion = "INICIADO";
        return true;
    }

    @Override
    public boolean verificarPago(String idTransaccion) {
        System.out.printf("[PAGO PAYPAL] Validando token de autorización PayPal (PayerID) para TXN: %s...\n", idTransaccion);
        this.estadoTransaccion = "VERIFICADO";
        return true;
    }

    @Override
    public boolean confirmarPago(String idTransaccion) {
        if ("VERIFICADO".equals(estadoTransaccion) || "INICIADO".equals(estadoTransaccion)) {
            this.estadoTransaccion = "APROBADO_Y_LIQUIDADO";
            System.out.printf("[PAGO PAYPAL] ¡Pago verificado y transferido desde cuenta PayPal '%s'! TXN: %s por $%.2f\n",
                    emailCuentaPayPal, idTransaccion, montoProcesado);
            return true;
        }
        return false;
    }

    @Override
    public String getNombreMetodo() {
        return "PayPal Wallet (" + emailCuentaPayPal + ")";
    }
}
