# Guía de Compilación, Ejecución y Publicación - Asignación 4

**Plataforma e-Commerce CompraYa (Java OOP)**  
**Estudiante:** Alberto Alfonso López Pereira  

---

## 💻 1. Compilación y Ejecución (Asignación 4)

### Compilación (PowerShell)
```powershell
cd eCommerceCSE6041
if (!(Test-Path bin)) { New-Item -ItemType Directory -Path bin }
javac -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
```

### Ejecución de Pruebas

#### Prueba de la Asignación 4 (Polimorfismo, Sobrecarga y Sobreescritura)
```powershell
java -cp bin com.compraya.MainAsignacion4
```

#### Pruebas de Asignaciones Anteriores
```powershell
java -cp bin com.compraya.MainAsignacion3  # Asignación 3
java -cp bin com.compraya.Main             # Asignación 1
```

---

## 🌐 2. Publicación en GitHub

```bash
git add .
git commit -m "feat(asig-4): Polimorfismo de invocacion, sobrecarga de agregarProducto en Carrito y sobreescritura de mostrarDetalle"
git push origin main
```

---

© 2026 **Alberto Alfonso López Pereira**
