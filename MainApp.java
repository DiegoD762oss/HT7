

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Aplicación principal para interactuar con el sistema de artículos.
 */
public class MainApp {
    public static void main(String[] args) {
        ItemManager manager = new ItemManager();
        Scanner scanner = new Scanner(System.in);

        try {
            // ✅ Usa aquí el nombre del archivo .csv real que tengas en la misma carpeta
            manager.cargarDesdeCSV("home appliance skus lowes.csv");

            boolean continuar = true;

            while (continuar) {
                System.out.println("\n=== Sistema de Artículos ===");
                System.out.println("1. Buscar por código (SKU)");
                System.out.println("2. Listar artículos (precio ascendente)");
                System.out.println("3. Listar artículos (precio descendente)");
                System.out.println("4. Salir");
                System.out.print("Elige una opción: ");

                String opcion = scanner.nextLine().trim();

                switch (opcion) {
                    case "1":
                        System.out.print("Ingresa el código a buscar: ");
                        String codigo = scanner.nextLine().trim();
                        Item encontrado = manager.buscarPorCodigo(codigo);
                        manager.mostrarDetalles(encontrado);
                        break;

                    case "2":
                        System.out.println("\nArtículos ordenados por precio (ascendente):");
                        mostrarArticulos(manager.obtenerOrdenadosPorPrecio(true));
                        break;

                    case "3":
                        System.out.println("\nArtículos ordenados por precio (descendente):");
                        mostrarArticulos(manager.obtenerOrdenadosPorPrecio(false));
                        break;

                    case "4":
                        continuar = false;
                        System.out.println("Programa finalizado.");
                        break;

                    default:
                        System.out.println("Opción inválida.");
                }
            }

        } catch (IOException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    /**
     * Muestra una lista de artículos con paginación cada 20 elementos.
     */
    private static void mostrarArticulos(List<Item> lista) {
        if (lista.isEmpty()) {
            System.out.println("No hay artículos para mostrar.");
            return;
        }

        Scanner sc = new Scanner(System.in);

        for (int i = 0; i < lista.size(); i++) {
            Item item = lista.get(i);
            System.out.printf("%s - %s - $%.2f (Retail: $%.2f) - %s%n",
                    item.getCode(), item.getName(), item.getCurrentPrice(),
                    item.getRetailPrice(), item.getType());

            if ((i + 1) % 20 == 0 && i + 1 < lista.size()) {
                System.out.print("Presiona Enter para continuar: ");
                String input = sc.nextLine().trim();
                if (input.equalsIgnoreCase("q")) {
                    break;
                }
            }
        }
    }
}
