package com.compraya.asignacion8.exception;

/**
 * Excepción lanzada cuando una operación requiere más stock del disponible en inventario.
 */
public class InventarioInsuficienteException extends EcommerceException {
    
    private final int productoId;
    private final int stockSolicitado;
    private final int stockDisponible;

    public InventarioInsuficienteException(int productoId, int stockSolicitado, int stockDisponible) {
        super(String.format("Stock insuficiente para el producto ID %d. Solicitado: %d, Disponible: %d", 
                productoId, stockSolicitado, stockDisponible), "ERR_STOCK_INSUFICIENTE");
        this.productoId = productoId;
        this.stockSolicitado = stockSolicitado;
        this.stockDisponible = stockDisponible;
    }

    public int getProductoId() {
        return productoId;
    }

    public int getStockSolicitado() {
        return stockSolicitado;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }
}
