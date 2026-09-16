package com.compraya.asignacion8.payment;

import com.compraya.asignacion8.exception.PagoFallidoException;

/**
 * Implementación de pago con cuenta PayPal.
 */
public class PagoPayPal implements ProcesoPago {
    private final String emailPayPal;
    private final boolean saldoSuficiente;

    public PagoPayPal(String emailPayPal, boolean saldoSuficiente) {
        if (emailPayPal == null || !emailPayPal.contains("@")) {
            throw new IllegalArgumentException("Cuenta de correo PayPal no válida.");
        }
        this.emailPayPal = emailPayPal.trim();
        this.saldoSuficiente = saldoSuficiente;
    }

    @Override
    public boolean procesarPago(double monto, String referencia) throws PagoFallidoException {
        if (monto <= 0) {
            throw new PagoFallidoException(getNombreMetodo(), monto, "El monto debe ser positivo.");
        }
        if (!saldoSuficiente) {
            throw new PagoFallidoException(getNombreMetodo(), monto, "Saldo insuficiente en la cuenta PayPal.");
        }
        return true;
    }

    @Override
    public String getNombreMetodo() {
        return "PayPal Global Checkout";
    }
}
