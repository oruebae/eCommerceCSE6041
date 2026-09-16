# Documentación de Arquitectura de Software - Asignación No. 8: Manejo de Excepciones y Pruebas Unitarias

**Asignatura**: CSE6041 – Object-Oriented Programming  
**Institución**: Broward International University (BIU)  
**Proyecto**: Plataforma e-Commerce CompraYa  

---

## 🎯 1. Filosofía de Manejo de Excepciones Orientado a Objetos

El manejo de excepciones en lenguajes orientados a objetos como Java tiene dos objetivos principales:
1. **Separación de Intereses (Separation of Concerns)**: Aislar el flujo normal de ejecución de la lógica de tratamiento de errores.
2. **Resiliencia y Tolerancia a Fallos**: Garantizar que situaciones anómalas (falta de fondos, desabastecimiento de stock, accesos no autorizados) no colapsen el sistema, sino que permitan una **recuperación graciosa** o informen de manera clara al usuario.

### 1.1 Excepciones Verificadas (Checked) vs. No Verificadas (Unchecked)

En Java:
- **Checked Exceptions** (`Exception`): Exigen declaración explícita en la firma del método (`throws`). Son útiles para fallos externos recuperables fuera del control directo de la aplicación (ej. red, E/S).
- **Unchecked Exceptions** (`RuntimeException`): No fuerzan la declaración explícita, simplificando el código cliente y permitiendo la propagación limpia en capas de servicio y controladores.

En **CompraYa (Asignación 8)**, se adoptó una jerarquía basada en **Unchecked Exceptions** (`EcommerceException extends RuntimeException`), enriquecida con:
- `codigoError` (String único para clasificación de registros de auditoría).
- `timestamp` (Marca de tiempo milimétrica en que ocurrió la excepción).

```text
java.lang.Object
  └── java.lang.Throwable
        └── java.lang.Exception
              └── java.lang.RuntimeException
                    └── com.compraya.asignacion8.exception.EcommerceException
                          ├── InventarioInsuficienteException
                          ├── PagoFallidoException
                          ├── ProductoNoEncontradoException
                          ├── StockInvalidoException
                          ├── UsuarioNoAutorizadoException
                          └── CarritoVacioException
```

---

## 🧪 2. Principios de Pruebas Unitarias (Unit Testing)

Las pruebas unitarias validan la menor unidad de código evaluable de manera aislada (métodos y clases).

### 2.1 Principios F.I.R.S.T. Aplicados

1. **Fast (Rápidas)**: La suite completa de 31 pruebas ejecuta en menos de 50 milisegundos.
2. **Independent (Independientes)**: Cada método de prueba en `ProductoTest`, `UsuarioTest`, `CarritoTest`, `InventarioTest` y `PagoTest` instancia sus propios datos sin compartir estado mutable global.
3. **Repeatable (Repetibles)**: La ejecución produce exactamente el mismo resultado del 100% de pases en cualquier sistema con Java 17+.
4. **Self-Validating (Auto-validables)**: `TestFramework.java` verifica automáticamente el resultado esperado vs. actual sin requerir inspección manual del usuario.
5. **Timely (Oportunas)**: Diseñadas en sintonía con la implementación de las clases de modelo y servicios.

### 2.2 Patrón AAA (Arrange - Act - Assert)

Todas las pruebas escritas siguen el patrón AAA:
```java
// 1. Arrange (Preparación)
GestorInventario gestor = new GestorInventario();
ProductoFisico monitor = new ProductoFisico(301, "Monitor 27 UHD", "4K", 1200000.0, 8, 4.5, "60x40 cm");
gestor.registrarProducto(monitor);

// 2. Act (Acción)
gestor.descontarStock(301, 3);

// 3. Assert (Aserción)
TestFramework.assertEquals(5, gestor.getStockActual(301), "Stock descontado correctamente de 8 a 5");
```

---

## 🔄 3. Estrategias de Recuperación de Errores Implementadas

### 3.1 Fallback de Pasarela de Pagos
Si la transacción en la pasarela principal falla por límite de crédito excedido (`PagoFallidoException`), la capa de servicio `EcommerceServiceAsignacion8` no falla de inmediato; en su lugar, intenta ejecutar el cobro de forma transparente mediante una pasarela secundaria o de contingencia (`PagoPayPal`), garantizando la tasa de conversión de compras en la plataforma.

### 3.2 Rollback Automático de Inventario
Si ambas pasarelas de pago rechazan el cobro, el sistema captura la excepción final y realiza un **Rollback de Inventario**, restituyendo las unidades previamente descontadas en bodega para que no queden bloqueadas o perdidas.
