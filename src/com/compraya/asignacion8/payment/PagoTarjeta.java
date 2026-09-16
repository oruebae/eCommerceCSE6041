package com.compraya.asignacion8.payment;

import com.compraya.asignacion8.exception.PagoFallidoException;

/**
 * Implementación de pago con tarjeta de crédito/débito.
 */
public class PagoTarjeta implements ProcesoPago {
    private final String numeroTarjeta;
    private final String titular;
    private final double limiteCredito;

    public PagoTarjeta(String numeroTarjeta, String titular, double limiteCredito) {
        if (numeroTarjeta == null || numeroTarjeta.trim().length() < 13) {
            throw new IllegalArgumentException("Número de tarjeta inválido.");
        }
        this.numeroTarjeta = numeroTarjeta.trim();
        this.titular = titular != null ? titular.trim() : "Titular Desconocido";
        this.limiteCredito = limiteCredito;
    }

    @Override
    public boolean procesarPago(double monto, String referencia) throws PagoFallidoException {
        if (monto <= 0) {
            throw new PagoFallidoException(getNombreMetodo(), monto, "El monto del pago debe ser mayor a cero.");
        }
        if (monto > limiteCredito) {
            throw new PagoFallidoException(getNombreMetodo(), monto, 
                    String.format("Límite de crédito excedido. Disponible: $%.2f", limiteCredito));
        }
        // Simular aprobación exitosa
        return true;
    }

    @Override
    public String getNombreMetodo() {
        return "Tarjeta de Crédito/Débito";
    }
}
