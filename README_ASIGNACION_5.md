🛒 CompraYa - Asignación No. 5: Encapsulamiento y Abstracción
Curso: CSE6041 – Object-Oriented Programming
Institución: Broward International University (BIU)
Estudiante: Alberto Alfonso López Pereira
Docente: Dr. José Ignacio Requeno Jarabo
---
📋 Descripción del Proyecto
Esta entrega refuerza el encapsulamiento de la plataforma CompraYa y aplica
abstracción avanzada mediante una clase base abstracta y dos interfaces, construyendo sobre
la jerarquía de herencia de las Asignaciones No. 3 y No. 4.
Se creó el paquete independiente `com.compraya.asignacion5`, que introduce la clase abstracta
`Item` —de la cual deriva `Producto`, y de esta a su vez `ProductoFisico` y `ProductoDigital`— y
dos interfaces (`Despachable`, `Licenciable`) que separan el comportamiento de logística física y
licenciamiento digital sin forzarlo sobre subclases que no lo necesitan. Adicionalmente, se
endurecieron las validaciones en los mutadores de `Producto`, `Usuario` y `Carrito`, de modo que
cualquier dato inválido (precio negativo, correo mal formado, contraseña débil, etc.) es
rechazado de inmediato con una excepción explicativa.
---
🛠️ Tecnologías Utilizadas
Lenguaje de Programación: Java 21 (OpenJDK).
Paradigma: Programación Orientada a Objetos (POO) — foco en Encapsulamiento y
Abstracción.
Herramientas de Construcción: `javac`, `java` (JDK estándar, sin dependencias externas).
Control de Versiones: Git & GitHub.
---
🏗️ Estructura del Proyecto (paquete nuevo de esta entrega)
```
eCommerceCSE6041/
├── src/
│   └── com/
│       └── compraya/
│           ├── asignacion5/                    # ★ NUEVO — paquete independiente de la Asignación 5
│           │   ├── model/
│           │   │   ├── Item.java                # ★ Clase abstracta base (Abstracción)
│           │   │   ├── Despachable.java          # ★ Interfaz — contrato de envío físico
│           │   │   ├── Licenciable.java          # ★ Interfaz — contrato de licenciamiento digital
│           │   │   ├── Producto.java             # extends Item
│           │   │   ├── ProductoFisico.java        # extends Producto implements Despachable
│           │   │   ├── ProductoDigital.java       # extends Producto implements Licenciable
│           │   │   ├── Usuario.java               # Encapsulamiento estricto + regex de email
│           │   │   ├── Cliente.java, Administrador.java
│           │   │   ├── Carrito.java               # Lista interna inmodificable desde fuera
│           │   │   ├── ItemCarrito.java, ItemPedido.java, Pedido.java, Pago.java, Categoria.java, DireccionEnvio.java
│           │   ├── service/
│           │   │   └── EcommerceServiceAsignacion5.java
│           │   └── MainAsignacion5.java          # ★ NUEVO — demo de la Asignación 5
│           ├── model/                            # (paquete original, Asignaciones 1-4, sin cambios)
│           ├── service/                          # (sin cambios)
│           ├── Main.java, MainAsignacion3.java, MainAsignacion4.java  # (sin cambios)
├── docs/
│   ├── DOCS_ARQUITECTURA_OOP_ASIGNACION_5.md     # Fundamento teórico
│   ├── GUIA_EJECUCION_ASIGNACION_5.md            # Compilación, ejecución y publicación
│   └── EJECUCION_OUTPUT_ASIGNACION_5.txt         # Registro de salida de consola
├── README.md, README_ASIGNACION_3.md, README_ASIGNACION_4.md
└── README_ASIGNACION_5.md        # Este documento
```
> **Nota de diseño:** se optó por un paquete `asignacion5` independiente (en vez de modificar en
> sitio las clases de `com.compraya.model`) para mantener ejecutables y verificables, de forma
> aislada, los entregables de todas las semanas anteriores.
---
💻 Implementación de Encapsulamiento y Abstracción
1. Abstracción: clase base `Item` e interfaces
`src/com/compraya/asignacion5/model/Item.java`
`Item` concentra los atributos comunes a cualquier bien vendible (`id`, `nombre`, `descripcion`,
`precio`, `stock`, `categoria`) y declara tres métodos abstractos que toda subclase concreta
debe implementar:
`mostrarDetalle()` — ficha técnica en consola.
`requiereEnvioFisico()` — indica si el ítem necesita logística de envío.
`obtenerDetallesEspecificos()` — resumen textual de los atributos propios de la subclase.
La jerarquía queda: `Item` (abstracta) → `Producto` (concreta, ficha genérica) →
`ProductoFisico` / `ProductoDigital` (fichas especializadas).
Para el comportamiento que no aplica a todas las subclases por igual, se usaron interfaces en
lugar de forzarlo en `Item`:
Interfaz	Contrato	Implementada por
`Despachable`	`calcularCostoEnvio(String)`, `getPesoKg()`, `getDimensiones()`	`ProductoFisico`
`Licenciable`	`generarEnlaceDescarga()`, `getLicencia()`, `getFormatoArchivo()`	`ProductoDigital`
`MainAsignacion5` recorre el catálogo como `List<Item>` y usa `instanceof Despachable` /
`instanceof Licenciable` para invocar únicamente el comportamiento adicional que corresponde a
cada producto — la abstracción no compromete la funcionalidad específica de las subclases.
2. Encapsulamiento estricto con validación en mutadores
Clase	Atributo	Regla de validación
`Item` / `Producto`	`precio`	No puede ser negativo.
`Item` / `Producto`	`stock`	No puede ser negativo.
`Item` / `Producto`	`nombre`	No puede estar vacío o nulo.
`ProductoFisico`	`pesoKg`	Debe ser mayor a 0 kg.
`ProductoDigital`	`tamanoMB`	Debe ser mayor a 0 MB.
`Usuario`	`email`	Debe cumplir un patrón de expresión regular (`usuario@dominio`).
`Usuario`	`password`	Debe tener al menos 6 caracteres.
Cada violación lanza `IllegalArgumentException` con un mensaje descriptivo prefijado
`[VALIDACIÓN ENCAPSULADA]`, capturable por el código cliente.
En `Carrito`, el encapsulamiento se refuerza exponiendo la lista interna de ítems únicamente a
través de `Collections.unmodifiableList()`, de modo que ningún código externo puede alterar el
contenido del carrito sin pasar por `agregarProducto()` / `removerProducto()`, que validan stock
y recalculan el total automáticamente.
---
📸 Capturas y Demostración de Ejecución
Salida real de `com.compraya.asignacion5.MainAsignacion5` (ver también
`docs/EJECUCION_OUTPUT_ASIGNACION_5.txt`):
```
====================================================================
          PLATAFORMA E-COMMERCE "COMPRAYA" - ASIGNACIÓN 5
         Aplicación de Encapsulamiento Estricto y Abstracción
====================================================================

====================================================================
 1. ABSTRACCIÓN AVANZADA: CLASE BASE ABSTRACTA 'ITEM' E INTERFACES
====================================================================
[CATÁLOGO PROCESADO MEDIANTE ABSTRACCIÓN DE ITEM]
-------------------------------------------------------
 [FIS] FICHA TÉCNICA DE PRODUCTO FÍSICO (ITEM)
-------------------------------------------------------
ID:            1
Nombre:        Smartphone X Pro
Precio:        $1500000,00
Peso Físico:   0,45 kg
Flete Bogotá:  $12000,00
   [INTERFACE Despachable] Peso: 0,45 kg | Flete Bogotá: $12000,00

-------------------------------------------------------
 [DIG] FICHA TÉCNICA DE PRODUCTO DIGITAL (ITEM)
-------------------------------------------------------
ID:            3
Nombre:        Curso Java OOP Avanzado
Formato:       MP4/ZIP
Licencia:      LIC-JAVA-2026-BIU
   [INTERFACE Licenciable] Licencia: LIC-JAVA-2026-BIU | Token: https://cdn.compraya.com/...

====================================================================
 2. ENCAPSULAMIENTO ESTRICTO Y VALIDACIÓN DE DATOS EN MUTADORES
====================================================================
[PRUEBA 2.1] Asignación de precio y stock válidos:
   [OK] Precio actualizado a: $1450000,00
   [OK] Stock actualizado a: 25 unidades

[PRUEBA 2.2 - CAPTURA DE EXCEPCIÓN] Asignación de precio negativo (-500.0):
   [!] Excepción capturada exitosamente: [VALIDACIÓN ENCAPSULADA] El precio ($-500,00) no puede ser negativo.

[PRUEBA 2.3 - CAPTURA DE EXCEPCIÓN] Asignación de stock negativo (-10):
   [!] Excepción capturada exitosamente: [VALIDACIÓN ENCAPSULADA] El stock (-10) no puede ser negativo.

[PRUEBA 2.4 - CAPTURA DE EXCEPCIÓN] Asignación de correo electrónico inválido ('correo-sin-arroba'):
   [!] Excepción capturada exitosamente: [VALIDACIÓN ENCAPSULADA] Formato de correo electrónico inválido: 'correo-sin-arroba'. Debe contener '@' y dominio válido.

[PRUEBA 2.5 - CAPTURA DE EXCEPCIÓN] Asignación de contraseña débil ('123'):
   [!] Excepción capturada exitosamente: [VALIDACIÓN ENCAPSULADA] La contraseña debe tener al menos 6 caracteres de longitud.

[PRUEBA 2.6] Asignación exitosa de credenciales válidas:
   [OK] Nombre:  Carlos Alberto López
   [OK] Email:   carlos.lopez.2026@empresa.com
   [OK] Estado:  Usuario encapsulado y validado correctamente

====================================================================
 3. OPERACIÓN DE CARRITO ENCAPSULADO Y CHECKOUT (ASIGNACIÓN 5)
====================================================================
=== Carrito de Compras Encapsulado (Total: $1690000,00) ===
 - Smartphone X Pro x1 -> Subtotal: $1450000,00
 - Curso Java OOP Avanzado x2 -> Subtotal: $240000,00

[PEDIDO #1001] Confirmado y pagado con éxito. Total: $2011100,00
 Subtotal:  $1690000,00
 IVA (19%): $321100,00
 TOTAL:     $2011100,00

Ingresos Totales Acumulados: $2011100,00

====================================================================
  ¡PRUEBA DE ENCAPSULAMIENTO Y ABSTRACCIÓN COMPLETADA CON ÉXITO!
====================================================================
```
> El separador decimal (coma o punto) depende de la configuración regional (`Locale`) de la
> máquina donde se ejecute; no afecta la lógica de negocio.
---
⚡ Desafíos Enfrentados y Soluciones Aplicadas
Desafío Enfrentado	Causa Raíz	Solución Aplicada
Abstraer propiedades comunes sin perder el comportamiento específico de cada tipo de producto	Forzar `calcularCostoEnvio()` o `generarEnlaceDescarga()` directamente en `Item` obligaría a todas las subclases a implementarlos, incluso las que no aplican.	Solo los tres métodos verdaderamente universales quedaron en `Item`; el comportamiento exclusivo se aisló en las interfaces `Despachable` y `Licenciable`, implementadas únicamente por la subclase que lo necesita.
Validar formato de correo electrónico de forma robusta	Una validación ingenua (buscar solo el carácter `@`) admite formatos inválidos.	Se definió un `Pattern` de expresión regular reutilizable como constante estática en `Usuario`, evaluado con `Matcher` dentro de `setEmail()`.
Evitar que código externo modifique el carrito sin pasar por las validaciones de stock	Exponer la lista de ítems directamente permitiría `list.add()`/`list.remove()` sin recalcular el total ni validar stock.	`getItems()` retorna la lista envuelta en `Collections.unmodifiableList()`, forzando toda modificación a través de los métodos encapsulados del `Carrito`.
Probar los casos de error sin detener la ejecución de la demostración	Una excepción no capturada terminaría abruptamente el programa antes de completar todas las pruebas.	`MainAsignacion5` envuelve cada mutador crítico en bloques `try/catch` que capturan `IllegalArgumentException`, imprimen el mensaje y continúan con la siguiente prueba.
---
🚀 Guía de Compilación y Ejecución
Ver `docs/GUIA_EJECUCION_ASIGNACION_5.md` para el detalle completo. Resumen:
```bash
mkdir -p bin
javac -d bin $(find src -name "*.java")
java -cp bin com.compraya.asignacion5.MainAsignacion5
```
---
📄 Documentación Adicional
`docs/DOCS_ARQUITECTURA_OOP_ASIGNACION_5.md` — Fundamento teórico de abstracción mediante
clase base e interfaces, y del encapsulamiento con validadores de negocio.
`docs/GUIA_EJECUCION_ASIGNACION_5.md` — Instrucciones de compilación, ejecución y publicación.
`docs/EJECUCION_OUTPUT_ASIGNACION_5.txt` — Registro completo en texto plano de la consola.
---
© 2026 Alberto Alfonso López Pereira - Broward International University. Todos los derechos reservados.