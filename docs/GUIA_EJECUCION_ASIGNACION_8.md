# Guía de Compilación y Ejecución - Asignación No. 8: Manejo de Excepciones y Pruebas Unitarias

Esta guía detalla los pasos para compilar, ejecutar y verificar el funcionamiento de la **Asignación No. 8** en la plataforma e-Commerce **CompraYa**.

---

## 📋 Requisitos Previos

1. **Java Development Kit (JDK 17 o superior)** instalado y configurado en las variables de entorno (`JAVA_HOME` y `PATH`).
2. Consola de comandos (PowerShell, CMD, Bash o Terminal de VS Code).

---

## 🛠️ Paso 1: Verificación de la Estructura del Proyecto

Asegúrese de que la siguiente estructura de archivos fuente esté disponible:

```text
src/com/compraya/asignacion8/
├── MainAsignacion8.java
├── exception/
│   ├── EcommerceException.java
│   ├── InventarioInsuficienteException.java
│   ├── PagoFallidoException.java
│   ├── ProductoNoEncontradoException.java
│   ├── StockInvalidoException.java
│   ├── UsuarioNoAutorizadoException.java
│   └── CarritoVacioException.java
├── model/
│   ├── Producto.java
│   ├── ProductoFisico.java
│   ├── ProductoDigital.java
│   ├── Usuario.java
│   ├── Cliente.java
│   ├── Administrador.java
│   ├── Carrito.java
│   ├── Pedido.java
│   └── ItemCarrito.java
├── inventory/
│   └── GestorInventario.java
├── payment/
│   ├── ProcesoPago.java
│   ├── PagoTarjeta.java
│   └── PagoPayPal.java
├── service/
│   └── EcommerceServiceAsignacion8.java
└── test/
    ├── TestFramework.java
    ├── ProductoTest.java
    ├── UsuarioTest.java
    ├── CarritoTest.java
    ├── InventarioTest.java
    ├── PagoTest.java
    └── TestRunnerAsignacion8.java
```

---

## 💻 Paso 2: Compilación de la Asignación 8

Abra una consola en la raíz del proyecto (`c:\ProyectosBIU\eCommerceCSE6041`) y ejecute:

```powershell
# En Windows PowerShell / CMD:
javac -d bin -sourcepath src src/com/compraya/asignacion8/MainAsignacion8.java
```

Si utiliza una versión específica de JDK (como el JBR de Android Studio / IntelliJ):

```powershell
& "C:\Program Files\Android\Android Studio\jbr\bin\javac.exe" -d bin -sourcepath src src/com/compraya/asignacion8/MainAsignacion8.java
```

---

## 🚀 Paso 3: Ejecución de las Pruebas Unitarias y Demostración

Para correr la suite completa de pruebas unitarias y los escenarios de excepción en tiempo de ejecución, ejecute:

```powershell
# En Windows PowerShell / CMD:
java -cp bin com.compraya.asignacion8.MainAsignacion8
```

O con la ruta directa a `java.exe`:

```powershell
& "C:\Program Files\Android\Android Studio\jbr\bin\java.exe" -cp bin com.compraya.asignacion8.MainAsignacion8
```

---

## 📊 Paso 4: Salida Esperada

Al ejecutar la aplicación, observará:
1. **Paso 1**: Ejecución automatizada de 31 pruebas unitarias con un 100% de tasa de éxito.
2. **Paso 2**: Demostración interactiva de captura de excepciones (`ProductoNoEncontradoException`, `InventarioInsuficienteException`, `PagoFallidoException` con pasarela de contingencia, `CarritoVacioException` y `UsuarioNoAutorizadoException`).

La salida completa generada se encuentra almacenada en [`docs/EJECUCION_OUTPUT_ASIGNACION_8.txt`](EJECUCION_OUTPUT_ASIGNACION_8.txt).
