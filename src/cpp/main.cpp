#include <iostream>
#include <queue>
#include <string>
using namespace std;

// ============================================================
//  PARTE 1 — ÁRBOL BINARIO DE ENTEROS
// ============================================================

// Nodo genérico para enteros
struct Nodo {
    int dato;
    Nodo* izquierda;
    Nodo* derecha;

    Nodo(int valor) {
        dato      = valor;
        izquierda = nullptr;
        derecha   = nullptr;
    }
};

// ------------------------------------------------------------
//  Recorridos DFS (usan recursividad / pila implícita)
// ------------------------------------------------------------

// Preorden: Raíz → Izquierda → Derecha
void preorden(Nodo* raiz) {
    if (raiz == nullptr) return;          // caso base
    cout << raiz->dato << " ";            // visitar raíz
    preorden(raiz->izquierda);            // recorrer subárbol izquierdo
    preorden(raiz->derecha);              // recorrer subárbol derecho
}

// Inorden: Izquierda → Raíz → Derecha
// En un BST produce los elementos en orden ascendente
void inorden(Nodo* raiz) {
    if (raiz == nullptr) return;
    inorden(raiz->izquierda);
    cout << raiz->dato << " ";
    inorden(raiz->derecha);
}

// Postorden: Izquierda → Derecha → Raíz
// Útil para liberar memoria o calcular tamaños
void postorden(Nodo* raiz) {
    if (raiz == nullptr) return;
    postorden(raiz->izquierda);
    postorden(raiz->derecha);
    cout << raiz->dato << " ";
}

// ------------------------------------------------------------
//  Recorrido BFS (usa cola explícita — nivel por nivel)
// ------------------------------------------------------------
void bfs(Nodo* raiz) {
    if (raiz == nullptr) return;

    queue<Nodo*> cola;
    cola.push(raiz);                      // encolar la raíz

    while (!cola.empty()) {
        Nodo* actual = cola.front();      // tomar el primero de la cola
        cola.pop();

        cout << actual->dato << " ";     // visitar nodo actual

        // encolar hijos para el siguiente nivel
        if (actual->izquierda != nullptr) cola.push(actual->izquierda);
        if (actual->derecha   != nullptr) cola.push(actual->derecha);
    }
}

// ------------------------------------------------------------
//  Funciones extra — Ejercicios 3 y 4
// ------------------------------------------------------------

// Cuenta todos los nodos del árbol
int contarNodos(Nodo* raiz) {
    if (raiz == nullptr) return 0;
    return 1 + contarNodos(raiz->izquierda) + contarNodos(raiz->derecha);
}

// Cuenta solo las hojas (nodos sin hijos)
int contarHojas(Nodo* raiz) {
    if (raiz == nullptr) return 0;
    if (raiz->izquierda == nullptr && raiz->derecha == nullptr) return 1;
    return contarHojas(raiz->izquierda) + contarHojas(raiz->derecha);
}

// Calcula la altura del árbol
int altura(Nodo* raiz) {
    if (raiz == nullptr) return 0;
    int altIzq = altura(raiz->izquierda);
    int altDer = altura(raiz->derecha);
    return 1 + max(altIzq, altDer);
}

// ============================================================
//  PARTE 2 — ÁRBOL BINARIO DE STRINGS (Caso real: Biblioteca)
// ============================================================

struct NodoStr {
    string dato;
    NodoStr* izquierda;
    NodoStr* derecha;

    NodoStr(string valor) {
        dato      = valor;
        izquierda = nullptr;
        derecha   = nullptr;
    }
};

// Preorden para strings — muestra el menú jerárquico
// (padre antes que hijos → ideal para menú principal)
void preordenStr(NodoStr* raiz) {
    if (raiz == nullptr) return;
    cout << raiz->dato << endl;
    preordenStr(raiz->izquierda);
    preordenStr(raiz->derecha);
}

// BFS para strings — muestra módulos nivel por nivel
void bfsStr(NodoStr* raiz) {
    if (raiz == nullptr) return;
    queue<NodoStr*> cola;
    cola.push(raiz);
    int nivel = 0;

    while (!cola.empty()) {
        int tamano = cola.size();         // nodos en el nivel actual
        cout << "  Nivel " << nivel << ": ";

        for (int i = 0; i < tamano; i++) {
            NodoStr* actual = cola.front();
            cola.pop();
            cout << actual->dato;
            if (i < tamano - 1) cout << ", ";
            if (actual->izquierda != nullptr) cola.push(actual->izquierda);
            if (actual->derecha   != nullptr) cola.push(actual->derecha);
        }
        cout << endl;
        nivel++;
    }
}

// Postorden para strings — procesa primero módulos internos
void postordenStr(NodoStr* raiz) {
    if (raiz == nullptr) return;
    postordenStr(raiz->izquierda);
    postordenStr(raiz->derecha);
    cout << raiz->dato << endl;
}

// ============================================================
//  MAIN
// ============================================================
int main() {

    // --------------------------------------------------------
    //  ÁRBOL BASE — Ejercicio 1
    //
    //          10
    //         /  \
    //        5    15
    //       / \   / \
    //      2   7 12  20
    // --------------------------------------------------------
    Nodo* raiz = new Nodo(10);
    raiz->izquierda            = new Nodo(5);
    raiz->derecha              = new Nodo(15);
    raiz->izquierda->izquierda = new Nodo(2);
    raiz->izquierda->derecha   = new Nodo(7);
    raiz->derecha->izquierda   = new Nodo(12);
    raiz->derecha->derecha     = new Nodo(20);

    // --------------------------------------------------------
    //  NODOS NUEVOS — Ejercicio 2 (nodos: 1, 3, 18, 25)
    //
    //           10
    //          /  \
    //         5    15
    //        / \   / \
    //       2   7 12  20
    //      / \       /  \
    //     1   3     18   25
    // --------------------------------------------------------
    raiz->izquierda->izquierda->izquierda = new Nodo(1);   // 1 < 2 → izquierda de 2
    raiz->izquierda->izquierda->derecha   = new Nodo(3);   // 3 > 2 → derecha de 2
    raiz->derecha->derecha->izquierda     = new Nodo(18);  // 18 < 20 → izquierda de 20
    raiz->derecha->derecha->derecha       = new Nodo(25);  // 25 > 20 → derecha de 20

    // --------------------------------------------------------
    //  Mostrar recorridos del árbol entero
    // --------------------------------------------------------
    cout << "=============================================" << endl;
    cout << "  RECORRIDOS DE ARBOLES BINARIOS - UTA      " << endl;
    cout << "=============================================" << endl;

    cout << "\n[DFS] Preorden  (Raiz -> Izq -> Der): ";
    preorden(raiz);

    cout << "\n[DFS] Inorden   (Izq -> Raiz -> Der): ";
    inorden(raiz);

    cout << "\n[DFS] Postorden (Izq -> Der -> Raiz): ";
    postorden(raiz);

    cout << "\n[BFS] Nivel por nivel               : ";
    bfs(raiz);

    // --------------------------------------------------------
    //  Estadísticas del árbol — Ejercicios 3 y 4
    // --------------------------------------------------------
    cout << "\n\n--- Estadisticas del arbol ---" << endl;
    cout << "Total de nodos : " << contarNodos(raiz)  << endl;
    cout << "Total de hojas : " << contarHojas(raiz)  << endl;
    cout << "Altura del arbol: " << altura(raiz)       << endl;

    // --------------------------------------------------------
    //  CASO REAL — Sistema de Biblioteca (Ejercicio 5)
    //
    //              Sistema Biblioteca
    //             /                  \
    //    Gestion Usuarios       Gestion Catalogo
    //     /          \              /           \
    // Registrar    Buscar        Libros       Reportes
    //   /   \                   /    \
    // Login Perfil          Agregar Eliminar
    // --------------------------------------------------------
    cout << "\n=============================================" << endl;
    cout << "  CASO REAL: Sistema de Biblioteca          " << endl;
    cout << "=============================================" << endl;

    NodoStr* biblioteca = new NodoStr("Sistema Biblioteca");

    // Nivel 1 — módulos principales
    biblioteca->izquierda = new NodoStr("Gestion Usuarios");
    biblioteca->derecha   = new NodoStr("Gestion Catalogo");

    // Nivel 2 — submódulos
    biblioteca->izquierda->izquierda = new NodoStr("Registrar");
    biblioteca->izquierda->derecha   = new NodoStr("Buscar");
    biblioteca->derecha->izquierda   = new NodoStr("Libros");
    biblioteca->derecha->derecha     = new NodoStr("Reportes");

    // Nivel 3 — operaciones concretas (hojas)
    biblioteca->izquierda->izquierda->izquierda = new NodoStr("Login");
    biblioteca->izquierda->izquierda->derecha   = new NodoStr("Perfil");
    biblioteca->derecha->izquierda->izquierda   = new NodoStr("Agregar");
    biblioteca->derecha->izquierda->derecha     = new NodoStr("Eliminar");

    // Preorden → mostrar menú principal (padre antes que hijos)
    cout << "\n[PREORDEN] Menu principal del sistema:" << endl;
    preordenStr(biblioteca);

    // BFS → mostrar módulos nivel por nivel
    cout << "\n[BFS] Modulos por nivel:" << endl;
    bfsStr(biblioteca);

    // Postorden → procesar módulos internos primero
    cout << "\n[POSTORDEN] Procesando modulos internos primero:" << endl;
    postordenStr(biblioteca);

    cout << "\n=============================================" << endl;
    cout << "  Ejecucion completada — UTA 2025           " << endl;
    cout << "=============================================" << endl;

    return 0;
}