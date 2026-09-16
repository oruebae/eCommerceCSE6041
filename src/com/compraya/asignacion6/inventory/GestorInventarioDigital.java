package com.compraya.asignacion6.inventory;

import com.compraya.asignacion6.model.Item;
import com.compraya.asignacion6.model.ProductoDigital;

import java.util.HashMap;
import java.util.Map;

/**
 * Clase Concreta GestorInventarioDigital (Asignación 6).
 * Extiende GestorInventario implementando la gestión de contenidos digitales, licencias cloud y CDN.
 */
public class GestorInventarioDigital extends GestorInventario {
    private Map<Integer, ProductoDigital> catalogoDigital;
    private Map<Integer, String> servidoresCDN;

    public GestorInventarioDigital() {
        super("INVENTARIO_DIGITAL_NUBE_CDN");
        this.catalogoDigital = new HashMap<>();
        this.servidoresCDN = new HashMap<>();
    }

    @Override
    public boolean añadirProducto(Item item) {
        if (item instanceof ProductoDigital) {
            ProductoDigital pd = (ProductoDigital) item;
            catalogoDigital.put(pd.getId(), pd);
            String cdn = "cdn-node-" + (pd.getId() % 3 + 1) + ".compraya.com";
            servidoresCDN.put(pd.getId(), cdn);
            incrementarArticulosProcesados();
            System.out.printf("[INVENTARIO DIGITAL] Licencia digital '%s' sincronizada en nodo CDN %s (%d licencias).\n",
                    pd.getNombre(), cdn, pd.getStock());
            return true;
        } else {
            System.out.println("[INVENTARIO DIGITAL] Error: No se puede añadir un ítem físico a la nube CDN.");
            return false;
        }
    }

    @Override
    public boolean eliminarProducto(int id) {
        if (catalogoDigital.containsKey(id)) {
            ProductoDigital pd = catalogoDigital.remove(id);
            servidoresCDN.remove(id);
            System.out.printf("[INVENTARIO DIGITAL] Licencia '%s' (ID %d) revocada y removida del CDN.\n", pd.getNombre(), id);
            return true;
        }
        System.out.printf("[INVENTARIO DIGITAL] Error: Licencia con ID %d no encontrada en el inventario digital.\n", id);
        return false;
    }

    @Override
    public boolean actualizarStock(int id, int nuevoStock) {
        if (catalogoDigital.containsKey(id)) {
            ProductoDigital pd = catalogoDigital.get(id);
            pd.setStock(nuevoStock);
            System.out.printf("[INVENTARIO DIGITAL] Cupo de licencias actualizado para '%s': %d disponibles en nube.\n",
                    pd.getNombre(), nuevoStock);
            return true;
        }
        return false;
    }

    @Override
    public void generarReporteInventario() {
        System.out.println("=======================================================");
        System.out.println(" 💾 REPORTE INVENTARIO DIGITAL Y LICENCIAS (COMPRAYA)  ");
        System.out.println("=======================================================");
        System.out.println("Tipo: " + getTipoInventario() + " | Licencias Registradas: " + catalogoDigital.size());
        for (ProductoDigital pd : catalogoDigital.values()) {
            String cdn = servidoresCDN.getOrDefault(pd.getId(), "cdn-primary.compraya.com");
            System.out.printf("  • ID %d | %-22s | Licencias: %-4d | Nodo CDN: %s | Formato: %s (%.1f MB)\n",
                    pd.getId(), pd.getNombre(), pd.getStock(), cdn, pd.getFormatoArchivo(), pd.getTamanoMB());
        }
        System.out.println("=======================================================");
    }
}
