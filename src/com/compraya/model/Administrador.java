package com.compraya.model;

/**
 * Clase Administrador que extiende Usuario (Herencia).
 * Gestiona el inventario y genera reportes de ventas de la plataforma CompraYa.
 */
public class Administrador extends Usuario {
    private String nivelAcceso;

    public Administrador() {
        super();
        this.nivelAcceso = "SUPERADMIN";
    }

    public Administrador(int id, String nombre, String email, String password, String nivelAcceso) {
        super(id, nombre, email, password);
        this.nivelAcceso = nivelAcceso;
    }

    public String getNivelAcceso() {
        return nivelAcceso;
    }

    public void setNivelAcceso(String nivelAcceso) {
        this.nivelAcceso = nivelAcceso;
    }

    public void gestionarInventario(Producto producto, int nuevoStock) {
        if (producto != null) {
            producto.setStock(nuevoStock);
            System.out.printf("[ADMIN %s] Stock actualizado para '%s': %d unidades.\n",
                    getNombre(), producto.getNombre(), nuevoStock);
        }
    }

    public void generarReporte(double totalVentas, int totalPedidos) {
        System.out.println("==========================================");
        System.out.println("         REPORTE DE VENTAS COMPRAYA       ");
        System.out.println("==========================================");
        System.out.printf("Generado por Admin: %s (%s)\n", getNombre(), nivelAcceso);
        System.out.printf("Total de Pedidos Procesados: %d\n", totalPedidos);
        System.out.printf("Ingresos Totales Acumulados: $%.2f\n", totalVentas);
        System.out.println("==========================================");
    }

    @Override
    public String toString() {
        return String.format("Administrador[ID: %d | Nombre: %s | NivelAcceso: %s]",
                getId(), getNombre(), nivelAcceso);
    }
}
