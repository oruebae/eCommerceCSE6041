# Guía de Compilación y Ejecución - Asignación No. 7: Patrones de Diseño (Singleton, Factory y Observer)

Esta guía detalla los pasos para compilar, ejecutar y verificar el funcionamiento de la **Asignación No. 7** en el proyecto **CompraYa**, donde se han implementado los patrones de diseño GoF: **Singleton**, **Factory** y **Observer**.

---

## 📋 Requisitos Previos

1. **Java Development Kit (JDK 17 o superior)** instalado y configurado en las variables de entorno (`JAVA_HOME` y `PATH`).
2. Consola de comandos (PowerShell, CMD, Bash o Terminal de VS Code).

---

## 🛠️ Paso 1: Verificación de la Estructura de Proyecto

Asegúrese de que el proyecto contenga los siguientes archivos fuente:

```text
src/com/compraya/asignacion7/
├── MainAsignacion7.java
├── config/
│   └── ConfiguracionSistema.java
├── factory/
│   └── FabricaEntidades.java
├── model/
│   ├── Carrito.java
│   ├── Cliente.java
│   ├── Pedido.java
│   ├── Producto.java
│   ├── ProductoDigital.java
│   ├── ProductoFisico.java
│   └── Usuario.java
├── observer/
│   ├── GestorNotificaciones.java
│   ├── NotificadorClienteEmail.java
│   ├── NotificadorModuloInventario.java
│   ├── NotificadorPanelUI.java
│   ├── ObservadorEvento.java
│   └── SujetoNotificable.java
└── service/
    └── EcommerceServiceAsignacion7.java
```

---

## 💻 Paso 2: Compilación de la Asignación 7

Abra una consola en la raíz del proyecto (`c:\ProyectosBIU\eCommerceCSE6041`) y ejecute el siguiente comando para compilar todos los archivos `.java` en el directorio `bin`:

```powershell
# En Windows PowerShell / CMD:
javac -d bin -sourcepath src src/com/compraya/asignacion7/MainAsignacion7.java
```

Si utiliza una versión específica de JDK (como el JBR incluido en Android Studio / IntelliJ):

```powershell
& "C:\Program Files\Android\Android Studio\jbr\bin\javac.exe" -d bin -sourcepath src src/com/compraya/asignacion7/MainAsignacion7.java
```

---

## 🚀 Paso 3: Ejecución de la Aplicación

Para ejecutar la prueba de la Asignación 7, corra el siguiente comando:

```powershell
# En Windows PowerShell / CMD:
java -cp bin com.compraya.asignacion7.MainAsignacion7
```

O usando la ruta directa a `java.exe`:

```powershell
& "C:\Program Files\Android\Android Studio\jbr\bin\java.exe" -cp bin com.compraya.asignacion7.MainAsignacion7
```

---

## 📊 Paso 4: Salida Esperada de la Ejecución

Al ejecutar la aplicación, observará la demostración interactiva de los 3 patrones de diseño:

1. **Patrón Singleton**: Verificación de instancia única de `ConfiguracionSistema` mediante comparación por identidad (`config1 == config2`).
2. **Patrón Factory**: Creación dinámica de `ProductoFisico`, `ProductoDigital`, `Cliente` y `Administrador` usando `FabricaEntidades`.
3. **Patrón Observer**:
   - Transiciones del estado del pedido (`CREADO` -> `EN_PROCESO` -> `ENVIADO` -> `ENTREGADO`).
   - Notificación de alerta por bajo stock cuando se actualizan existencias por debajo del umbral configurado.

La salida generada coincidirá con la documentada en [`docs/EJECUCION_OUTPUT_ASIGNACION_7.txt`](file:///c:/ProyectosBIU/eCommerceCSE6041/docs/EJECUCION_OUTPUT_ASIGNACION_7.txt).

---

## 📝 Documentación Completa

Para conocer en detalle el diseño de arquitectura y diagramas UML de la Asignación 7, consulte:
- [README_ASIGNACION_7.md](file:///c:/ProyectosBIU/eCommerceCSE6041/README_ASIGNACION_7.md)
- [readme_asignacion_7.md](file:///c:/ProyectosBIU/eCommerceCSE6041/readme_asignacion_7.md)
- [DOCS_ARQUITECTURA_OOP_ASIGNACION_7.md](file:///c:/ProyectosBIU/eCommerceCSE6041/docs/DOCS_ARQUITECTURA_OOP_ASIGNACION_7.md)
