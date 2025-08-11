package dev.saul.views;

public class HomeView {

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
            """
        );
    }
}
