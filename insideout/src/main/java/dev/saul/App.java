package dev.saul;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import dev.saul.models.Emocion;
import dev.saul.models.Momento;
//view, solo iniciar (llamaria a HomeController -> Homeview.printMenu())
public class App {

    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Diario diario = new Diario();
        boolean salir = false;

        while (!salir) {

            //reducir a menos system.out
            System.out.println("\nMi Diario:");
            System.out.println("1. Añadir momento");
            System.out.println("2. Ver todos los momentos disponibles");
            System.out.println("3. Eliminar un momento");
            System.out.println("4. Filtrar los momentos");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarMomento(scanner, diario);
                    break;
                case 2:
                    diario.listarMomentos();
                    break;
                case 3:
                    eliminarMomento(scanner, diario);
                    break;
                case 4:
                    filtrarMomentos(scanner, diario);
                    break;
                case 5:
                    salir = true;
                    System.out.println("Hasta la próxima!!!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

        scanner.close();
    }

    private static void agregarMomento(Scanner scanner, Diario diario) {
        System.out.print("\nIngrese el título: ");
        String titulo = scanner.nextLine();

        System.out.print("Ingresa la fecha (dd/MM/yyyy): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine(), FORMATO_FECHA);

        System.out.print("Ingrese la descripción: ");
        String descripcion = scanner.nextLine();

        System.out.println("\nSelecciona una emoción:");
        Emocion.mostrarOpciones();
        int emocionOpcion = Integer.parseInt(scanner.nextLine());
        Emocion emocion = Emocion.fromInt(emocionOpcion);

        Momento nuevo = new Momento(titulo, descripcion, emocion, fecha);
        diario.agregarMomento(nuevo);
    }

    private static void eliminarMomento(Scanner scanner, Diario diario) {
        System.out.print("\nIngresa el identificador del momento: ");
        int id = Integer.parseInt(scanner.nextLine());
        boolean eliminado = diario.eliminarMomento(id);
        if (eliminado) {
            System.out.println("Momento vivido eliminado correctamente.");
        } else {
            System.out.println("No se encontró ningún momento con ese ID.");
        }
    }

    private static void filtrarMomentos(Scanner scanner, Diario diario) {
        System.out.println("\nFiltrar por ...:");
        System.out.println("1. Emoción");
        System.out.println("2. Fecha");
        System.out.print("Ingrese una opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1:
                Emocion.mostrarOpciones();
                int emocionOpcion = Integer.parseInt(scanner.nextLine());
                Emocion emocion = Emocion.fromInt(emocionOpcion);
                System.out.println("\nLista de momentos vividos:");
                diario.filtrarPorEmocion(emocion);
                break;
            case 2:
                System.out.print("Ingrese la fecha (dd/MM/yyyy): ");
                LocalDate fecha = LocalDate.parse(scanner.nextLine(), FORMATO_FECHA);
                System.out.println("\nLista de momentos vividos:");
                diario.filtrarPorFecha(fecha);
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }
}
