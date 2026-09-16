# Documentación Teórica y Técnica: Interfaces y Clases Abstractas (Asignación 6)

**Curso:** CSE6041 – Object-Oriented Programming  
**Institución:** Broward International University (BIU)  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 1. Análisis Teórico: Clases Abstractas vs. Interfaces

### A. Clase Abstracta `GestorInventario`
- **Modelado:** Se utiliza una clase abstracta cuando existe un estado común o una relación de herencia fuerte ("es-un").
- **Atributos Compartidos:** `tipoInventario` y `totalArticulosProcesados`.
- **Contrato Obligatorio:** Métodos abstractos `añadirProducto()`, `eliminarProducto()`, `actualizarStock()` y `generarReporteInventario()`.

### B. Interfaz `ProcesoPago`
- **Modelado:** Se utiliza una interfaz cuando se requiere definir un contrato de comportamiento puro ("puede-hacer") sin compartir estado ni jerarquía.
- **Contrato Estándar:** `iniciarPago()`, `verificarPago()`, `confirmarPago()`, `getNombreMetodo()`.
- **Implementaciones:** `PagoTarjeta`, `PagoPayPal`, `PagoPSE`.

---

© 2026 **Alberto Alfonso López Pereira** - CSE6041
