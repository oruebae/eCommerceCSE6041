package com.compraya.asignacion8.exception;

/**
 * Excepción base para todas las excepciones del dominio e-Commerce CompraYa.
 * Permite capturar de manera uniforme cualquier error de negocio de la plataforma.
 * 
 * @author Alberto Alfonso López Pereira
 * @version 8.0.2026
 */
public class EcommerceException extends RuntimeException {
    
    private final String codigoError;
    private final long timestamp;

    public EcommerceException(String mensaje, String codigoError) {
        super(mensaje);
        this.codigoError = codigoError;
        this.timestamp = System.currentTimeMillis();
    }

    public EcommerceException(String mensaje, String codigoError, Throwable causa) {
        super(mensaje, causa);
        this.codigoError = codigoError;
        this.timestamp = System.currentTimeMillis();
    }

    public String getCodigoError() {
        return codigoError;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[%s] Error (%s): %s", getClass().getSimpleName(), codigoError, getMessage());
    }
}
