# Documentación Conceptual de Arquitectura POO - CompraYa

**Asignación No. 1 - Diseño Conceptual e Implementación**  
**Curso:** CSE6041 – Object-Oriented Programming  
**Institución:** Broward International University (BIU)  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 1. Mapeo de Requerimientos Funcionales (RF)

| ID | Requerimiento Funcional | Clase(s) Responsable(s) | Método / Atributo Clave |
| :--- | :--- | :--- | :--- |
| **RF-01** | Registro e inicio de sesión de usuarios. | `Usuario`, `Cliente`, `Administrador` | `iniciarSesion(String pass)`, `actualizarPerfil(...)` |
| **RF-02** | Búsqueda y filtrado de productos. | `EcommerceService`, `Producto`, `Categoria` | `buscarProductosPorNombre()`, `filtrarProductosPorCategoria()` |
| **RF-03** | Visualización de detalles del producto. | `Producto` | `getNombre()`, `getDescripcion()`, `getPrecio()`, `getStock()` |
| **RF-04** | Gestión del carrito de compras. | `Carrito`, `ItemCarrito` | `agregarProducto()`, `removerProducto()`, `modificarCantidad()` |
| **RF-05** | Cálculo automático de subtotales e impuestos. | `Carrito`, `Pedido` | `calcularTotal()`, `subtotal`, `impuestos` (IVA 19%) |
| **RF-06** | Generación y confirmación de pedidos. | `Pedido`, `Cliente` | `crearDesdeCarrito()`, `confirmarPedido()` |
| **RF-07** | Registro de dirección de envío. | `DireccionEnvio` | Attributes: `calle`, `ciudad`, `departamento`, `codigoPostal` |
| **RF-08** | Procesamiento de pagos vía pasarela externa. | `Pago` | `procesarPago()`, `verificarPago()` |
| **RF-09** | Consulta del historial de pedidos. | `Cliente`, `Pedido` | `getHistorialCompras()`, `agregarPedidoAlHistorial()` |
| **RF-10** | Gestión de inventario (alta/baja stock). | `Producto`, `Administrador` | `actualizarStock(int cant)`, `gestionarInventario()` |
| **RF-11** | Actualización de estado del pedido. | `Pedido`, `Administrador` | `actualizarEstado(String nuevoEstado)` |
| **RF-13** | Generación de reportes de ventas. | `Administrador`, `EcommerceService` | `generarReporte()`, `calcularTotalVentasPlataforma()` |

---

## 2. Cobertura de Requerimientos No Funcionales (RNF)

- **RNF-03 (Seguridad):** Cifrado y ocultamiento de credenciales encapsulando las variables `password` dentro de la clase `Usuario`, restringiendo el acceso directo mediante la visibilidad `private`.
- **RNF-06 (Mantenibilidad):** Diseño orientado a objetos de bajo acoplamiento y alta cohesión. Clases independientes con responsabilidades únicas (SRP).
- **RNF-08 (Portabilidad):** Escrito en código estándar Java sin dependencias externas propietarias, ejecutable en cualquier entorno multiplataforma (Windows, Linux, macOS) con JDK 17+.
- **RNF-09 (Auditabilidad):** Trazabilidad de operaciones mediante salida estructurada y registro de fechas (`LocalDateTime`) en objetos `Usuario`, `Carrito` y `Pedido`.

---

## 3. Decisiones de Diseño POO

### A. Herencia (`Usuario` -> `Cliente` / `Administrador`)
Se utilizó una clase base `Usuario` que encapsula la identidad, correo, contraseña y estado de sesión. La herencia evita duplicar lógica de autenticación entre compradores (`Cliente`) y gestores de la plataforma (`Administrador`).

### B. Composición vs. Asociación
- **Composición Fuerte:** `Carrito` posee la lista de `ItemCarrito`, y `Pedido` posee la lista de `ItemPedido`. Si el carrito o pedido se destruye, sus ítems asociados se destruyen de la memoria.
- **Asociación Débil:** `Producto` se relaciona con `Categoria`, `ItemCarrito` y `ItemPedido`. Un `Producto` existe de forma independiente a que esté o no dentro de un carrito específico.

### C. Inmutabilidad en Pedidos Confirmados
Para garantizar que los cambios de precio o la eliminación de productos en el catálogo no alteren transacciones pasadas, `ItemPedido` guarda una copia inmutable del valor negociado en el momento exacto del checkout.

---

© 2026 **Alberto Alfonso López Pereira** - CSE6041
