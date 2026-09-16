package com.compraya.asignacion8.exception;

/**
 * Excepción lanzada cuando ocurre un error en la pasarela o procesamiento de pagos.
 */
public class PagoFallidoException extends EcommerceException {

    private final String metodoPago;
    private final double monto;

    public PagoFallidoException(String metodoPago, double monto, String motivo) {
        super(String.format("Fallo al procesar el pago con '%s' por $%.2f. Motivo: %s", 
                metodoPago, monto, motivo), "ERR_PAGO_FALLIDO");
        this.metodoPago = metodoPago;
        this.monto = monto;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public double getMonto() {
        return monto;
    }
}
