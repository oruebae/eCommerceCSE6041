# Documentación Teórica y Técnica: Patrones de Diseño (Asignación 7)

**Curso:** CSE6041 – Object-Oriented Programming  
**Institución:** Broward International University (BIU)  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 1. Análisis Teórico de los Patrones de Diseño GoF Implementados

### A. Patrón Singleton (`ConfiguracionSistema`)
- **Propósito:** Garantizar que una clase tenga una única instancia en todo el dominio de la aplicación y proporcionar un punto de acceso global a ella.
- **Implementación:** Constructor privado, variable estática `volatile` e implementación de *Double-Checked Locking* en `getInstancia()`.
- **Beneficio:** Evita discrepancias en parámetros globales como la tasa de IVA (19%) o cadenas de conexión a base de datos.

### B. Patrón Factory Method (`FabricaEntidades`)
- **Propósito:** Definir una interfaz para crear un objeto, pero dejar que la fábrica decida qué clase instanciar.
- **Implementación:** `FabricaEntidades.crearProducto(...)` y `FabricaEntidades.crearUsuario(...)` reciben el tipo deseado en un parámetro String y construyen `ProductoFisico`, `ProductoDigital`, `Cliente` o `Administrador`.
- **Beneficio:** Elimina el acoplamiento directo entre el código cliente y los constructores específicos.

### C. Patrón Observer (`GestorNotificaciones`, `ObservadorEvento`)
- **Propósito:** Definir una dependencia uno-a-muchos entre objetos de modo que cuando un objeto cambie de estado, todos sus dependientes sean notificados y actualizados automáticamente.
- **Implementación:** `GestorNotificaciones` administra una lista de `ObservadorEvento` y transmite eventos como `CAMBIO_ESTADO_PEDIDO` o `ALERTA_STOCK_BAJO`.
- **Beneficio:** Desacopla la lógica del pedido de la lógica de envío de emails, reserva de bodega física y actualización de dashboards UI.

---

© 2026 **Alberto Alfonso López Pereira** - CSE6041
