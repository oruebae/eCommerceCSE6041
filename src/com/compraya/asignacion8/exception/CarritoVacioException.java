package com.compraya.asignacion8.exception;

/**
 * Excepción lanzada al intentar realizar un checkout o pago con un carrito de compras vacío.
 */
public class CarritoVacioException extends EcommerceException {

    public CarritoVacioException() {
        super("No se puede procesar la compra o checkout porque el carrito de compras no contiene productos.", 
                "ERR_CARRITO_VACIO");
    }
}
