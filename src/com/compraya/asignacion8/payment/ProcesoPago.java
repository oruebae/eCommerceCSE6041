package com.compraya.asignacion8.payment;

import com.compraya.asignacion8.exception.PagoFallidoException;

/**
 * Interfaz que define el contrato de procesamiento de pagos.
 */
public interface ProcesoPago {
    
    /**
     * Procesa una transacción financiera.
     * 
     * @param monto El monto total a cobrar.
     * @param referencia La referencia única del pedido o transacción.
     * @return true si el pago fue aprobado exitosamente.
     * @throws PagoFallidoException si el pago es rechazado o falla.
     */
    boolean procesarPago(double monto, String referencia) throws PagoFallidoException;

    String getNombreMetodo();
}
