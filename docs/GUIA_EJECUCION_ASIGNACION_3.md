# Guía de Compilación, Ejecución y Publicación - Asignación 3

**Plataforma e-Commerce CompraYa (Java OOP)**  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 💻 1. Compilación y Ejecución

### Compilación (PowerShell)
```powershell
cd eCommerceCSE6041
if (!(Test-Path bin)) { New-Item -ItemType Directory -Path bin }
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
```

### Ejecución de las Pruebas

#### Prueba de la Asignación 3 (Herencia y Especialización)
```powershell
java -cp bin com.compraya.MainAsignacion3
```

#### Prueba de la Asignación 1 (Modelo Base)
```powershell
java -cp bin com.compraya.Main
```

---

## 🌐 2. Publicación en GitHub

Para actualizar y publicar la Asignación 3 en el repositorio de GitHub:

```bash
# 1. Agregar todos los nuevos archivos de la Asignación 3
git add .

# 2. Hacer commit especificando los requerimientos de la Asignación 3
git commit -m "feat(asig-3): Aplicacion de herencia y especializacion en Producto (Fisico/Digital) y Usuario (Cliente/Admin)"

# 3. Subir al repositorio público de GitHub
git push origin main
```

---

© 2026 **Alberto Alfonso López Pereira**
