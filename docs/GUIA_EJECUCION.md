# Guía de Ejecución y Publicación en GitHub

**Plataforma e-Commerce CompraYa (Java OOP)**  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 💻 1. Compilación y Ejecución Local

### Paso 1: Verificar la instalación de Java
Abre una consola de comandos (PowerShell o CMD) y ejecuta:
```powershell
java -version
javac -version
```
*Se requiere JDK 17 o superior.*

### Paso 2: Compilar el código fuente
Desde la carpeta raíz del proyecto (`eCommerceCSE6041`), ejecuta:

**En Windows (PowerShell):**
```powershell
# Crear la carpeta donde se almacenarán las clases compiladas
if (!(Test-Path bin)) { New-Item -ItemType Directory -Path bin }

# Compilar todos los archivos Java
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
```

**En Linux / macOS:**
```bash
mkdir -p bin
find src -name "*.java" | xargs javac -d bin
```

### Paso 3: Ejecutar la Demostración
Una vez compilado, ejecuta la clase principal `Main`:
```bash
java -cp bin com.compraya.Main
```

---

## 🌐 2. Publicación en GitHub

Sigue estos comandos para subir este proyecto a un nuevo repositorio de GitHub:

```bash
# 1. Inicializar el repositorio Git local
git init

# 2. Agregar todos los archivos al seguimiento
git add .

# 3. Realizar el primer commit
git commit -m "feat: Implementación completa de clases POO CompraYa (Producto, Usuario, Carrito)"

# 4. Cambiar el nombre de la rama principal a main
git branch -M main

# 5. Vincular el repositorio remoto de GitHub (remplaza la URL con tu repo)
git remote add origin https://github.com/TU_USUARIO/eCommerceCSE6041.git

# 6. Subir el código a GitHub
git push -u origin main
```

---

© 2026 **Alberto Alfonso López Pereira**
