

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas unitarias para el árbol binario de búsqueda MyTree.
 */
public class MyTreeTest {
    private MyTree<Integer> intTree;
    private MyTree<Item> itemTree;

    @Before
    public void setUp() {
        intTree = new MyTree<>();
        itemTree = new MyTree<>();
    }

    @Test
    public void testInsertarYBuscarEnteros() {
        intTree.insertar(10);
        intTree.insertar(5);
        intTree.insertar(15);

        assertEquals(Integer.valueOf(10), intTree.buscar(10));
        assertEquals(Integer.valueOf(5), intTree.buscar(5));
        assertEquals(Integer.valueOf(15), intTree.buscar(15));
        assertNull(intTree.buscar(99));
    }

    @Test
    public void testRecorridoInOrderEnteros() {
        intTree.insertar(10);
        intTree.insertar(5);
        intTree.insertar(15);

        List<Integer> result = new ArrayList<>();
        intTree.recorridoInOrder(result);

        List<Integer> expected = List.of(5, 10, 15);
        assertEquals(expected, result);
    }

    @Test
    public void testArbolDeItems() {
        Item item1 = new Item("AAA111", 99.99, 79.99, "Item Uno", "Cat1");
        Item item2 = new Item("CCC333", 150.00, 130.00, "Item Dos", "Cat2");
        Item item3 = new Item("BBB222", 89.99, 60.00, "Item Tres", "Cat1");

        itemTree.insertar(item1);
        itemTree.insertar(item2);
        itemTree.insertar(item3);

        assertEquals(item1, itemTree.buscar(new Item("AAA111", 0, 0, "", "")));
        assertEquals(item2, itemTree.buscar(new Item("CCC333", 0, 0, "", "")));

        List<Item> ordenados = new ArrayList<>();
        itemTree.recorridoInOrder(ordenados);

        assertEquals(3, ordenados.size());
        assertEquals("AAA111", ordenados.get(0).getCode());
        assertEquals("BBB222", ordenados.get(1).getCode());
        assertEquals("CCC333", ordenados.get(2).getCode());
    }
}
