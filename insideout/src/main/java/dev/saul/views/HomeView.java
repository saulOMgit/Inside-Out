package dev.saul.views;

import dev.saul.controllers.DiarioController;
import dev.saul.models.EmocionEnum;
import dev.saul.models.Momento;
import dev.saul.models.PositividadEnum;

import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class HomeView {

    private static Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void printMenu() {
        System.out.println(
                """
                        ===== MI DIARIO =====
                        1. Agregar momento
                        2. Listar momentos
                        3. Buscar momento por ID
                        4. Eliminar momento
                        5. Listar momentos por emoción
                        6. Listar momentos por mes
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

        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
            String input = scanner.nextLine();
            try {
                fecha = LocalDate.parse(input, FORMATO_FECHA);
            } catch (Exception e) {
                mostrarMensaje("Formato de fecha inválido. Intente de nuevo.");
            }
        }

        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();

        System.out.println("Selecciona una emoción:");
        mostrarOpcionesEmocion();
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
        EmocionEnum emocion = fromIntEmocion(opcion);

        System.out.println("¿El momento fue bueno o malo? (1 = Bueno, 2 = Malo)");
        opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer

        PositividadEnum positividad = (opcion == 1) ? PositividadEnum.BUENO : PositividadEnum.MALO;

        Momento momento = new Momento(titulo, fecha, descripcion, emocion, positividad);
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

    public static void listarMomentosPorEmocion(DiarioController diarioController) {
        System.out.println("Selecciona una emoción para filtrar:");
        mostrarOpcionesEmocion();
        int opcion = Integer.parseInt(scanner.nextLine());
        try {
            EmocionEnum emocion = fromIntEmocion(opcion);
            List<Momento> momentos = diarioController.filtrarPorEmocion(emocion);
            if (momentos.isEmpty()) {
                mostrarMensaje("No hay momentos con esa emoción.");
            } else {
                System.out.println("Momentos con emoción " + capitalize(emocion.name()) + ":");
                momentos.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            mostrarMensaje("Opción inválida.");
        }
    }

    public static void listarMomentosPorMesAno(DiarioController diarioController) {
        System.out.print("Ingrese el mes (1-12): ");
        int mes = Integer.parseInt(scanner.nextLine());
        System.out.print("Ingrese el año (yyyy): ");
        int ano = Integer.parseInt(scanner.nextLine());

        List<Momento> momentos = diarioController.filtrarPorMesAno(mes, ano);
        if (momentos.isEmpty()) {
            mostrarMensaje("No hay momentos registrados en ese mes.");
        } else {
            System.out.println("Momentos del " + mes + "/" + ano + ":");
            momentos.forEach(System.out::println);
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
