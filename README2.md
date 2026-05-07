# Recorridos de Árboles Binarios

> **Universidad Técnica de Ambato** · Ingeniería de Software · Estructura de Datos · Tercero B

##  Descripción

Implementación completa de los **cuatro recorridos principales de árboles binarios** en C++ y Java, con aplicación a un caso real de Sistema de Biblioteca. El proyecto incluye funciones auxiliares de análisis, ejercicios resueltos y documentación detallada.

| Recorrido | Tipo | Orden | Estructura |
|-----------|------|-------|------------|
| **Preorden** | DFS | Raíz → Izq → Der | Recursividad |
| **Inorden** | DFS | Izq → Raíz → Der | Recursividad |
| **Postorden** | DFS | Izq → Der → Raíz | Recursividad |
| **BFS** | BFS | Nivel por nivel | Cola (Queue) |

---

##  Estructura del proyecto

```
recorridos-arboles/
├── src/
│   ├── cpp/
│   │   └── main.cpp          ← Implementación completa en C++
│   └── java/
│       └── Main.java         ← Implementación completa en Java
├── docs/
│   └── guia_practica.md      ← Guía de la práctica
├── exercises/
│   └── ejercicios.md         ← Ejercicios resueltos
├── moodle/
│   └── preguntas_moodle.md   ← Banco de preguntas
├── assets/
│   └── capturas/             ← Capturas de ejecución
└── README.md
```

---

##  Árbol utilizado

```
           10
          /  \
         5    15
        / \   / \
       2   7 12  20
      / \       /  \
     1   3     18   25
```

**11 nodos** · **6 hojas** · **Altura: 4**

---


### Salida esperada

```
=============================================
  RECORRIDOS DE ARBOLES BINARIOS - UTA
=============================================

[DFS] Preorden  (Raiz -> Izq -> Der): 10 5 2 1 3 7 15 12 20 18 25
[DFS] Inorden   (Izq -> Raiz -> Der): 1 2 3 5 7 10 12 15 18 20 25
[DFS] Postorden (Izq -> Der -> Raiz): 1 3 2 7 5 12 18 25 20 15 10
[BFS] Nivel por nivel               : 10 5 15 2 7 12 20 1 3 18 25

--- Estadisticas del arbol ---
Total de nodos  : 11
Total de hojas  : 6
Altura del arbol: 4
```

> ✅ La salida del **Inorden** produce los elementos en orden ascendente, confirmando que el árbol está correctamente estructurado como BST.

---

##  Funciones implementadas

| Función | Descripción | Resultado |
|---------|-------------|-----------|
| `preorden()` | Visita raíz antes que los hijos | `10 5 2 1 3 7 15 12 20 18 25` |
| `inorden()` | Visita raíz entre los hijos | `1 2 3 5 7 10 12 15 18 20 25` |
| `postorden()` | Visita raíz después de los hijos | `1 3 2 7 5 12 18 25 20 15 10` |
| `bfs()` | Recorre nivel por nivel con cola | `10 5 15 2 7 12 20 1 3 18 25` |
| `contarNodos()` | Cuenta todos los nodos | `11` |
| `contarHojas()` | Cuenta nodos sin hijos | `6` |
| `altura()` | Altura máxima del árbol | `4` |

---

##  Caso real — Sistema de Biblioteca

Se aplicó la estructura de árbol binario para modelar los módulos de un sistema web:

```
              Sistema Biblioteca
             /                  \
    Gestión Usuarios       Gestión Catálogo
     /          \              /           \
 Registrar    Buscar        Libros       Reportes
   /   \                   /    \
Login  Perfil          Agregar  Eliminar
```

| Necesidad | Recorrido | Razón |
|-----------|-----------|-------|
| Mostrar menú principal | **Preorden** | El padre aparece antes que sus opciones hijas |
| Procesar módulos internos | **Postorden** | Los hijos se procesan antes que el módulo padre |
| Ver módulos por nivel | **BFS** | Muestra todos los módulos del mismo nivel juntos |

---

##  Preguntas de reflexión

<details>
<summary><b>¿Qué recorrido sirve para ordenar valores en un BST?</b></summary>

**Inorden**, porque visita los nodos en el orden Izquierda → Raíz → Derecha, lo que en un BST siempre produce los valores de menor a mayor.

</details>

<details>
<summary><b>¿Por qué BFS requiere una cola?</b></summary>

Porque necesita procesar los nodos en el orden en que fueron descubiertos (FIFO). La cola guarda los nodos de cada nivel pendientes de visitar. Si usara una pila (LIFO), el comportamiento sería DFS, no BFS.

</details>

<details>
<summary><b>¿En qué caso real se usaría Postorden?</b></summary>

En la **liberación de memoria** de un árbol: hay que eliminar los hijos antes que el padre. También se usa para calcular el tamaño de carpetas en un sistema de archivos (primero sumar las subcarpetas, luego reportar el total al nivel superior).

</details>

---

## 📊 Complejidad

| Recorrido | Tiempo | Espacio | Nota |
|-----------|--------|---------|------|
| Preorden | O(n) | O(h) | h = altura del árbol |
| Inorden | O(n) | O(h) | Ordena BST |
| Postorden | O(n) | O(h) | Libera recursos |
| BFS | O(n) | O(w) | w = ancho máximo del árbol |

---


## Integrantes 

| Nombre | GitHub |
|--------|--------|
| [Matias Mena] | 
---

## 📎 Entregables

- [x] Código fuente comentado en C++ y Java
- [x] Cuatro recorridos implementados y verificados
- [x] Funciones auxiliares (contarNodos, contarHojas, altura)
- [x] Caso real aplicado al proyecto final
- [x] Ejercicios resueltos
- [x] Informe en formato Word
- [x] Banco de preguntas Moodle
- [ ] Capturas de ejecución en consola ← *agregar en `/assets/capturas/`*
- [ ] Link del repositorio GitHub ← *actualizar arriba*

---


<div align="center">
  <sub>Universidad Técnica de Ambato · Estructura de Datos · 2025</sub>
</div>