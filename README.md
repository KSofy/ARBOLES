# Tarea: Árbol Binario de Búsqueda (BST) en Java

**Curso:** Programación 3  
**Tema:** Estructuras de datos no lineales — Árboles  
**Modalidad:** Individual  
**Entrega:** Repositorio Git con el código modificado + capturas de ejecución

---

## 1. Objetivo

Comprender, completar y extender una implementación **manual** de un Árbol Binario de Búsqueda (BST) en Java, **sin usar `java.util` ni librerías externas**, siguiendo el mismo estilo con el que ya implementamos `queue` y `listas` enlazadas en clase.

Al finalizar la tarea el estudiante debe ser capaz de:

- Explicar la propiedad fundamental de un BST.
- Implementar inserción, búsqueda, eliminación y recorridos de forma recursiva.
- Reconocer los 3 casos de eliminación de un nodo en un BST.
- Implementar algoritmos clásicos sobre árboles (altura, balanceo, validación, LCA, espejo, etc.).

---

## 2. Proyecto base

El proyecto Maven `arboles/` ya contiene una base funcional:

```
arboles/
├── pom.xml
└── src/main/java/umg/edu/progra/arboles/
    ├── Nodo.java
    ├── ArbolBinarioBusqueda.java
    └── Principal.java
```

### Lo que YA está implementado

- `Nodo` con `dato`, `izquierdo` y `derecho`.
- `ArbolBinarioBusqueda` con:
  - `insertar(int)`
  - `buscar(int)` / `contiene(int)`
  - `eliminar(int)` cubriendo los 3 casos clásicos.
  - `minimo()`, `maximo()`, `altura()`, `tamanio()`, `contarHojas()`.
  - Recorridos: `inOrden()`, `preOrden()`, `postOrden()`, `recorridoPorNiveles()` (BFS con una cola casera, **sin `java.util`**).
  - `imprimirArbol()` (impresión visual rotada 90°).
- `Principal` con un ejemplo completo de uso.

---

## 3. Cómo ejecutar el proyecto

Desde la carpeta `arboles/`:

```bash
mvn compile
java -cp target/classes umg.edu.progra.arboles.Principal
```

O abriéndolo como proyecto Maven en Eclipse y ejecutando la clase `Principal`.

Salida esperada (resumen):

```
===== Arbol Binario de Busqueda =====
Tamanio: 8
Altura:  3
Minimo:  10
Maximo:  80
Hojas:   4
...
InOrden    (ascendente): 10 20 30 40 50 60 70 80
PreOrden   (raiz primero): 50 30 20 10 40 70 60 80
PostOrden  (raiz al final): 10 20 40 30 60 80 70 50
Por niveles (BFS):         50 30 70 20 40 60 80 10
```

---

## 4. Reglas obligatorias

> El incumplimiento de cualquiera de estas reglas invalida la tarea.

1. **Prohibido usar `java.util.*`** (ni `ArrayList`, ni `LinkedList`, ni `Queue`, ni `Stack`, ni `HashMap`, ni `Arrays`, etc.).
2. **Prohibido usar cualquier librería externa** para la estructura del árbol.
3. Si necesitan una estructura auxiliar (cola, pila, lista), deben implementarla manualmente como ya se hizo con `ColaNodos` dentro de `ArbolBinarioBusqueda`.
4. Toda la lógica nueva debe estar en la clase `ArbolBinarioBusqueda` (o en clases auxiliares dentro del mismo paquete `umg.edu.progra.arboles`).
5. Cada método nuevo debe probarse desde la clase `Principal`.
6. El código debe compilar con `mvn compile` sin errores ni warnings.

---

## 5. Problemas a resolver

Implementar los siguientes métodos en la clase `ArbolBinarioBusqueda` y demostrar su funcionamiento desde `Principal`.

### Problema 1 — Contar nodos recursivamente

Implementar:

```java
public int contarNodos();
```

- Debe devolver la cantidad total de nodos del árbol **usando recursividad**.
- **NO** puede usar el campo `tamanio` ya existente.
- Validar que su resultado coincida con `tamanio()` antes y después de insertar/eliminar.

### Problema 2 — ¿Está balanceado?

Implementar:

```java
public boolean esBalanceado();
```

- Un árbol está balanceado si, para **cada nodo**, la diferencia de altura entre su subárbol izquierdo y derecho es `<= 1`.
- Probarlo con un árbol balanceado y con uno claramente desbalanceado (por ejemplo, insertando 1, 2, 3, 4, 5 en ese orden).

### Problema 3 — Validar que sea un BST

Implementar:

```java
public boolean esBSTValido();
```

- Debe verificar que el árbol cumple la propiedad de BST (todo el subárbol izquierdo `<` raíz, todo el subárbol derecho `>` raíz).
- **Pista:** una solución limpia es pasar un rango `(min, max)` permitido en cada llamada recursiva.
- Probarlo en el árbol generado por `Principal`. Debe retornar `true`.
- Para demostrar el caso `false`, construir manualmente un árbol "roto" (modificando nodos directamente) y validar que devuelve `false`.

### Problema 4 — Ancestro común más bajo (LCA)

Implementar:

```java
public int ancestroComunMasBajo(int a, int b);
```

- Debe devolver el dato del nodo que es el **ancestro común más bajo** (Lowest Common Ancestor) de los valores `a` y `b`.
- Aprovechar la propiedad del BST: si ambos valores son menores que el actual → ir a la izquierda; si ambos son mayores → ir a la derecha; en caso contrario, el nodo actual es el LCA.
- Si `a` o `b` no existen en el árbol, lanzar `IllegalArgumentException`.
- Ejemplo con el árbol de `Principal`:
  - `lca(10, 40)` → `30`
  - `lca(10, 80)` → `50`
  - `lca(60, 80)` → `70`

### Problema 5 — Espejo del árbol (inversión)

Implementar:

```java
public void invertir();
```

- Debe intercambiar `izquierdo` y `derecho` en **todos** los nodos del árbol (reflejo / espejo).
- Antes de invertir, mostrar el árbol con `imprimirArbol()` e `inOrden()`.
- Después de invertir, volver a mostrarlos. El `inOrden` original ya **no** estará ordenado (se invierte).

---

## 6. Ejercicios extra (opcionales, suman puntos)

Solo cuentan si los 5 problemas anteriores están correctos.

- **E1.** `int kEsimoMenor(int k)` — devuelve el k-ésimo valor más pequeño usando inOrden.
- **E2.** `void imprimirRangoOrdenado(int min, int max)` — imprime en orden todos los valores en el rango `[min, max]` recorriendo lo menos posible el árbol.
- **E3.** `int diametro()` — el camino más largo (en aristas) entre dos nodos cualesquiera del árbol.
- **E4.** Construir un BST a partir de un arreglo `int[]` recibido por la consola (`args`).

---

## 7. Entregables

1. **Repositorio Git** (GitHub / GitLab) con el proyecto modificado.
2. **Capturas** de la salida en consola que demuestren cada problema resuelto.
3. **README.md propio** del estudiante (puede partir de este) explicando:
   - Cómo compilar y ejecutar.
   - Qué hace cada método nuevo.
   - Un ejemplo de entrada y salida por cada problema.
4. Commits descriptivos por cada problema (por ejemplo: `feat: problema 1 contarNodos recursivo`).

---

## 8. Rúbrica de evaluación (100 pts)

| Criterio                                            | Puntos |
| --------------------------------------------------- | -----: |
| Problema 1 — `contarNodos` recursivo                | 10     |
| Problema 2 — `esBalanceado`                         | 15     |
| Problema 3 — `esBSTValido`                          | 15     |
| Problema 4 — `ancestroComunMasBajo` (LCA)           | 20     |
| Problema 5 — `invertir` (espejo)                    | 15     |
| Pruebas claras en `Principal` (cada método se ve)   | 10     |
| Código limpio, recursivo cuando aplica, sin `util`  | 10     |
| Commits, README propio y capturas                   | 5      |
| **Ejercicios extra (E1–E4)**                        | +10    |

---

## 9. Recomendaciones

- **Piensen recursivamente.** Casi todo en árboles se resuelve definiendo el caso base (nodo `null`) y combinando el resultado de los subárboles izquierdo y derecho.
- **Dibujen el árbol antes de codificar.** Especialmente para `eliminar`, `LCA` e `invertir`.
- **Prueben con árboles vacíos** (`raiz == null`) y con árboles de un solo nodo. La mitad de los errores aparecen en esos bordes.
- **No copien código de internet sin entenderlo.** En la defensa oral se pregunta sobre cualquier línea.

---

> "Un BST bien implementado es más rápido que muchas estructuras prediseñadas… si entienden el porqué."


---
# 🎓 DOCUMENTACIÓN DEL ESTUDIANTE
# 🌳 Estructuras de Datos - Práctica Dirigida: Árboles Binarios

Este proyecto contiene la implementación manual de un **Árbol Binario de Búsqueda (BST)** en Java utilizando Maven. Se han desarrollado los 5 problemas obligatorios de la guía y 4 ejercicios extra avanzados, respetando la restricción de **no utilizar librerías de colecciones nativas (`java.util.*`)**.

**Estudiante:** [KATHYA SOFIA MELGAR MARROQUIN]  
**Curso:** Programación 3  

---

## 🚀 Cómo Compilar y Ejecutar

El proyecto utiliza **Apache Maven** para la gestión de dependencias y ciclo de vida del software.

### 1. Compilar el proyecto
Para limpiar el entorno y compilar el código fuente, ejecuta en tu terminal:
```bash
mvn clean compile

2. Ejecución Estándar (Árbol por Defecto)Para ejecutar el programa utilizando el árbol de prueba predeterminado por el docente ([50, 30, 70, 20, 40, 60, 80, 10]), utiliza:Bashmvn exec:java "-Dexec.mainClass=umg.edu.progra.arboles.Principal"
3. Ejecución Dinámica (Ejercicio Extra 4 - Argumentos de Consola)Para construir un árbol de forma completamente personalizada desde la terminal, introduce una lista de números enteros separados por espacios al final del comando:Bashmvn exec:java "-Dexec.mainClass=umg.edu.progra.arboles.Principal" "-Dexec.args=45 23 67 12 34 89"

---

## 🛠️ Descripción de Métodos Nuevos e Implementados

### Métodos Obligatorios

* **`public int contarNodos()`**: Llama a una función auxiliar recursiva que cuenta el nodo actual más la suma total de los nodos de sus subárboles izquierdo y derecho. No utiliza la variable interna `tamanio`.
* **`public boolean esBalanceado()`**: Evalúa recursivamente si la diferencia absoluta de altura entre el subárbol izquierdo y derecho de **cada nodo** en el árbol es menor o igual a 1.
* **`public boolean esBSTValido()`**: Verifica que las llaves respeten las restricciones de ordenamiento de un BST, arrastrando límites dinámicos (`min` y `max`) en pre-orden para evitar que un nodo fuera de posición burle las validaciones locales.
* **`public int ancestroComunMasBajo(int a, int b)`**: Encuentra el nodo más profundo que es ancestro de ambos valores simultáneamente. Aplica una validación inicial de existencia y aprovecha las propiedades del BST para podar caminos de búsqueda.
* **`public void invertir()`**: Transforma el árbol físico en su reflejo geométrico (espejo), intercambiando los punteros `izquierdo` y `derecho` de todos los nodos mediante un recorrido recursivo en post-orden.

### Ejercicios Extra (Opcionales)

* **`public int kEsimoMenor(int k)`**: Devuelve el $k$-ésimo valor más pequeño del árbol simulando un recorrido `InOrden` con un contador global simulado por referencia. Stop temprano al alcanzar $k$.
* **`public void imprimirRangoOrdenado(int min, int max)`**: Imprime de manera ordenada los valores comprendidos en el intervalo cerrado `[min, max]`. Aplica técnicas de **poda de árbol** ignorando ramas completas si el dato actual desborda las fronteras.
* **`public int diametro()`**: Calcula el camino más largo (medido en aristas) entre dos nodos hojas cualesquiera en el árbol utilizando relaciones de altura máximas.

---

📊 Ejemplos de Entrada y Salida por ProblemaA continuación se detallan los resultados obtenidos en la consola al procesar el flujo sobre el árbol vivo (que sufrió modificaciones de eliminación en etapas previas):

Problema 1: Contar NodosEntrada (Árbol vivo post-eliminaciones): Nodos activos [10, 40, 60, 70, 80]Salida:PlaintextTamanio por variable: 5
Tamanio por recursión: 5
-> Insertando valor de prueba: 90
Nuevo tamanio variable: 6
Nuevo tamanio recursión: 6

Problema 2: ¿Está Balanceado?Entrada 1: Árbol actual con desbalance provocado por la inserción del 90.Entrada 2: Árbol secuencial lineal cargado del 1 al 5.Salida:Plaintext¿El árbol actual está balanceado? false
¿Árbol secuencial (1 al 5) está balanceado? false

Problema 3: Validar BSTEntrada 1: Árbol de trabajo ordenado correctamente.Entrada 2: arbolRoto saboteado manualmente asignando un 120 a la derecha de un 50 (bajo la raíz 100).Salida:Plaintext¿El árbol de trabajo es un BST válido? true
¿El árbol alterado manualmente es un BST válido? false

Problema 4: Ancestro Común Más Bajo (LCA)Entrada: Búsquedas sobre la estructura viva del árbol (con raíz 60).Salida:PlaintextLCA(10, 40) -> Resultado: 40
LCA(10, 80) -> Resultado: 60
LCA(60, 80) -> Resultado: 60
Probando caso de error LCA(99, 10): Excepción capturada con éxito -> Uno o ambos valores no existen en el árbol.

Problema 5: Inversión EspejoEntrada: Árbol normal InOrden: 10 40 60 70 80 90Salida:Plaintext-> Invirtiendo el árbol estructuralmente...
Recorrido InOrden (debe salir invertido): 90 80 70 60 40 10 
          -> 10
     -> 40
-> 60
     -> 70
          -> 80
               -> 90

Ejercicio Extra 1: k-ésimo MenorEntrada: Árbol restaurado a su orientación biológica. Solicitud de posiciones $k=1$ y $k=3$.Salida:PlaintextÁrbol actual (InOrden): 10 40 60 70 80 90 
El 1er menor es: 10
El 3er menor es: 60

Ejercicio Extra 2: Imprimir Rango OrdenadoEntrada: Rangos de filtrado min = 15, max = 75Salida:PlaintextValores en rango [15, 75]: 40 60 70 

Ejercicio Extra 3: Diámetro del ÁrbolEntrada: Estructura geométrica total del árbol de trabajo.Salida:PlaintextEl diámetro actual del árbol es: 5 aristas.
