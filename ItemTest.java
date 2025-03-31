

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase Item.
 */
public class ItemTest {
    private Item item1;
    private Item item2;
    private Item item3;

    @Before
    public void setUp() {
        item1 = new Item("ABC123", 99.99, 79.99, "Test Product 1", "Electronics");
        item2 = new Item("XYZ789", 149.99, 129.99, "Test Product 2", "Appliances");
        item3 = new Item("ABC123", 89.99, 69.99, "Test Product 3", "Electronics");
    }

    @Test
    public void testGetters() {
        assertEquals("ABC123", item1.getCode());
        assertEquals(99.99, item1.getRetailPrice(), 0.001);
        assertEquals(79.99, item1.getCurrentPrice(), 0.001);
        assertEquals("Test Product 1", item1.getName());
        assertEquals("Electronics", item1.getType());
    }

    @Test
    public void testCompareToEquals() {
        assertEquals(0, item1.compareTo(item3));
    }

    @Test
    public void testCompareToDifferent() {
        assertTrue(item1.compareTo(item2) < 0);
        assertTrue(item2.compareTo(item1) > 0);
    }

    @Test
    public void testToString() {
        String expected = "Item{code='ABC123', name='Test Product 1', price=79.99}";
        assertEquals(expected, item1.toString());
    }
}
