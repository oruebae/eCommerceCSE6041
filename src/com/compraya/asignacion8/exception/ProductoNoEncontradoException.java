package com.compraya.asignacion8.exception;

/**
 * Excepción lanzada al intentar buscar o manipular un producto que no existe en el catálogo.
 */
public class ProductoNoEncontradoException extends EcommerceException {

    private final int productoId;

    public ProductoNoEncontradoException(int productoId) {
        super(String.format("No se encontró ningún producto registrado con el ID %d", productoId), 
                "ERR_PRODUCTO_NO_ENCONTRADO");
        this.productoId = productoId;
    }

    public int getProductoId() {
        return productoId;
    }
}
