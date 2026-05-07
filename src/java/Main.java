// ============================================================
//  Universidad Técnica de Ambato
//  Carrera   : Ingeniería de Software
//  Asignatura: Estructura de Datos  –  Tercero B
//  Tema      : Recorridos de Árboles Binarios
//  Lenguaje  : Java
//  Autores   : [Nombre del grupo]
//  Fecha     : 2025
// ============================================================

import java.util.LinkedList;
import java.util.Queue;

// ============================================================
//  Nodo para árbol de enteros
// ============================================================
class Nodo {
    int dato;
    Nodo izquierda;
    Nodo derecha;

    public Nodo(int dato) {
        this.dato      = dato;
        this.izquierda = null;
        this.derecha   = null;
    }
}

// ============================================================
//  Nodo para árbol de Strings (caso real: Biblioteca)
// ============================================================
class NodoStr {
    String dato;
    NodoStr izquierda;
    NodoStr derecha;

    public NodoStr(String dato) {
        this.dato      = dato;
        this.izquierda = null;
        this.derecha   = null;
    }
}

// ============================================================
//  Clase principal
// ============================================================
public class Main {

    // --------------------------------------------------------
    //  Recorridos DFS — árbol de enteros
    // --------------------------------------------------------

    // Preorden: Raíz → Izquierda → Derecha
    public static void preorden(Nodo raiz) {
        if (raiz == null) return;             // caso base
        System.out.print(raiz.dato + " ");    // visitar raíz
        preorden(raiz.izquierda);             // subárbol izquierdo
        preorden(raiz.derecha);               // subárbol derecho
    }

    // Inorden: Izquierda → Raíz → Derecha
    // En un BST produce los elementos en orden ascendente
    public static void inorden(Nodo raiz) {
        if (raiz == null) return;
        inorden(raiz.izquierda);
        System.out.print(raiz.dato + " ");
        inorden(raiz.derecha);
    }

    // Postorden: Izquierda → Derecha → Raíz
    // Útil para liberar recursos o calcular tamaños
    public static void postorden(Nodo raiz) {
        if (raiz == null) return;
        postorden(raiz.izquierda);
        postorden(raiz.derecha);
        System.out.print(raiz.dato + " ");
    }

    // --------------------------------------------------------
    //  Recorrido BFS — usa Queue (cola explícita)
    // --------------------------------------------------------
    public static void bfs(Nodo raiz) {
        if (raiz == null) return;

        Queue<Nodo> cola = new LinkedList<>();
        cola.add(raiz);                       // encolar la raíz

        while (!cola.isEmpty()) {
            Nodo actual = cola.poll();         // sacar el primero (FIFO)
            System.out.print(actual.dato + " ");

            // encolar hijos para el siguiente nivel
            if (actual.izquierda != null) cola.add(actual.izquierda);
            if (actual.derecha   != null) cola.add(actual.derecha);
        }
    }

    // --------------------------------------------------------
    //  Funciones extra — Ejercicios 3 y 4
    // --------------------------------------------------------

    // Cuenta todos los nodos del árbol
    public static int contarNodos(Nodo raiz) {
        if (raiz == null) return 0;
        return 1 + contarNodos(raiz.izquierda) + contarNodos(raiz.derecha);
    }

    // Cuenta solo las hojas (nodos sin hijos)
    public static int contarHojas(Nodo raiz) {
        if (raiz == null) return 0;
        if (raiz.izquierda == null && raiz.derecha == null) return 1;
        return contarHojas(raiz.izquierda) + contarHojas(raiz.derecha);
    }

    // Calcula la altura del árbol
    public static int altura(Nodo raiz) {
        if (raiz == null) return 0;
        int altIzq = altura(raiz.izquierda);
        int altDer = altura(raiz.derecha);
        return 1 + Math.max(altIzq, altDer);
    }

    // --------------------------------------------------------
    //  Recorridos para árbol de Strings (caso real)
    // --------------------------------------------------------

    // Preorden → muestra menú jerárquico (padre antes que hijos)
    public static void preordenStr(NodoStr raiz) {
        if (raiz == null) return;
        System.out.println(raiz.dato);
        preordenStr(raiz.izquierda);
        preordenStr(raiz.derecha);
    }

    // BFS con nivel → muestra módulos nivel por nivel
    public static void bfsStr(NodoStr raiz) {
        if (raiz == null) return;

        Queue<NodoStr> cola = new LinkedList<>();
        cola.add(raiz);
        int nivel = 0;

        while (!cola.isEmpty()) {
            int tamano = cola.size();          // nodos en el nivel actual
            System.out.print("  Nivel " + nivel + ": ");

            for (int i = 0; i < tamano; i++) {
                NodoStr actual = cola.poll();
                System.out.print(actual.dato);
                if (i < tamano - 1) System.out.print(", ");
                if (actual.izquierda != null) cola.add(actual.izquierda);
                if (actual.derecha   != null) cola.add(actual.derecha);
            }
            System.out.println();
            nivel++;
        }
    }

    // Postorden → procesa módulos internos antes que el padre
    public static void postordenStr(NodoStr raiz) {
        if (raiz == null) return;
        postordenStr(raiz.izquierda);
        postordenStr(raiz.derecha);
        System.out.println(raiz.dato);
    }

    // --------------------------------------------------------
    //  MAIN
    // --------------------------------------------------------
    public static void main(String[] args) {

        // ----------------------------------------------------
        //  ÁRBOL BASE — Ejercicio 1
        //
        //          10
        //         /  \
        //        5    15
        //       / \   / \
        //      2   7 12  20
        // ----------------------------------------------------
        Nodo raiz = new Nodo(10);
        raiz.izquierda            = new Nodo(5);
        raiz.derecha              = new Nodo(15);
        raiz.izquierda.izquierda  = new Nodo(2);
        raiz.izquierda.derecha    = new Nodo(7);
        raiz.derecha.izquierda    = new Nodo(12);
        raiz.derecha.derecha      = new Nodo(20);

        // ----------------------------------------------------
        //  NODOS NUEVOS — Ejercicio 2 (nodos: 1, 3, 18, 25)
        //
        //           10
        //          /  \
        //         5    15
        //        / \   / \
        //       2   7 12  20
        //      / \       /  \
        //     1   3     18   25
        // ----------------------------------------------------
        raiz.izquierda.izquierda.izquierda = new Nodo(1);   // 1 < 2 → izquierda de 2
        raiz.izquierda.izquierda.derecha   = new Nodo(3);   // 3 > 2 → derecha de 2
        raiz.derecha.derecha.izquierda     = new Nodo(18);  // 18 < 20 → izquierda de 20
        raiz.derecha.derecha.derecha       = new Nodo(25);  // 25 > 20 → derecha de 20

        // ----------------------------------------------------
        //  Mostrar recorridos del árbol entero
        // ----------------------------------------------------
        System.out.println("=============================================");
        System.out.println("  RECORRIDOS DE ARBOLES BINARIOS - UTA      ");
        System.out.println("=============================================");

        System.out.print("\n[DFS] Preorden  (Raiz -> Izq -> Der): ");
        preorden(raiz);

        System.out.print("\n[DFS] Inorden   (Izq -> Raiz -> Der): ");
        inorden(raiz);

        System.out.print("\n[DFS] Postorden (Izq -> Der -> Raiz): ");
        postorden(raiz);

        System.out.print("\n[BFS] Nivel por nivel               : ");
        bfs(raiz);

        // ----------------------------------------------------
        //  Estadísticas — Ejercicios 3 y 4
        // ----------------------------------------------------
        System.out.println("\n\n--- Estadisticas del arbol ---");
        System.out.println("Total de nodos  : " + contarNodos(raiz));
        System.out.println("Total de hojas  : " + contarHojas(raiz));
        System.out.println("Altura del arbol: " + altura(raiz));

        // ----------------------------------------------------
        //  CASO REAL — Sistema de Biblioteca (Ejercicio 5)
        //
        //              Sistema Biblioteca
        //             /                  \
        //    Gestion Usuarios       Gestion Catalogo
        //     /          \              /           \
        // Registrar    Buscar        Libros       Reportes
        //   /   \                   /    \
        // Login Perfil          Agregar Eliminar
        // ----------------------------------------------------
        System.out.println("\n=============================================");
        System.out.println("  CASO REAL: Sistema de Biblioteca          ");
        System.out.println("=============================================");

        NodoStr biblioteca = new NodoStr("Sistema Biblioteca");

        // Nivel 1 — módulos principales
        biblioteca.izquierda = new NodoStr("Gestion Usuarios");
        biblioteca.derecha   = new NodoStr("Gestion Catalogo");

        // Nivel 2 — submódulos
        biblioteca.izquierda.izquierda = new NodoStr("Registrar");
        biblioteca.izquierda.derecha   = new NodoStr("Buscar");
        biblioteca.derecha.izquierda   = new NodoStr("Libros");
        biblioteca.derecha.derecha     = new NodoStr("Reportes");

        // Nivel 3 — operaciones concretas (hojas)
        biblioteca.izquierda.izquierda.izquierda = new NodoStr("Login");
        biblioteca.izquierda.izquierda.derecha   = new NodoStr("Perfil");
        biblioteca.derecha.izquierda.izquierda   = new NodoStr("Agregar");
        biblioteca.derecha.izquierda.derecha     = new NodoStr("Eliminar");

        // Preorden → mostrar menú principal
        System.out.println("\n[PREORDEN] Menu principal del sistema:");
        preordenStr(biblioteca);

        // BFS → módulos nivel por nivel
        System.out.println("\n[BFS] Modulos por nivel:");
        bfsStr(biblioteca);

        // Postorden → procesar módulos internos primero
        System.out.println("\n[POSTORDEN] Procesando modulos internos primero:");
        postordenStr(biblioteca);

        System.out.println("\n=============================================");
        System.out.println("  Ejecucion completada — UTA 2025           ");
        System.out.println("=============================================");
    }
}