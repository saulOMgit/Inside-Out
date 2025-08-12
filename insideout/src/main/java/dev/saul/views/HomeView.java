package dev.saul.views;

import dev.saul.controllers.DiarioController;
import dev.saul.models.EmocionEnum;
import dev.saul.models.Momento;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class HomeView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void printMenu() {
        System.out.println(
                """
                        ===== MI DIARIO =====
                        1. Agregar momento
                        2. Listar momentos
                        3. Buscar momento por ID
                        4. Editar momento
                        5. Eliminar momento
                        0. Salir
                        =====================
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

    public static void agregarMomento(DiarioController diarioController) {
        System.out.print("Ingrese el título: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine(), FORMATO_FECHA);

        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();

        System.out.println("Selecciona una emoción:");
        HomeView.mostrarOpcionesEmocion();
        int opcion = Integer.parseInt(scanner.nextLine());
        EmocionEnum emocion = HomeView.fromIntEmocion(opcion);

        Momento momento = new Momento(titulo, descripcion, emocion, fecha);
        diarioController.agregarMomento(momento);

        mostrarMensaje("Momento vivido añadido correctamente.");
    }

    public static void listarMomentos(DiarioController diarioController) {
        List<Momento> momentos = diarioController.listarMomentos();
        if (momentos.isEmpty()) {
            mostrarMensaje("No hay momentos guardados.");
        } else {
            System.out.println("Lista de momentos vividos:");
            momentos.forEach(System.out::println);
        }
    }

    public static void buscarMomentoPorId(DiarioController diarioController) {
        System.out.print("Ingrese el ID del momento: ");
        int id = Integer.parseInt(scanner.nextLine());
        List<Momento> momentos = diarioController.listarMomentos();
        momentos.stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .ifPresentOrElse(
                        System.out::println,
                        () -> mostrarMensaje("No se encontró momento con ese ID."));
    }

    public static void editarMomento(DiarioController diarioController) {
        // Implementar cuando se haga editar, si quieres te ayudo después
        mostrarMensaje("Función no implementada aún.");
    }

    public static void eliminarMomento(DiarioController diarioController) {
        System.out.print("Ingrese el ID del momento a eliminar: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean eliminado = diarioController.eliminarMomento(id);
        if (eliminado) {
            mostrarMensaje("Momento eliminado correctamente.");
        } else {
            mostrarMensaje("No se encontró momento con ese ID.");
        }
    }

    public static void mostrarOpcionesEmocion() {
        int i = 1;
        for (EmocionEnum e : EmocionEnum.values()) {
            System.out.println(i + ". " + capitalize(e.name()));
            i++;
        }
    }

    public static EmocionEnum fromIntEmocion(int opcion) {
        if (opcion < 1 || opcion > EmocionEnum.values().length) {
            throw new IllegalArgumentException("Opción inválida");
        }
        return EmocionEnum.values()[opcion - 1];
    }

    private static String capitalize(String str) {
        return str.charAt(0) + str.substring(1).toLowerCase();
    }

    public static void closeScanner() {
        scanner.close();
    }
}
