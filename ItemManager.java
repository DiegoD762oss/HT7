

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Administra la carga, búsqueda y ordenamiento de artículos desde un archivo CSV.
 */
public class ItemManager {
    private MyTree<Item> itemTree;

    public ItemManager() {
        this.itemTree = new MyTree<>();
    }

    public void cargarDesdeCSV(String rutaArchivo) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            String encabezado = lector.readLine(); // Saltar encabezado
            System.out.println("Encabezado del CSV: " + encabezado);

            int totalLineas = 0;
            int totalAgregados = 0;
            int erroresPrecio = 0;

            while ((linea = lector.readLine()) != null) {
                totalLineas++;
                List<String> campos = dividirCSV(linea);

                if (campos.size() >= 19) {
                    try {
                        String code = campos.get(6).trim();      // SKU
                        String type = campos.get(0).trim();      // Categoría
                        String name = campos.get(18).trim();     // Nombre

                        double retailPrice = 0;
                        double currentPrice = 0;

                        try {
                            String pNormal = campos.get(9).trim();
                            String pActual = campos.get(10).trim();

                            if (!pNormal.isEmpty() && pNormal.matches("^\\d*\\.?\\d+$")) {
                                retailPrice = Double.parseDouble(pNormal);
                            }
                            if (!pActual.isEmpty() && pActual.matches("^\\d*\\.?\\d+$")) {
                                currentPrice = Double.parseDouble(pActual);
                            }
                        } catch (NumberFormatException e) {
                            erroresPrecio++;
                            if (erroresPrecio < 5) {
                                System.out.println("Error de precio en línea " + totalLineas + ": " + e.getMessage());
                            }
                            continue;
                        }

                        Item nuevo = new Item(code, retailPrice, currentPrice, name, type);
                        itemTree.insertar(nuevo);
                        totalAgregados++;

                    } catch (Exception e) {
                        System.out.println("Entrada inválida en línea " + totalLineas + ": " + e.getMessage());
                    }
                } else {
                    System.out.println("Línea con campos insuficientes: " + totalLineas);
                }
            }

            System.out.println("Líneas procesadas: " + totalLineas);
            System.out.println("Artículos agregados: " + totalAgregados);
            System.out.println("Errores de precio: " + erroresPrecio);
        }
    }

    private List<String> dividirCSV(String linea) {
        List<String> resultado = new ArrayList<>();
        boolean entreComillas = false;
        StringBuilder campo = new StringBuilder();

        for (int i = 0; i < linea.length(); i++) {
            char c = linea.charAt(i);

            if (c == '"') {
                entreComillas = !entreComillas;
            } else if (c == ',' && !entreComillas) {
                resultado.add(campo.toString());
                campo = new StringBuilder();
            } else {
                campo.append(c);
            }
        }
        resultado.add(campo.toString());
        return resultado;
    }

    public Item buscarPorCodigo(String codigo) {
        Item dummy = new Item(codigo, 0, 0, "", "");
        return itemTree.buscar(dummy);
    }

    public List<Item> obtenerOrdenadosPorPrecio(boolean ascendente) {
        List<Item> lista = new ArrayList<>();
        itemTree.recorridoInOrder(lista);

        Comparator<Item> comparador = Comparator.comparingDouble(item -> item.getCurrentPrice());
        if (!ascendente) {
            comparador = comparador.reversed();
        }

        lista.sort(comparador);
        return lista;
    }

    public void mostrarDetalles(Item item) {
        if (item != null) {
            System.out.println("Detalles del artículo:");
            System.out.println("Código: " + item.getCode());
            System.out.println("Nombre: " + item.getName());
            System.out.println("Categoría: " + item.getType());
            System.out.println("Precio Normal: $" + item.getRetailPrice());
            System.out.println("Precio Actual: $" + item.getCurrentPrice());
        } else {
            System.out.println("Artículo no encontrado.");
        }
    }
}
