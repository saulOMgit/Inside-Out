package dev.saul.views;

import dev.saul.controllers.PeliculaController;
import dev.saul.models.EmocionEnum;
import dev.saul.models.Pelicula;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class PeliculaView {

    private static Scanner scanner = new Scanner(System.in);

    public static void printMenu() {
        System.out.println("""
                ===== GESTIÓN DE PELÍCULAS =====
                1. Agregar película
                2. Listar películas
                3. Filtrar por género
                4. Exportar películas a CSV
                5. Eliminar película por IMDb ID
                0. Volver al menú principal
                ===============================
                """);
    }

    public static int readOption() {
        System.out.print("Seleccione una opción: ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // opción inválida
        }
    }

    public static void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public static void agregarPelicula(PeliculaController controller) {
        System.out.print("Ingrese IMDb ID: ");
        String imdbId = scanner.nextLine();

        System.out.print("Ingrese título: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese géneros separados por coma: ");
        String generosInput = scanner.nextLine();
        List<String> generos = List.of(generosInput.split("\\s*,\\s*"));

        System.out.println("Seleccione la emoción provocada:");
        int i = 1;
        for (EmocionEnum e : EmocionEnum.values()) {
            System.out.println(i + ". " + capitalize(e.name()));
            i++;
        }

        int opcion = -1;
        while (opcion < 1 || opcion > EmocionEnum.values().length) {
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                if (opcion < 1 || opcion > EmocionEnum.values().length) {
                    mostrarMensaje("Opción inválida. Intente de nuevo.");
                }
            } catch (NumberFormatException e) {
                mostrarMensaje("Debe ingresar un número válido.");
            }
        }

        EmocionEnum emocion = EmocionEnum.values()[opcion - 1];

        System.out.print("Ingrese año de estreno (yyyy): ");
        int anoEstreno = Integer.parseInt(scanner.nextLine());

        controller.agregarPelicula(imdbId, titulo, generos, emocion, anoEstreno);
        mostrarMensaje("Película añadida correctamente.");
    }

    public static void listarPeliculas(PeliculaController controller) {
        List<Pelicula> peliculas = controller.listarPeliculas();
        if (peliculas.isEmpty()) {
            mostrarMensaje("No hay películas registradas.");
        } else {
            System.out.println("Lista de películas:");
            peliculas.forEach(System.out::println);
        }
    }

    public static void filtrarPorGenero(PeliculaController controller) {
        System.out.print("Ingrese el género a filtrar: ");
        String genero = scanner.nextLine();
        List<Pelicula> filtradas = controller.filtrarPorGenero(genero);
        if (filtradas.isEmpty()) {
            mostrarMensaje("No se encontraron películas con ese género.");
        } else {
            System.out.println("Películas del género '" + genero + "':");
            filtradas.forEach(System.out::println);
        }
    }

    public static void exportarPeliculasCSV(PeliculaController controller) {
        System.out.print("Ingrese la ruta del archivo CSV: ");
        String ruta = scanner.nextLine();
        try {
            controller.exportarPeliculasACSV(ruta);
            mostrarMensaje("Películas exportadas correctamente a " + ruta);
        } catch (Exception e) {
            mostrarMensaje("Error al exportar películas: " + e.getMessage());
        }
    }

    public static void eliminarPelicula(PeliculaController controller) {
        System.out.print("Ingrese IMDb ID de la película a eliminar: ");
        String id = scanner.nextLine();
        boolean eliminado = controller.eliminarPelicula(id);
        if (eliminado) {
            mostrarMensaje("Película eliminada correctamente.");
        } else {
            mostrarMensaje("No se encontró película con ese IMDb ID.");
        }
    }

    static String capitalize(String str) {
        return str.charAt(0) + str.substring(1).toLowerCase();
    }

    public static void setScanner(InputStream in) {
        scanner = new Scanner(in);
    }

    public static void closeScanner() {
        scanner.close();
    }
}
