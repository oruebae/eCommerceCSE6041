# Guía de Compilación, Ejecución y Publicación - Asignación 5

**Plataforma e-Commerce CompraYa (Java OOP)**  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 💻 1. Compilación y Ejecución (Asignación 5)

### Compilación (PowerShell)
```powershell
cd eCommerceCSE6041
if (!(Test-Path bin)) { New-Item -ItemType Directory -Path bin }
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
```

### Ejecución de Pruebas

#### Prueba de la Asignación 5 (Encapsulamiento y Abstracción)
```powershell
java -cp bin com.compraya.asignacion5.MainAsignacion5
```

#### Pruebas de Asignaciones Anteriores
```powershell
java -cp bin com.compraya.MainAsignacion4  # Asignación 4
java -cp bin com.compraya.MainAsignacion3  # Asignación 3
java -cp bin com.compraya.Main             # Asignación 1
```

---

## 🌐 2. Publicación en GitHub

```bash
git add .
git commit -m "feat(asig-5): Clase base abstracta Item, interfaces Despachable/Licenciable y encapsulamiento con validaciones en setters"
git push origin main
```

---

© 2026 **Alberto Alfonso López Pereira**
