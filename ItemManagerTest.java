
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Pruebas unitarias para la clase ItemManager.
 */
public class ItemManagerTest {
    private ItemManager manager;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() {
        manager = new ItemManager();
    }

    @Test
    public void testBuscarPorCodigo() throws IOException {
        File csvFile = crearArchivoCSV();

        manager.cargarDesdeCSV(csvFile.getAbsolutePath());

        Item item = manager.buscarPorCodigo("ABC123");
        assertNotNull(item);
        assertEquals("ABC123", item.getCode());
        assertEquals("Test Product 1", item.getName());
        assertEquals(79.99, item.getCurrentPrice(), 0.001);

        Item noExiste = manager.buscarPorCodigo("XXXXXX");
        assertNull(noExiste);
    }

    @Test
    public void testOrdenarPorPrecio() throws IOException {
        File csvFile = crearArchivoCSV();

        manager.cargarDesdeCSV(csvFile.getAbsolutePath());

        List<Item> ascendente = manager.obtenerOrdenadosPorPrecio(true);
        assertEquals(3, ascendente.size());
        assertEquals(59.99, ascendente.get(0).getCurrentPrice(), 0.001);
        assertEquals(79.99, ascendente.get(1).getCurrentPrice(), 0.001);
        assertEquals(129.99, ascendente.get(2).getCurrentPrice(), 0.001);

        List<Item> descendente = manager.obtenerOrdenadosPorPrecio(false);
        assertEquals(129.99, descendente.get(0).getCurrentPrice(), 0.001);
        assertEquals(79.99, descendente.get(1).getCurrentPrice(), 0.001);
        assertEquals(59.99, descendente.get(2).getCurrentPrice(), 0.001);
    }

    private File crearArchivoCSV() throws IOException {
        File archivo = tempFolder.newFile("test_items.csv");
        try (FileWriter writer = new FileWriter(archivo)) {
            writer.write("Category,Field1,Field2,Field3,Field4,Field5,Code,Field7,Field8,RetailPrice,CurrentPrice,F11,F12,F13,F14,F15,F16,F17,Name\n");
            writer.write("Electronics,F1,F2,F3,F4,F5,ABC123,F7,F8,99.99,79.99,F11,F12,F13,F14,F15,F16,F17,Test Product 1\n");
            writer.write("Appliances,F1,F2,F3,F4,F5,XYZ789,F7,F8,149.99,129.99,F11,F12,F13,F14,F15,F16,F17,Test Product 2\n");
            writer.write("Electronics,F1,F2,F3,F4,F5,MNO456,F7,F8,69.99,59.99,F11,F12,F13,F14,F15,F16,F17,Test Product 3\n");
        }
        return archivo;
    }
}
