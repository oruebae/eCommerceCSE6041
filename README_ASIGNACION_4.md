
# 🛒 CompraYa - Asignación No. 4: Polimorfismo y Sobrecarga

**Curso:** CSE6041 – Object-Oriented Programming
**Institución:** Broward International University (BIU)
**Estudiante:** Alberto Alfonso López Pereira
**Docente:** Dr. José Ignacio Requeno Jarabo

---

## 📋 Descripción del Proyecto

Esta entrega aplica **polimorfismo** y **sobrecarga de métodos** (*method overloading*) sobre la
plataforma **CompraYa**, construyendo directamente sobre la jerarquía de herencia de `Producto` y
`Usuario` establecida en la Asignación No. 3.

Se demuestra el **polimorfismo de invocación** recorriendo el catálogo como una lista heterogénea
de `Producto` e invocando métodos sobrescritos (`mostrarDetalle()`, `requiereEnvioFisico()`) sin
que el código cliente conozca el tipo concreto de cada objeto. Adicionalmente, se aplicó
**sobrecarga de métodos** en `Carrito`, ofreciendo cinco formas distintas de agregar un producto y
dos de removerlo, todas resueltas en tiempo de compilación según la firma invocada.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje de Programación:** Java 21 (OpenJDK).
- **Paradigma:** Programación Orientada a Objetos (POO) — foco en **Polimorfismo** y
  **Sobrecarga**.
- **Herramientas de Construcción:** `javac`, `java` (JDK estándar, sin dependencias externas).
- **Control de Versiones:** Git & GitHub.

---

## 🏗️ Estructura del Proyecto (archivos nuevos o modificados en esta entrega)

```
eCommerceCSE6041/
├── src/
│   └── com/
│       └── compraya/
│           ├── model/
│           │   ├── Producto.java              # (Asig. 3, sin cambios)
│           │   ├── ProductoFisico.java         # (Asig. 3, sin cambios)
│           │   ├── ProductoDigital.java        # (Asig. 3, sin cambios)
│           │   ├── Carrito.java                # ★ MODIFICADO — sobrecarga de agregarProducto()/removerProducto()
│           │   ├── Cliente.java                # (Asig. 3, sin cambios)
│           │   └── Administrador.java          # (Asig. 3, sin cambios)
│           ├── Main.java                       # (Asig. 1-2, sin cambios)
│           ├── MainAsignacion3.java            # (Asig. 3, sin cambios)
│           └── MainAsignacion4.java            # ★ NUEVO — demo de la Asignación 4
├── docs/
│   ├── DOCS_ARQUITECTURA_OOP_ASIGNACION_4.md   # Fundamento teórico: sobrecarga vs. sobreescritura
│   ├── GUIA_EJECUCION_ASIGNACION_4.md          # Compilación, ejecución y publicación
│   └── EJECUCION_OUTPUT_ASIGNACION_4.txt       # Registro de salida de consola
├── README.md                     # Documentación de la Asignación No. 1
├── README_ASIGNACION_3.md        # Documentación de la Asignación No. 3
└── README_ASIGNACION_4.md        # Este documento
```

---

## 💻 Implementación de Polimorfismo y Sobrecarga

### 1. Polimorfismo: sobreescritura de `mostrarDetalle()`

`MainAsignacion4.java` recorre `List<Producto> catalogo` (que contiene indistintamente objetos
`ProductoFisico` y `ProductoDigital`) invocando `prod.mostrarDetalle()` sobre la referencia de
tipo base. En tiempo de ejecución, la JVM despacha dinámicamente la versión sobrescrita
correspondiente a la clase real de cada objeto:

```java
for (Producto prod : catalogo) {
    prod.mostrarDetalle(); // Ejecuta la versión sobrescrita específica de cada subclase
}
```

El método privado `procesarProductosPolimorficos()` refuerza el mismo principio, consultando
`p.requiereEnvioFisico()` sobre cada elemento **sin condicionales `instanceof`** para clasificar
el catálogo en físicos/digitales y calcular el valor total del inventario.

### 2. Sobrecarga de métodos en `Carrito`

`src/com/compraya/model/Carrito.java`

`agregarProducto()` se sobrecargó en cinco variantes, cada una resuelta en tiempo de compilación
según la firma de parámetros:

| Firma | Comportamiento |
| --- | --- |
| `agregarProducto(Producto p)` | Agrega 1 unidad por defecto a partir de un objeto `Producto`. |
| `agregarProducto(Producto p, int cantidad)` | Agrega la cantidad especificada a partir de un objeto `Producto`. |
| `agregarProducto(int productoId, EcommerceService service)` | Busca el producto por ID en el catálogo del servicio y agrega 1 unidad. |
| `agregarProducto(int productoId, int cantidad, EcommerceService service)` | Busca el producto por ID y agrega la cantidad indicada. |
| `agregarProducto(String nombre, double precio, int cantidad, Categoria cat)` | Crea dinámicamente un producto genérico y lo agrega al carrito. |

Las primeras cuatro variantes convergen internamente en `agregarProducto(Producto, int)`,
evitando duplicar la validación de stock y el recálculo del total. De forma análoga,
`removerProducto(int productoId)` y `removerProducto(Producto producto)` se sobrecargaron para
aceptar tanto el identificador como el objeto completo.

---

## 📸 Capturas y Demostración de Ejecución

Salida real de `com.compraya.MainAsignacion4` (ver también
`docs/EJECUCION_OUTPUT_ASIGNACION_4.txt`):

```
====================================================================
          PLATAFORMA E-COMMERCE "COMPRAYA" - ASIGNACIÓN 4          
         Demostración de Polimorfismo, Sobrecarga y Sobreescritura  
====================================================================
Estudiante: Alberto Alfonso López Pereira
Docente:    Dr. José Ignacio Requeno Jarabo
Curso:      CSE6041 – Object-Oriented Programming (BIU)
====================================================================

====================================================================
 1. DEMOSTRACIÓN DE SOBREESCRITURA DE MÉTODOS (mostrarDetalle())  
====================================================================
[FICHA TÉCNICA DE PRODUCTO FÍSICO] Smartphone X Pro — Precio: $1500000,00 — Flete Bogotá: $12000,00
[FICHA TÉCNICA DE PRODUCTO FÍSICO] Cafetera Express  — Precio: $450000,00  — Flete Bogotá: $24600,00
[FICHA TÉCNICA DE PRODUCTO DIGITAL] Curso Java OOP Avanzado — Formato: MP4/ZIP — Tamaño: 2450,0 MB
[FICHA TÉCNICA DE PRODUCTO DIGITAL] E-Book Arquitectura Software — Formato: PDF/EPUB — Tamaño: 18,5 MB

====================================================================
 2. DEMOSTRACIÓN DE SOBRECARGA DE MÉTODOS (agregarProducto())     
====================================================================
[VARIACIÓN 1] agregarProducto(Producto p) -> true
[VARIACIÓN 2] agregarProducto(Producto p, int cantidad) -> true
[VARIACIÓN 3] agregarProducto(int productoId, EcommerceService service) -> true
[VARIACIÓN 4] agregarProducto(int productoId, int cantidad, EcommerceService service) -> true
[VARIACIÓN 5] agregarProducto(String nombre, double precio, int cantidad, Categoria cat) -> true

[ESTADO DEL CARRITO TRAS SOBRECARGA]
=== Carrito de Compras (Total: $2410000,00) ===
 - Smartphone X Pro x1 -> Subtotal: $1500000,00
 - Curso Java OOP Avanzado x2 -> Subtotal: $240000,00
 - Cafetera Express x1 -> Subtotal: $450000,00
 - E-Book Arquitectura Software x3 -> Subtotal: $135000,00
 - Soporte Ergonómico Laptop x1 -> Subtotal: $85000,00

[CARRITO TRAS REMOCIONES SOBRECARGADAS]
=== Carrito de Compras (Total: $460000,00) ===
 - Curso Java OOP Avanzado x2 -> Subtotal: $240000,00
 - E-Book Arquitectura Software x3 -> Subtotal: $135000,00
 - Soporte Ergonómico Laptop x1 -> Subtotal: $85000,00

====================================================================
 3. TRATAMIENTO POLIMÓRFICO DE OBJETOS DERIVADOS                  
====================================================================
  -> ID: 1 | Smartphone X Pro          | Precio: $1500000,00 | EnvioFisico: true
  -> ID: 2 | Cafetera Express          | Precio: $450000,00  | EnvioFisico: true
  -> ID: 3 | Curso Java OOP Avanzado   | Precio: $120000,00  | EnvioFisico: false
  -> ID: 4 | E-Book Arquitectura Software | Precio: $45000,00 | EnvioFisico: false
 Resumen Polimórfico -> Físicos: 2 | Digitales: 2 | Valor Inventario: $191835000,00

====================================================================
 4. CHECKOUT Y FACTURACIÓN CON IMPUESTOS (ASIGNACIÓN 4)            
====================================================================
[PEDIDO #1001] Confirmado y pagado con éxito. Total: $547400,00
 Subtotal:  $460000,00
 IVA (19%): $87400,00
 TOTAL:     $547400,00

Ingresos Totales Acumulados: $547400,00

====================================================================
  ¡PRUEBA DE POLIMORFISMO Y SOBRECARGA COMPLETADA EXITOSAMENTE!    
====================================================================
```

> Registro completo, sin resumir, disponible en `docs/EJECUCION_OUTPUT_ASIGNACION_4.txt`.
> El separador decimal (coma o punto) depende de la configuración regional (`Locale`) de la
> máquina donde se ejecute; no afecta la lógica de negocio.

---

## ⚡ Desafíos Enfrentados y Soluciones Aplicadas

| Desafío Enfrentado | Causa Raíz | Solución Aplicada |
| --- | --- | --- |
| **Ofrecer varias formas naturales de agregar un producto sin duplicar lógica de negocio** | Distintos contextos de uso disponen de distinta información (objeto completo, solo el ID, o datos sueltos). | Las 4 primeras sobrecargas de `agregarProducto()` delegan en la variante `(Producto, int)`, que concentra la validación de stock y el recálculo del total. |
| **Permitir insertar un producto ad-hoc que no existe en el catálogo** | A veces se requiere añadir un artículo genérico (p. ej. un accesorio) sin registrarlo previamente en `EcommerceService`. | Se sobrecargó `agregarProducto(String, double, int, Categoria)`, que construye un `Producto` temporal con ID generado a partir de la marca de tiempo del sistema. |
| **Clasificar y valorar el catálogo sin acoplarse a los tipos concretos de Producto** | Un enfoque con `instanceof` en cada punto del código sería frágil ante nuevos tipos de producto. | `procesarProductosPolimorficos()` usa únicamente los métodos declarados en la superclase (`getPrecio()`, `requiereEnvioFisico()`), aprovechando el despacho dinámico de la JVM. |

---

## 🚀 Guía de Compilación y Ejecución

Ver `docs/GUIA_EJECUCION_ASIGNACION_4.md` para el detalle completo. Resumen:

```bash
mkdir -p bin
javac -d bin $(find src -name "*.java")
java -cp bin com.compraya.MainAsignacion4
```

---

## 📄 Documentación Adicional

- `docs/DOCS_ARQUITECTURA_OOP_ASIGNACION_4.md` — Tabla comparativa sobrecarga vs. sobreescritura y
  ventajas arquitectónicas (flexibilidad de interfaz, extensibilidad OCP).
- `docs/GUIA_EJECUCION_ASIGNACION_4.md` — Instrucciones de compilación, ejecución y publicación.
- `docs/EJECUCION_OUTPUT_ASIGNACION_4.txt` — Registro completo en texto plano de la consola.

---

© 2026 **Alberto Alfonso López Pereira** - Broward International University. Todos los derechos reservados.
