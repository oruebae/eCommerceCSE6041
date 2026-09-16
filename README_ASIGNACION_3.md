# 🛒 CompraYa - Asignación No. 3: Extensión de Funcionalidades mediante Herencia

**Curso:** CSE6041 – Object-Oriented Programming
**Institución:** Broward International University (BIU)
**Estudiante:** Alberto Alfonso López Pereira
**Docente:** Dr. José Ignacio Requeno Jarabo

---

## 📋 Descripción del Proyecto

Esta entrega extiende la plataforma **CompraYa** aplicando el pilar de **Herencia** para
generalizar y especializar las funcionalidades de `Producto` y `Usuario`.

Se transformó `Producto` en una superclase con métodos diseñados explícitamente para ser
sobrescritos (`mostrarDetalle()`, `obtenerDetallesEspecificos()`, `requiereEnvioFisico()`), y se
crearon dos especializaciones —`ProductoFisico` y `ProductoDigital`— que modelan de forma
diferenciada la logística de entrega de cada tipo de artículo. Asimismo, se ampliaron las
especializaciones de `Usuario` ya existentes: `Cliente` ahora gestiona preferencias de compra y
recomendaciones personalizadas, mientras que `Administrador` gestiona promociones comerciales por
departamento.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje de Programación:** Java 21 (OpenJDK).
- **Paradigma:** Programación Orientada a Objetos (POO) — foco en **Herencia** y preparación para
  **Polimorfismo** (Semana 4).
- **Herramientas de Construcción:** `javac`, `java` (JDK estándar, sin dependencias externas).
- **Control de Versiones:** Git & GitHub.
- **Modelado:** UML 2.0 (diagrama de clases en sintaxis Mermaid).

---

## 🏗️ Estructura del Proyecto (archivos nuevos o modificados en esta entrega)

```
eCommerceCSE6041/
├── src/
│   └── com/
│       └── compraya/
│           ├── model/
│           │   ├── Producto.java              # ★ MODIFICADO — métodos virtuales para overriding
│           │   ├── ProductoFisico.java         # ★ NUEVO — extiende Producto
│           │   ├── ProductoDigital.java        # ★ NUEVO — extiende Producto
│           │   ├── Usuario.java                # (sin cambios)
│           │   ├── Cliente.java                # ★ MODIFICADO — + preferencias y recomendaciones
│           │   └── Administrador.java          # ★ MODIFICADO — + establecerPromocion()
│           ├── service/
│           │   └── EcommerceService.java       # ★ MODIFICADO — catálogo con productos especializados
│           ├── Main.java                       # (Asig. 1-2, sin cambios)
│           └── MainAsignacion3.java            # ★ NUEVO — demo de la Asignación 3
├── docs/
│   ├── DOCS_ARQUITECTURA_OOP_ASIGNACION_3.md   # Fundamento teórico de la herencia aplicada
│   ├── GUIA_EJECUCION_ASIGNACION_3.md          # Compilación, ejecución y publicación
│   └── EJECUCION_OUTPUT_ASIGNACION_3.txt       # Registro de salida de consola
├── README.md                     # Documentación de la Asignación No. 1
└── README_ASIGNACION_3.md        # Este documento
```

---

## 💻 Implementación de la Herencia

### 1. `Producto` — superclase preparada para sobreescritura

`src/com/compraya/model/Producto.java`

Se definieron tres métodos pensados para ser sobrescritos por las subclases:

- `mostrarDetalle()`: imprime la ficha técnica general; cada subclase la reemplaza con su propio
  formato.
- `obtenerDetallesEspecificos()`: retorna un resumen textual; la superclase retorna un valor
  genérico ("Producto General de Catálogo") que cada subclase reemplaza.
- `requiereEnvioFisico()`: retorna `true` por defecto; `ProductoDigital` lo sobrescribe a `false`.

### 2. `ProductoFisico extends Producto`

`src/com/compraya/model/ProductoFisico.java`

- **Atributos añadidos:** `pesoKg` (double), `dimensiones` (String), `costoEnvioBase` (double).
- **Constructor:** `ProductoFisico(id, nombre, descripcion, precio, stock, categoria, pesoKg,
  dimensiones, costoEnvioBase)`, que invoca `super(...)` para reutilizar las validaciones de
  `Producto`.
- **Comportamiento específico:**
  * `calcularCostoEnvio(String departamento)`: aplica un recargo por peso superior a 2 kg
    ($3.000/kg adicional) y un recargo regional fijo para San Andrés y Amazonas.
  * `mostrarDetalle()` (sobrescrito): ficha técnica con peso, dimensiones y flete estimado a
    Cundinamarca.
  * `obtenerDetallesEspecificos()` y `requiereEnvioFisico()` (sobrescritos, este último retorna
    `true`).

### 3. `ProductoDigital extends Producto`

`src/com/compraya/model/ProductoDigital.java`

- **Atributos añadidos:** `formatoArchivo` (String), `tamanoMB` (double), `urlDescarga` (String),
  `licencia` (String).
- **Constructor:** `ProductoDigital(id, nombre, descripcion, precio, stock, categoria,
  formatoArchivo, tamanoMB, urlDescarga, licencia)`, también con `super(...)`.
- **Comportamiento específico:**
  * `generarEnlaceDescarga()`: construye una URL de descarga con un token aleatorio de seguridad.
  * `mostrarDetalle()` (sobrescrito): ficha técnica con formato, tamaño, licencia y tipo de
    entrega.
  * `obtenerDetallesEspecificos()` y `requiereEnvioFisico()` (sobrescritos, este último retorna
    `false`).

### 4. `Cliente extends Usuario` — preferencias y recomendaciones

`src/com/compraya/model/Cliente.java`

- **Atributos añadidos:** `preferencias` (`List<String>`), `direccionPredeterminada`
  (`DireccionEnvio`).
- **Métodos nuevos:**
  * `agregarPreferencia(String preferencia)`: registra una categoría de interés evitando
    duplicados.
  * `obtenerRecomendaciones(List<Producto> catalogo)`: filtra el catálogo por las categorías
    preferidas del cliente; si no hay preferencias registradas, retorna los primeros 3 productos
    del catálogo como recomendación por defecto.

### 5. `Administrador extends Usuario` — promociones por departamento

`src/com/compraya/model/Administrador.java`

- **Atributo añadido:** `departamento` (String).
- **Método nuevo:** `establecerPromocion(Producto producto, double porcentajeDescuento)`, que
  delega en el método heredado `aplicarDescuento()` de `Producto` — funciona indistintamente sobre
  cualquier subclase (`ProductoFisico` o `ProductoDigital`) sin verificaciones `instanceof`.
- `generarReporteVentas()` amplía el reporte de la Asignación No. 2 incluyendo el departamento del
  administrador responsable.

### 6. Catálogo especializado (`EcommerceService`)

El catálogo de demostración ahora se construye exclusivamente con instancias de `ProductoFisico`
(Smartphone, Cafetera) y `ProductoDigital` (Curso Java OOP, E-Book), y el cliente de prueba se
inicializa con dos preferencias de categoría, evidenciando la especialización desde el arranque de
la aplicación.

---

## 📐 Diagrama de Clases UML Actualizado (Mermaid)

```
classDiagram
    class Producto {
        -int id
        -String nombre
        -double precio
        -int stock
        -Categoria categoria
        +actualizarStock(int cantidad) boolean
        +aplicarDescuento(double porcentaje)
        +mostrarDetalle()
        +obtenerDetallesEspecificos() String
        +requiereEnvioFisico() boolean
    }

    class ProductoFisico {
        -double pesoKg
        -String dimensiones
        -double costoEnvioBase
        +calcularCostoEnvio(String departamento) double
        +mostrarDetalle()
        +requiereEnvioFisico() boolean
    }

    class ProductoDigital {
        -String formatoArchivo
        -double tamanoMB
        -String urlDescarga
        -String licencia
        +generarEnlaceDescarga() String
        +mostrarDetalle()
        +requiereEnvioFisico() boolean
    }

    class Usuario {
        -int id
        -String nombre
        -String email
        -String password
        +iniciarSesion(String pass) boolean
        +actualizarPerfil(String, String)
        +destruirSesion()
    }

    class Cliente {
        -List~Pedido~ historialCompras
        -Carrito carrito
        -List~String~ preferencias
        -DireccionEnvio direccionPredeterminada
        +agregarPreferencia(String categoria)
        +obtenerRecomendaciones(List~Producto~) List~Producto~
    }

    class Administrador {
        -String nivelAcceso
        -String departamento
        +gestionarInventario(Producto p, int stock)
        +establecerPromocion(Producto p, double pct)
        +generarReporteVentas(double, int)
    }

    Usuario <|-- Cliente
    Usuario <|-- Administrador
    Producto <|-- ProductoFisico
    Producto <|-- ProductoDigital
```

---

## 📸 Capturas y Demostración de Ejecución

Salida real de `com.compraya.MainAsignacion3` (ver también
`docs/EJECUCION_OUTPUT_ASIGNACION_3.txt`):

```
====================================================================
          PLATAFORMA E-COMMERCE "COMPRAYA" - ASIGNACIÓN 3          
        Especialización mediante Herencia de Clases en Java         
====================================================================
Estudiante: Alberto Alfonso López Pereira
Docente:    Dr. José Ignacio Requeno Jarabo
Curso:      CSE6041 – Object-Oriented Programming (BIU)
====================================================================
[CLIENTE Alberto López] Nueva preferencia agregada: Software y Libros Digitales
[CLIENTE Alberto López] Nueva preferencia agregada: Electrónica

====================================================================
 1. ESPECIALIZACIÓN DE PRODUCTO (ProductoFisico y ProductoDigital) 
====================================================================
[CATÁLOGO DIVERSISFICADO CON HERENCIA]
  • Smartphone X Pro -> $1500000,00
    └ Detalles Polimórficos: Físico [Peso: 0,45 kg | Dim: 15x7x0.8 cm | Flete Base: $12000,00]
    └ [FÍSICO] Flete Bogotá: $12000,00 | Flete San Andrés: $27000,00

  • Cafetera Express -> $450000,00
    └ Detalles Polimórficos: Físico [Peso: 4,20 kg | Dim: 35x25x30 cm | Flete Base: $18000,00]
    └ [FÍSICO] Flete Bogotá: $24600,00 | Flete San Andrés: $39600,00

  • Curso Java OOP Avanzado -> $120000,00
    └ Detalles Polimórficos: Digital [Formato: MP4/ZIP | Tamaño: 2450,0 MB | Licencia: LIC-JAVA-2026-BIU]
    └ [DIGITAL] Enlace de descarga generado: https://cdn.compraya.com/cursos/java-oop?token=f0d56&licence=LIC-JAVA-2026-BIU

  • E-Book Arquitectura Software -> $45000,00
    └ Detalles Polimórficos: Digital [Formato: PDF/EPUB | Tamaño: 18,5 MB | Licencia: LIC-EBOOK-SINGLE]
    └ [DIGITAL] Enlace de descarga generado: https://cdn.compraya.com/ebooks/arch-design?token=7a475&licence=LIC-EBOOK-SINGLE

====================================================================
 2. ESPECIALIZACIÓN DE USUARIO (Cliente vs Administrador)          
====================================================================
[CLIENTE ESPECIALIZADO]
  • Cliente[ID: 1 | Nombre: Alberto López | Email: alberto.lopez@example.com | Preferencias: [Software y Libros Digitales, Electrónica] | Compras: 0]

[RECOMENDACIONES BASADAS EN PREFERENCIAS DEL CLIENTE]
  -> Recomendación para Alberto López: Smartphone X Pro (Electrónica)
  -> Recomendación para Alberto López: Curso Java OOP Avanzado (Software y Libros Digitales)
  -> Recomendación para Alberto López: E-Book Arquitectura Software (Software y Libros Digitales)

[ADMINISTRADOR ESPECIALIZADO - GESTIÓN DE PROMOCIONES]
  • Administrador[ID: 2 | Nombre: José Requeno | NivelAcceso: SUPERADMIN | Dpto: GESTION_CATALOGO]
[ADMIN José Requeno - GESTION_CATALOGO] Promoción aplicada a 'Curso Java OOP Avanzado': 15,0% de descuento. (Precio anterior: $120000,00 -> Nuevo: $102000,00)
[ADMIN José Requeno] Stock actualizado para 'Smartphone X Pro': 20 unidades.

====================================================================
 3. COMPRA MIXTA EN CARRITO (PRODUCTOS FÍSICOS + DIGITALES)        
====================================================================
[AÑADIENDO PRODUCTO FÍSICO Y DIGITAL AL CARRITO]
=== Carrito de Compras (Total: $1602000,00) ===
 - Smartphone X Pro x1 -> Subtotal: $1500000,00
 - Curso Java OOP Avanzado x1 -> Subtotal: $102000,00

[DIRECCIÓN REGISTRADA] Calle 100 #15-30, Bogotá, Cundinamarca (110221) - Colombia

[PROCESANDO CHECKOUT]
[PAGO] Procesando pago de $1906380,00 vía Pasarela Externa (PSE)...
[PAGO] Transacción aprobada exitosamente. ID de Transacción: TXN-5001
[PEDIDO #1001] Confirmado y pagado con éxito. Total: $1906380,00

==========================================
          DETALLE DE PEDIDO #1001           
==========================================
Cliente: Alberto López (alberto.lopez@example.com)
Estado: PAGADO
Dirección: Calle 100 #15-30, Bogotá, Cundinamarca (110221) - Colombia
Ítems Adquiridos:
  • Smartphone X Pro (ID: 1) x1 @ $1500000,00 = $1500000,00
  • Curso Java OOP Avanzado (ID: 3) x1 @ $102000,00 = $102000,00
------------------------------------------
 Subtotal:  $1602000,00
 IVA (19%): $304380,00
 TOTAL:     $1906380,00
==========================================

[ENTREGA Y LOGÍSTICA DIFERENCIADA EN EL PEDIDO]
  🚚 Producto Físico 'Smartphone X Pro' despachado a la transportadora hacia: Bogotá
  ✓ Producto Digital 'Curso Java OOP Avanzado' entregado de inmediato -> URL: https://cdn.compraya.com/cursos/java-oop?token=dee14&licence=LIC-JAVA-2026-BIU

====================================================================
 4. REPORTE DE VENTAS GENERADO POR EL ADMINISTRADOR                 
====================================================================
==========================================
     REPORTE DE VENTAS COMPRAYA (ASIG. 3) 
==========================================
Admin Responsable: José Requeno (SUPERADMIN - GESTION_CATALOGO)
Total Pedidos Procesados: 1
Ingresos Totales Acumulados: $1906380,00
==========================================

====================================================================
  ¡PRUEBA DE HERENCIA Y ESPECIALIZACIÓN COMPLETADA CON ÉXITO!      
====================================================================
```

> **Nota:** el separador decimal (coma o punto) depende de la configuración regional (`Locale`)
> por defecto de la máquina donde se ejecute; no afecta la lógica de negocio.

---

## ⚡ Desafíos Enfrentados y Soluciones Aplicadas

| Desafío Enfrentado | Causa Raíz | Solución Aplicada |
| --- | --- | --- |
| **Diferenciar el comportamiento de entrega sin usar `instanceof` en cada punto del código** | El checkout y la ficha técnica necesitan comportarse distinto para productos físicos y digitales. | Se declararon los métodos `mostrarDetalle()`, `obtenerDetallesEspecificos()` y `requiereEnvioFisico()` en `Producto` para que cada subclase los sobrescriba; solo el flujo de entrega en `MainAsignacion3` consulta `requiereEnvioFisico()` una única vez por ítem. |
| **Calcular el flete de forma justa según peso y destino** | Un flete fijo penaliza envíos livianos y no cubre el sobrecosto de regiones apartadas. | `calcularCostoEnvio(String departamento)` en `ProductoFisico` aplica un recargo por kilogramo excedente y un recargo fijo para departamentos insulares/remotos (San Andrés, Amazonas). |
| **Aplicar una promoción sin importar el tipo concreto de producto** | `establecerPromocion()` debía funcionar tanto para `ProductoFisico` como para `ProductoDigital`. | Se tipó el parámetro como `Producto` y se delegó en el método heredado `aplicarDescuento()`, común a toda la jerarquía. |
| **Recomendar productos relevantes sin un motor de recomendación complejo** | Se necesitaba una funcionalidad de valor para `Cliente` acorde al alcance de la asignación. | `obtenerRecomendaciones()` filtra el catálogo comparando la categoría de cada producto contra la lista de preferencias del cliente, con una lista por defecto si no hay preferencias registradas. |

---

## 🚀 Guía de Compilación y Ejecución

Ver `docs/GUIA_EJECUCION_ASIGNACION_3.md` para el detalle completo. Resumen:

```bash
mkdir -p bin
javac -d bin $(find src -name "*.java")
java -cp bin com.compraya.MainAsignacion3
```

---

## 📄 Documentación Adicional

- `docs/DOCS_ARQUITECTURA_OOP_ASIGNACION_3.md` — Fundamento teórico de generalización,
  especialización y cumplimiento del principio de sustitución de Liskov (LSP).
- `docs/GUIA_EJECUCION_ASIGNACION_3.md` — Instrucciones de compilación, ejecución y publicación.
- `docs/EJECUCION_OUTPUT_ASIGNACION_3.txt` — Registro completo en texto plano de la consola.

---

© 2026 **Alberto Alfonso López Pereira** - Broward International University. Todos los derechos reservados.