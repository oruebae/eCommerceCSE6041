package com.compraya.asignacion8.service;

import com.compraya.asignacion8.exception.*;
import com.compraya.asignacion8.inventory.GestorInventario;
import com.compraya.asignacion8.model.*;
import com.compraya.asignacion8.payment.ProcesoPago;

/**
 * Servicio de Negocio e-Commerce para la Asignación 8.
 * Implementa el manejo robusto de excepciones con recuperación graciosa.
 */
public class EcommerceServiceAsignacion8 {
    private final GestorInventario inventario;
    private int contadorPedidos;

    public EcommerceServiceAsignacion8() {
        this.inventario = new GestorInventario();
        this.contadorPedidos = 8000;
    }

    public GestorInventario getInventario() {
        return inventario;
    }

    /**
     * Intenta agregar un producto al carrito de compras con captura de errores.
     */
    public boolean agregarAlCarritoSeguro(Carrito carrito, int productoId, int cantidad) {
        try {
            Producto producto = inventario.buscarProductoPorId(productoId);
            carrito.agregarProducto(producto, cantidad);
            System.out.printf("   [ÉXITO SEGURO] Se agregaron %d unidad(es) de '%s' al carrito.%n", 
                    cantidad, producto.getNombre());
            return true;
        } catch (ProductoNoEncontradoException e) {
            System.err.printf("   [RECUPERACIÓN GRACIOSA] Error de catálogo (%s): %s%n", 
                    e.getCodigoError(), e.getMessage());
            System.out.println("   -> Sugerencia: Por favor verifique el código del producto en el catálogo.");
            return false;
        } catch (InventarioInsuficienteException e) {
            System.err.printf("   [RECUPERACIÓN GRACIOSA] Error de stock (%s): %s%n", 
                    e.getCodigoError(), e.getMessage());
            System.out.printf("   -> Sugerencia: Solo hay %d unidades disponibles. ¿Desea agregar las %d restantes?%n", 
                    e.getStockDisponible(), e.getStockDisponible());
            return false;
        } catch (EcommerceException e) {
            System.err.printf("   [ERROR DE NEGOCIO] %s%n", e.toString());
            return false;
        }
    }

    /**
     * Procesa la compra de un carrito con estrategia de recuperación (Fallback de Pasarela de Pago).
     */
    public Pedido procesarCompraConFallback(Cliente cliente, Carrito carrito, ProcesoPago pasarelaPrincipal, ProcesoPago pasarelaSecundaria) {
        System.out.println("\n--- [PROCESO DE CHECKOUT CON MANEJO DE EXCEPCIONES] ---");

        if (carrito.estaVacio()) {
            throw new CarritoVacioException();
        }

        // 1. Verificar y descontar stock para cada producto
        for (ItemCarrito item : carrito.getItems()) {
            inventario.descontarStock(item.getProducto().getId(), item.getCantidad());
        }

        // 2. Crear Pedido
        contadorPedidos++;
        Pedido pedido = new Pedido(contadorPedidos, cliente, carrito, 0.19);
        System.out.printf("   [PEDIDO #%d CREADO] Total a pagar: $%.2f (Subtotal: $%.2f, IVA 19%%: $%.2f)%n", 
                pedido.getId(), pedido.getTotal(), pedido.getSubtotal(), pedido.getIva());

        // 3. Intentar cobrar con pasarela principal
        boolean pagoAprobado = false;
        try {
            System.out.printf("   [PASARELA PRINCIPAL] Intentando cobrar $%.2f con '%s'...%n", 
                    pedido.getTotal(), pasarelaPrincipal.getNombreMetodo());
            pagoAprobado = pasarelaPrincipal.procesarPago(pedido.getTotal(), "REF-" + pedido.getId());
            System.out.println("   [PASARELA PRINCIPAL] ¡Pago APROBADO exitosamente!");
        } catch (PagoFallidoException e) {
            System.err.printf("   [FALLO EN PASARELA PRINCIPAL] %s%n", e.getMessage());

            if (pasarelaSecundaria != null) {
                System.out.printf("   [RECUPERACIÓN DE ERROR] Intentando cobro alternativo con pasarela de contingencia '%s'...%n", 
                        pasarelaSecundaria.getNombreMetodo());
                try {
                    pagoAprobado = pasarelaSecundaria.procesarPago(pedido.getTotal(), "REF-ALT-" + pedido.getId());
                    System.out.println("   [PASARELA SECUNDARIA] ¡Pago APROBADO exitosamente en contingencia!");
                } catch (PagoFallidoException e2) {
                    System.err.printf("   [FALLO CRÍTICO DE PAGO] La pasarela secundaria también falló: %s%n", e2.getMessage());
                    // Rollback de stock
                    for (ItemCarrito item : carrito.getItems()) {
                        inventario.actualizarStock(item.getProducto().getId(), 
                                inventario.getStockActual(item.getProducto().getId()) + item.getCantidad());
                    }
                    throw new PagoFallidoException("TODAS_LAS_PASARELAS", pedido.getTotal(), 
                            "Transacción rechazada en todas las pasarelas. Se realizó el rollback del inventario.");
                }
            } else {
                // Rollback de stock
                for (ItemCarrito item : carrito.getItems()) {
                    inventario.actualizarStock(item.getProducto().getId(), 
                            inventario.getStockActual(item.getProducto().getId()) + item.getCantidad());
                }
                throw e;
            }
        }

        if (pagoAprobado) {
            pedido.setEstado("PAGADO");
            carrito.vaciarCarrito();
        }

        return pedido;
    }

    /**
     * Valida permisos administrativos antes de modificar inventario.
     */
    public void modificarInventarioComoUsuario(Usuario usuario, int productoId, int nuevoStock) {
        if (!(usuario instanceof Administrador)) {
            throw new UsuarioNoAutorizadoException(usuario.getEmail(), "Actualización directa de stock en inventario");
        }
        inventario.actualizarStock(productoId, nuevoStock);
        System.out.printf("   [ADMIN OK] Stock del producto ID %d actualizado a %d por '%s'%n", 
                productoId, nuevoStock, usuario.getNombre());
    }
}
