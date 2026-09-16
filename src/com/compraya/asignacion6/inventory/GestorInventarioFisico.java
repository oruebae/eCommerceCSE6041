package com.compraya.asignacion6.inventory;

import com.compraya.asignacion6.model.Item;
import com.compraya.asignacion6.model.ProductoFisico;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase Concreta GestorInventarioFisico (Asignación 6).
 * Extiende GestorInventario implementando la gestión de productos físicos en bodegas, pasillos y estantes.
 */
public class GestorInventarioFisico extends GestorInventario {
    private Map<Integer, ProductoFisico> bodegaFisica;
    private Map<Integer, String> ubicacionesPasillo;

    public GestorInventarioFisico() {
        super("INVENTARIO_FISICO_BODEGA");
        this.bodegaFisica = new HashMap<>();
        this.ubicacionesPasillo = new HashMap<>();
    }

    @Override
    public boolean añadirProducto(Item item) {
        if (item instanceof ProductoFisico) {
            ProductoFisico pf = (ProductoFisico) item;
            bodegaFisica.put(pf.getId(), pf);
            String pasillo = "Pasillo-" + (pf.getId() % 10 + 1) + " (Estante " + (pf.getId() % 5 + 1) + ")";
            ubicacionesPasillo.put(pf.getId(), pasillo);
            incrementarArticulosProcesados();
            System.out.printf("[INVENTARIO FÍSICO] Producto '%s' registrado en %s con stock: %d unidades.\n",
                    pf.getNombre(), pasillo, pf.getStock());
            return true;
        } else {
            System.out.println("[INVENTARIO FÍSICO] Error: No se puede añadir un ítem no físico al almacén de bodega.");
            return false;
        }
    }

    @Override
    public boolean eliminarProducto(int id) {
        if (bodegaFisica.containsKey(id)) {
            ProductoFisico pf = bodegaFisica.remove(id);
            ubicacionesPasillo.remove(id);
            System.out.printf("[INVENTARIO FÍSICO] Producto '%s' (ID %d) retirado del almacén.\n", pf.getNombre(), id);
            return true;
        }
        System.out.printf("[INVENTARIO FÍSICO] Error: Producto con ID %d no encontrado en la bodega.\n", id);
        return false;
    }

    @Override
    public boolean actualizarStock(int id, int nuevoStock) {
        if (bodegaFisica.containsKey(id)) {
            ProductoFisico pf = bodegaFisica.get(id);
            pf.setStock(nuevoStock);
            System.out.printf("[INVENTARIO FÍSICO] Stock físico actualizado para '%s': %d unidades en bodega.\n",
                    pf.getNombre(), nuevoStock);
            return true;
        }
        return false;
    }

    @Override
    public void generarReporteInventario() {
        System.out.println("=======================================================");
        System.out.println(" 📦 REPORTE BODEGA DE INVENTARIO FÍSICO (COMPRAYA)     ");
        System.out.println("=======================================================");
        System.out.println("Tipo: " + getTipoInventario() + " | Artículos: " + bodegaFisica.size());
        for (ProductoFisico pf : bodegaFisica.values()) {
            String ubicacion = ubicacionesPasillo.getOrDefault(pf.getId(), "Pasillo General");
            System.out.printf("  • ID %d | %-22s | Stock: %-4d | Ubicación: %s | Peso: %.2f kg\n",
                    pf.getId(), pf.getNombre(), pf.getStock(), ubicacion, pf.getPesoKg());
        }
        System.out.println("=======================================================");
    }
}
