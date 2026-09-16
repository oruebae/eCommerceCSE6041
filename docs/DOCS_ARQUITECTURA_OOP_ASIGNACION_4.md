# Documentación Teórica y Técnica: Polimorfismo y Sobrecarga (Asignación 4)

**Curso:** CSE6041 – Object-Oriented Programming  
**Institución:** Broward International University (BIU)  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 1. Análisis Teórico de Sobrecarga vs. Sobreescritura

| Mecanismo POO | Tipo de Enlace (*Binding*) | Momento de Resolución | Ejemplo en CompraYa |
| :--- | :--- | :--- | :--- |
| **Sobrecarga (Overloading)** | Enlace Estático (*Early Binding*) | Tiempo de Compilación (*Compile-Time*) | Múltiples versiones de `agregarProducto(...)` en `Carrito.java` variando parámetros. |
| **Sobreescritura (Overriding)** | Enlace Dinámico (*Late Binding*) | Tiempo de Ejecución (*Runtime*) | Implementación de `@Override mostrarDetalle()` en `ProductoFisico` y `ProductoDigital`. |

---

## 2. Ventajas Arquitectónicas

1. **Flexibilidad en Interfaces (Sobrecarga):** Los desarrolladores y usuarios de la API de `Carrito` pueden insertar productos de forma intuitiva adaptándose a los datos disponibles (objeto completo, ID de catálogo o datos genéricos).
2. **Extensibilidad de Catálogo (Polimorfismo):** Se pueden añadir nuevos tipos de productos (ej. `ProductoSuscripcion`, `ProductoServicio`) sin modificar los métodos de carrito, pedido o facturación, respetando el principio Abierto/Cerrado (OCP).

---

© 2026 **Alberto Alfonso López Pereira** - CSE6041
