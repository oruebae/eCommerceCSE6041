package com.compraya.asignacion8.exception;

/**
 * Excepción lanzada cuando se intenta asignar un valor de stock negativo o inválido.
 */
public class StockInvalidoException extends EcommerceException {

    private final int stockInvalido;

    public StockInvalidoException(int stockInvalido) {
        super(String.format("El valor de stock %d es inválido. El stock no puede ser negativo.", stockInvalido), 
                "ERR_STOCK_INVALIDO");
        this.stockInvalido = stockInvalido;
    }

    public int getStockInvalido() {
        return stockInvalido;
    }
}
