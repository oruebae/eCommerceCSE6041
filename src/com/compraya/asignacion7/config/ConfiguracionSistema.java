package com.compraya.asignacion7.config;

/**
 * Patrón Singleton - Gestión de Configuración (Asignación 7).
 * Garantiza la existencia de una única instancia en memoria para toda la aplicación CompraYa,
 * proporcionando acceso global seguro a los parámetros del sistema e-Commerce.
 */
public class ConfiguracionSistema {
    private static volatile ConfiguracionSistema instancia;

    private String nombrePlataforma;
    private String version;
    private double tasaIVA;
    private String moneda;
    private String urlConexionBD;
    private boolean modoMantenimiento;
    private int limiteMaximoItemsCarrito;

    /**
     * Constructor privado para prevenir instanciación externa directa (Singleton).
     */
    private ConfiguracionSistema() {
        this.nombrePlataforma = "CompraYa e-Commerce Platform";
        this.version = "7.0.2026";
        this.tasaIVA = 0.19; // 19% IVA Colombia
        this.moneda = "COP";
        this.urlConexionBD = "jdbc:postgresql://db.compraya.internal:5432/compraya_prod";
        this.modoMantenimiento = false;
        this.limiteMaximoItemsCarrito = 50;
    }

    /**
     * Obtiene la única instancia activa de ConfiguracionSistema utilizando Double-Checked Locking para hilo-seguridad.
     * 
     * @return La instancia única global de ConfiguracionSistema.
     */
    public static ConfiguracionSistema getInstancia() {
        if (instancia == null) {
            synchronized (ConfiguracionSistema.class) {
                if (instancia == null) {
                    instancia = new ConfiguracionSistema();
                }
            }
        }
        return instancia;
    }

    public void mostrarConfiguracionGlobal() {
        System.out.println("=======================================================");
        System.out.println(" ⚙️ CONFIGURACIÓN GLOBAL DEL SISTEMA (SINGLETON)       ");
        System.out.println("=======================================================");
        System.out.println("Plataforma:      " + nombrePlataforma);
        System.out.println("Versión:         " + version);
        System.out.println("Moneda Base:     " + moneda);
        System.out.printf("Tasa IVA:        %.2f%%\n", tasaIVA * 100);
        System.out.println("URL BD:          " + urlConexionBD);
        System.out.println("Mantenimiento:   " + (modoMantenimiento ? "ACTIVADO" : "DESACTIVADO (Operativo)"));
        System.out.println("Máx Items Cart:  " + limiteMaximoItemsCarrito);
        System.out.println("Hash Instancia:  " + System.identityHashCode(this));
        System.out.println("=======================================================");
    }

    // --- Getters y Setters Encapsulados ---

    public String getNombrePlataforma() { return nombrePlataforma; }
    public void setNombrePlataforma(String nombrePlataforma) { this.nombrePlataforma = nombrePlataforma; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }

    public double getTasaIVA() { return tasaIVA; }
    public void setTasaIVA(double tasaIVA) { this.tasaIVA = tasaIVA; }

    public String getMoneda() { return moneda; }
    public void setMoneda(String moneda) { this.moneda = moneda; }

    public String getUrlConexionBD() { return urlConexionBD; }
    public void setUrlConexionBD(String urlConexionBD) { this.urlConexionBD = urlConexionBD; }

    public boolean isModoMantenimiento() { return modoMantenimiento; }
    public void setModoMantenimiento(boolean modoMantenimiento) { this.modoMantenimiento = modoMantenimiento; }

    public int getLimiteMaximoItemsCarrito() { return limiteMaximoItemsCarrito; }
    public void setLimiteMaximoItemsCarrito(int limiteMaximoItemsCarrito) { this.limiteMaximoItemsCarrito = limiteMaximoItemsCarrito; }
}
