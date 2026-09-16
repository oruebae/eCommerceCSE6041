# Documentación Teórica y Técnica: Encapsulamiento y Abstracción (Asignación 5)

**Curso:** CSE6041 – Object-Oriented Programming  
**Institución:** Broward International University (BIU)  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 1. Análisis Teórico de Abstracción y Encapsulamiento

### A. Abstracción Mediante Clase Base `Item` e Interfaces
La abstracción consiste en reducir la complejidad ocultando los detalles irrelevantes de implementación y exponiendo solo las características esenciales.
- **Clase Abstracta `Item`:** Define un marco conceptual para cualquier bien comerciable en la plataforma. Impone a las subclases concretas la obligación de implementar los métodos `mostrarDetalle()`, `requiereEnvioFisico()` y `obtenerDetallesEspecificos()`.
- **Interfaces `Despachable` y `Licenciable`:** Separan los contratos de comportamiento de logística física y licenciamiento digital, desacoplando la implementación.

### B. Encapsulamiento con Validadores de Negocio
El encapsulamiento protege la integridad del objeto restringiendo el acceso directo a sus atributos privados y canalizando todas las modificaciones mediante métodos mutadores (`setters`) que aplican reglas de validación:
- **`Producto` / `Item`:** `setPrecio()` e `setStock()` garantizan invariantes financieras (evitan valores negativos).
- **`Usuario`:** `setEmail()` valida la estructura mediante expresiones regulares y `setPassword()` aplica políticas de seguridad.

---

© 2026 **Alberto Alfonso López Pereira** - CSE6041
