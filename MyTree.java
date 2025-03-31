import java.util.List;

/**
 * Árbol binario de búsqueda genérico para almacenar y buscar elementos.
 *
 * @param <T> Tipo de elementos que extienden Comparable.
 */
public class MyTree<T extends Comparable<T>> {
    private Nodo<T> raiz;

    private static class Nodo<T> {
        T dato;
        Nodo<T> izquierda;
        Nodo<T> derecha;

        Nodo(T dato) {
            this.dato = dato;
            this.izquierda = null;
            this.derecha = null;
        }
    }

    public MyTree() {
        this.raiz = null;
    }

    /**
     * Inserta un nuevo elemento en el árbol.
     *
     * @param dato Elemento a insertar.
     */
    public void insertar(T dato) {
        raiz = insertarRec(raiz, dato);
    }

    private Nodo<T> insertarRec(Nodo<T> actual, T dato) {
        if (actual == null) {
            return new Nodo<>(dato);
        }

        int comp = dato.compareTo(actual.dato);

        if (comp < 0) {
            actual.izquierda = insertarRec(actual.izquierda, dato);
        } else if (comp > 0) {
            actual.derecha = insertarRec(actual.derecha, dato);
        }

        return actual;
    }

    /**
     * Busca un elemento en el árbol.
     *
     * @param dato Elemento a buscar.
     * @return El elemento si se encuentra, o null si no.
     */
    public T buscar(T dato) {
        Nodo<T> resultado = buscarRec(raiz, dato);
        return resultado != null ? resultado.dato : null;
    }

    private Nodo<T> buscarRec(Nodo<T> actual, T dato) {
        if (actual == null || dato.compareTo(actual.dato) == 0) {
            return actual;
        }

        if (dato.compareTo(actual.dato) < 0) {
            return buscarRec(actual.izquierda, dato);
        }

        return buscarRec(actual.derecha, dato);
    }

    /**
     * Realiza un recorrido in-order y llena una lista con los elementos ordenados.
     *
     * @param lista Lista donde se agregarán los elementos.
     */
    public void recorridoInOrder(List<T> lista) {
        inOrderRec(raiz, lista);
    }

    private void inOrderRec(Nodo<T> actual, List<T> lista) {
        if (actual != null) {
            inOrderRec(actual.izquierda, lista);
            lista.add(actual.dato);
            inOrderRec(actual.derecha, lista);
        }
    }
}
