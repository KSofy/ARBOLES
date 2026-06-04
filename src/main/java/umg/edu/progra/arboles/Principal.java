package umg.edu.progra.arboles;

/**
 * Clase principal que demuestra el uso del Arbol Binario de Busqueda (BST)
 * implementado manualmente, sin usar librerias como java.util.
 *
 * Ejecucion sugerida:
 *   1. mvn compile
 *   2. mvn exec:java -Dexec.mainClass="umg.edu.progra.arboles.Principal"
 *
 * @author Walter Cordova
 */
public class Principal {

	public static void main(String[] args) {

        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        // ============================================================
        // EJERCICIO EXTRA 4: Construcción dinámica desde consola (args)
        // ============================================================
        if (args.length > 0) {
            System.out.println("!!! Argumentos detectados en consola. Construyendo árbol personalizado...");
            for (String arg : args) {
                try {
                    int valorConsola = Integer.parseInt(arg);
                    arbol.insertar(valorConsola);
                } catch (NumberFormatException e) {
                    System.out.println("Ignorando argumento no válido (no es entero): " + arg);
                }
            }
        } else {
            /*
             * Si no hay argumentos en la terminal, usamos el arreglo por defecto del profesor
             * para formar el siguiente BST:
             *
             * 50
             * /  \
             * 30    70
             * /  \  /  \
             * 20  40 60  80
             * /
             * 10
             */
            int[] valores = { 50, 30, 70, 20, 40, 60, 80, 10 };
            for (int v : valores) {
                arbol.insertar(v);
            }
        }

        System.out.println("===== Arbol Binario de Busqueda =====");
        System.out.println("Tamanio: " + arbol.tamanio());
        System.out.println("Altura:  " + arbol.altura());
        System.out.println("Minimo:  " + arbol.minimo());
        System.out.println("Maximo:  " + arbol.maximo());
        System.out.println("Hojas:   " + arbol.contarHojas());

        System.out.println("\n--- Representacion visual (rotada 90 grados) ---");
        arbol.imprimirArbol();

        System.out.println("\n--- Recorridos ---");
        System.out.print("InOrden    (ascendente): ");
        arbol.inOrden();

        System.out.print("PreOrden   (raiz primero): ");
        arbol.preOrden();

        System.out.print("PostOrden  (raiz al final): ");
        arbol.postOrden();

        System.out.print("Por niveles (BFS):         ");
        arbol.recorridoPorNiveles();

        System.out.println("\n--- Busquedas ---");
        System.out.println("Contiene 40? " + arbol.contiene(40));
        System.out.println("Contiene 99? " + arbol.contiene(99));

        System.out.println("\n--- Eliminacion ---");
        System.out.println("Eliminando 20 (nodo con 1 hijo)...");
        arbol.eliminar(20);
        System.out.print("InOrden tras eliminar 20: ");
        arbol.inOrden();

        System.out.println("Eliminando 30 (nodo con 2 hijos)...");
        arbol.eliminar(30);
        System.out.print("InOrden tras eliminar 30: ");
        arbol.inOrden();

        System.out.println("Eliminando 50 (raiz)...");
        arbol.eliminar(50);
        System.out.print("InOrden tras eliminar la raiz: ");
        arbol.inOrden();

        System.out.println("\n--- Estado final ---");
        arbol.imprimirArbol();
        System.out.println("Tamanio final: " + arbol.tamanio());
        System.out.println("Altura final:  " + arbol.altura());

        /*
         * Ejercicios
         *
         *  1. Implementar un metodo que devuelva la cantidad TOTAL de nodos
         *     usando recursividad (sin usar el campo 'tamanio').
         *  2. Implementar un metodo 'esBalanceado()' que indique si el arbol
         *     esta balanceado (diferencia de alturas <= 1 en cada nodo).
         *  3. Implementar 'esBSTValido()' que verifique que el arbol cumple
         *     la propiedad de BST recorriendo los nodos.
         *  4. Implementar un metodo para encontrar el ancestro comun mas
         *     bajo (LCA) entre dos valores.
         *  5. Implementar la inversion del arbol (espejo).
         */
        System.out.println("\n=========================================");
        System.out.println("PRUEBA PROBLEMA 1: Contar Nodos");
        System.out.println("=========================================");
        System.out.println("Tamanio por variable: " + arbol.tamanio());
        System.out.println("Tamanio por recursión: " + arbol.contarNodos());
        
        System.out.println("-> Insertando valor de prueba: 90");
        arbol.insertar(90);
        System.out.println("Nuevo tamanio variable: " + arbol.tamanio());
        System.out.println("Nuevo tamanio recursión: " + arbol.contarNodos());
        
        System.out.println("\n=========================================");
        System.out.println("PRUEBA PROBLEMA 2: ¿Está Balanceado?");
        System.out.println("=========================================");
        System.out.println("¿El árbol actual está balanceado? " + arbol.esBalanceado());

        
        ArbolBinarioBusqueda arbolSecuencial = new ArbolBinarioBusqueda();
        for (int i = 1; i <= 5; i++) {
            arbolSecuencial.insertar(i);
        }
        
        System.out.println("¿Árbol secuencial (1 al 5) está balanceado? " + arbolSecuencial.esBalanceado());
    
        System.out.println("\n=========================================");
        System.out.println("PRUEBA PROBLEMA 3: Validar BST");
        System.out.println("=========================================");
        System.out.println("¿El árbol de trabajo es un BST válido? " + arbol.esBSTValido());

        
        ArbolBinarioBusqueda arbolRoto = new ArbolBinarioBusqueda();
        arbolRoto.insertar(100);
        arbolRoto.insertar(50);
        arbolRoto.insertar(150);
        
       
        arbolRoto.getRaiz().izquierdo.derecho = new Nodo(120); 
        
        System.out.println("¿El árbol alterado manualmente es un BST válido? " + arbolRoto.esBSTValido());
    
        System.out.println("\n=========================================");
        System.out.println("PRUEBA PROBLEMA 4: LCA (Ancestro Común Más Bajo)");
        System.out.println("=========================================");
        
        arbol.insertar(10);
        arbol.insertar(40);
        arbol.insertar(80);
        arbol.insertar(60);

        System.out.println("LCA(10, 40) -> Esperado: 30 | Resultado: " + arbol.ancestroComunMasBajo(10, 40));
        System.out.println("LCA(10, 80) -> Esperado: 50 | Resultado: " + arbol.ancestroComunMasBajo(10, 80));
        System.out.println("LCA(60, 80) -> Esperado: 70 | Resultado: " + arbol.ancestroComunMasBajo(60, 80));
        
        try {
            System.out.print("Probando caso de error LCA(99, 10): ");
            arbol.ancestroComunMasBajo(99, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Excepción capturada con éxito -> " + e.getMessage());
        }
    
        System.out.println("\n=========================================");
        System.out.println("PRUEBA PROBLEMA 5: Inversión Espejo");
        System.out.println("=========================================");
        System.out.println("--- Árbol ANTES de invertir ---");
        System.out.print("Recorrido InOrden: ");
        arbol.inOrden();
        arbol.imprimirArbol();

        System.out.println("\n-> Invirtiendo el árbol estructuralmente...");
        arbol.invertir();

        System.out.println("--- Árbol DESPUÉS de invertir ---");
        System.out.print("Recorrido InOrden (debe salir invertido): ");
        arbol.inOrden();
        arbol.imprimirArbol();
        System.out.println("=========================================");
    
        System.out.println("\n=========================================");
        System.out.println("PRUEBA EXTRA 1: k-ésimo Menor");
        System.out.println("=========================================");
        // Nota: Como el árbol está invertido por el problema 5, vamos a reinvertirlo para que las búsquedas tengan sentido biológico
        arbol.invertir(); 
        System.out.print("Árbol actual (InOrden): ");
        arbol.inOrden();
        System.out.println("El 1er menor es: " + arbol.kEsimoMenor(1));
        System.out.println("El 3er menor es: " + arbol.kEsimoMenor(3));
    
        System.out.println("\n=========================================");
        System.out.println("PRUEBA EXTRA 2: Imprimir Rango Ordenado");
        System.out.println("=========================================");
        System.out.print("Valores en rango [15, 75]: ");
        arbol.imprimirRangoOrdenado(15, 75);
        
        System.out.println("\n=========================================");
        System.out.println("PRUEBA EXTRA 3: Diámetro del Árbol");
        System.out.println("=========================================");
        System.out.println("El diámetro actual del árbol es: " + arbol.diametro() + " aristas.");
    }
}
