# Documentación Teórica y Técnica de Herencia - Asignación 3

**Curso:** CSE6041 – Object-Oriented Programming  
**Institución:** Broward International University (BIU)  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 1. Justificación Teórica de la Generalización y Especialización

El principio de **Generalización y Especialización** es fundamental en el diseño orientado a objetos. Permite reutilizar código común en superclases e implementar comportamientos específicos en subclases.

### A. Jerarquía de Productos (`Producto`)
- **Generalización (`Producto`):** Todos los productos poseen identificador (`id`), `nombre`, `descripcion`, `precio` y `stock`.
- **Especialización Física (`ProductoFisico`):** Incorpora el peso (`pesoKg`) y volumen/dimensiones (`dimensiones`) requeridos para la logística de almacenamiento e integra el cálculo dinamizado de flete de envío (`calcularCostoEnvio`).
- **Especialización Digital (`ProductoDigital`):** Modela bienes sin peso físico que no ocupan espacio en bodega. Reemplaza el flete por entrega inmediata mediante URLs seguras con tokens dinámicos (`generarEnlaceDescarga`) y licenciamiento explícito.

### B. Jerarquía de Usuarios (`Usuario`)
- **Generalización (`Usuario`):** Agrupa atributos de autenticación (`email`, `password`) y perfil básico.
- **Especialización Comprador (`Cliente`):** Añade preferencias de consumo (`preferencias`), recomendaciones personalizadas y dirección habitual de entrega.
- **Especialización Administrador (`Administrador`):** Añade facultades corporativas como asignación de promociones comerciales (`establecerPromocion`), supervisión de inventario y generación de reportes de ventas por departamento.

---

## 2. Cumplimiento del Principio de Sustitución de Liskov (LSP)

El diseño implementado cumple con el principio LSP: cualquier subclase (`ProductoFisico` o `ProductoDigital`) puede sustituir a la superclase (`Producto`) dentro del `Carrito` de compras o la lista de catálogo sin alterar el funcionamiento del sistema.

---

© 2026 **Alberto Alfonso López Pereira** - CSE6041
