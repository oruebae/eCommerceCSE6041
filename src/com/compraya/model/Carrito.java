package com.compraya.model;

import com.compraya.service.EcommerceService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Clase Carrito (o CarritoDeCompras) que gestiona los productos seleccionados.
 * Aplica Sobrecarga de Métodos (Method Overloading) en agregarProducto y removerProducto.
 */
public class Carrito {
    private int id;
    private List<ItemCarrito> items;
    private double total;
    private LocalDateTime fechaCreacion;

    /**
     * Constructor por defecto para inicializar el objeto Carrito.
     */
    public Carrito() {
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.fechaCreacion = LocalDateTime.now();
    }

    /**
     * Constructor con ID para inicializar el Carrito de Compras.
     * 
     * @param id Identificador único del carrito.
     */
    public Carrito(int id) {
        this.id = id;
        this.items = new ArrayList<>();
        this.total = 0.0;
        this.fechaCreacion = LocalDateTime.now();
    }

    // =========================================================================
    // SOBRECARGA DE MÉTODOS (METHOD OVERLOADING) - agregarProducto
    // =========================================================================

    /**
     * Variación 1 de Sobrecarga: Agrega 1 unidad por defecto a partir de un objeto Producto.
     * 
     * @param producto Objeto Producto.
     * @return true si fue agregado exitosamente.
     */
    public boolean agregarProducto(Producto producto) {
        return agregarProducto(producto, 1);
    }

    /**
     * Variación 2 de Sobrecarga: Agrega una cantidad específica a partir de un objeto Producto.
     * 
     * @param producto Objeto Producto a agregar.
     * @param cantidad Cantidad requerida.
     * @return true si fue agregado exitosamente.
     */
    public boolean agregarProducto(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            System.out.println("Error: Producto no válido o cantidad menor o igual a cero.");
            return false;
        }

        if (producto.getStock() < cantidad) {
            System.out.printf("Error: Stock insuficiente para '%s'. Disponible: %d, Solicitado: %d\n",
                    producto.getNombre(), producto.getStock(), cantidad);
            return false;
        }

        // Buscar si ya existe el producto en los ítems
        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == producto.getId()) {
                int nuevaCantidad = item.getCantidad() + cantidad;
                if (producto.getStock() < nuevaCantidad) {
                    System.out.printf("Error: Supera el stock disponible (%d unidades).\n", producto.getStock());
                    return false;
                }
                item.setCantidad(nuevaCantidad);
                calcularTotal();
                return true;
            }
        }

        // Si no existe, crea un nuevo ItemCarrito
        items.add(new ItemCarrito(producto, cantidad));
        calcularTotal();
        return true;
    }

    /**
     * Variación 3 de Sobrecarga: Agrega 1 unidad por defecto buscando un producto por su ID en el catálogo.
     * 
     * @param productoId ID del producto en el catálogo.
     * @param service    Servicio de eCommerce/Catálogo.
     * @return true si el producto fue encontrado y agregado.
     */
    public boolean agregarProducto(int productoId, EcommerceService service) {
        return agregarProducto(productoId, 1, service);
    }

    /**
     * Variación 4 de Sobrecarga: Agrega la cantidad especificada buscando un producto por su ID en el catálogo.
     * 
     * @param productoId ID del producto.
     * @param cantidad   Cantidad requerida.
     * @param service    Servicio de eCommerce/Catálogo.
     * @return true si fue encontrado y agregado.
     */
    public boolean agregarProducto(int productoId, int cantidad, EcommerceService service) {
        if (service == null) {
            System.out.println("Error: Servicio de catálogo no proporcionado.");
            return false;
        }
        Producto p = service.buscarProductoPorId(productoId).orElse(null);
        if (p == null) {
            System.out.printf("Error: No se encontró ningún producto con ID %d en el catálogo.\n", productoId);
            return false;
        }
        return agregarProducto(p, cantidad);
    }

    /**
     * Variación 5 de Sobrecarga: Crea dinámicamente un producto rápido y lo agrega al carrito con la cantidad dada.
     * 
     * @param nombre    Nombre comercial.
     * @param precio    Precio unitario.
     * @param cantidad  Cantidad a adquirir.
     * @param categoria Categoría asignada.
     * @return true si el producto genérico fue creado e insertado.
     */
    public boolean agregarProducto(String nombre, double precio, int cantidad, Categoria categoria) {
        int idTemp = (int) (System.currentTimeMillis() % 100000);
        Producto prodGenerico = new Producto(idTemp, nombre, "Producto Rápido Generado", precio, cantidad + 10, categoria);
        System.out.printf("[CARRITO - SOBRECARGA] Generando e insertando producto dinámico: '%s' ($%.2f) x%d\n",
                nombre, precio, cantidad);
        return agregarProducto(prodGenerico, cantidad);
    }

    // =========================================================================
    // SOBRECARGA DE MÉTODOS (METHOD OVERLOADING) - removerProducto
    // =========================================================================

    /**
     * Variación 1 de Sobrecarga: Remueve un producto del carrito por su ID.
     * 
     * @param productoId ID del producto.
     * @return true si fue removido.
     */
    public boolean removerProducto(int productoId) {
        boolean removido = items.removeIf(item -> item.getProducto().getId() == productoId);
        if (removido) {
            calcularTotal();
        }
        return removido;
    }

    /**
     * Variación 2 de Sobrecarga: Remueve un producto del carrito pasando el objeto Producto.
     * 
     * @param producto Objeto Producto a remover.
     * @return true si fue removido.
     */
    public boolean removerProducto(Producto producto) {
        if (producto == null) return false;
        return removerProducto(producto.getId());
    }

    // --- Otros Métodos de Carrito ---

    public boolean modificarCantidad(int productoId, int nuevaCantidad) {
        if (nuevaCantidad <= 0) {
            return removerProducto(productoId);
        }

        for (ItemCarrito item : items) {
            if (item.getProducto().getId() == productoId) {
                if (item.getProducto().getStock() < nuevaCantidad) {
                    System.out.printf("Error: Stock insuficiente (%d unidades en stock).\n", item.getProducto().getStock());
                    return false;
                }
                item.setCantidad(nuevaCantidad);
                calcularTotal();
                return true;
            }
        }
        return false;
    }

    public double calcularTotal() {
        this.total = 0.0;
        for (ItemCarrito item : items) {
            this.total += item.getSubtotal();
        }
        return this.total;
    }

    public void vaciarCarrito() {
        items.clear();
        this.total = 0.0;
    }

    // --- Getters y Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ItemCarrito> getItems() {
        return Collections.unmodifiableList(items);
    }

    public double getTotal() {
        return calcularTotal();
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public int getCantidadTotalProductos() {
        int suma = 0;
        for (ItemCarrito item : items) {
            suma += item.getCantidad();
        }
        return suma;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("=== Carrito de Compras (Total: $%.2f) ===\n", getTotal()));
        if (items.isEmpty()) {
            sb.append(" [El carrito está vacío]\n");
        } else {
            for (ItemCarrito item : items) {
                sb.append(" - ").append(item.toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
