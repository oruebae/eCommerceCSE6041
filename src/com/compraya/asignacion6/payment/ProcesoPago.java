package com.compraya.asignacion6.payment;

/**
 * Interface ProcesoPago (Asignación 6).
 * Define el contrato estándar para cualquier pasarela o método de pago en la plataforma CompraYa.
 */
public interface ProcesoPago {

    /**
     * Inicia el proceso de transacción solicitando autorización a la pasarela.
     * 
     * @param monto      Valor total de la transacción.
     * @param referencia Código o número de referencia del pedido.
     * @return true si la solicitud fue recibida e iniciada.
     */
    boolean iniciarPago(double monto, String referencia);

    /**
     * Verifica el estado actual de una transacción en la pasarela externa.
     * 
     * @param idTransaccion Identificador único de transacción.
     * @return true si la transacción fue verificada con éxito.
     */
    boolean verificarPago(String idTransaccion);

    /**
     * Confirma y liquida los fondos del pago aprobado.
     * 
     * @param idTransaccion ID de transacción.
     * @return true si la confirmación final fue exitosa.
     */
    boolean confirmarPago(String idTransaccion);

    /**
     * Retorna el nombre comercial del método de pago.
     * 
     * @return Nombre del método (ej. "Tarjeta de Crédito", "PayPal", "PSE").
     */
    String getNombreMetodo();
}
