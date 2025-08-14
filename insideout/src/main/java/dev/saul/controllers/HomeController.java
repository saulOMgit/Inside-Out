package dev.saul.controllers;

import dev.saul.views.HomeView;

public class HomeController {

    private final DiarioController diarioController;

    public HomeController() {
        this.diarioController = new DiarioController();
    }

    public void run() {
        boolean salir = false;
        while (!salir) {
            HomeView.printMenu();
            int opcion = HomeView.readOption();

            switch (opcion) {
                case 0:
                    salir = true;
                    HomeView.mostrarMensaje("Hasta la próxima!!!");
                    break;
                case 1:
                    HomeView.agregarMomento(diarioController);
                    break;
                case 2:
                    HomeView.listarMomentos(diarioController);
                    break;
                case 3:
                    HomeView.buscarMomentoPorId(diarioController);
                    break;
                case 4:
                    HomeView.eliminarMomento(diarioController);
                    break;
                case 5:
                    HomeView.listarMomentosPorEmocion(diarioController);
                    break;
                case 6: 
                    HomeView.listarMomentosPorMesAno(diarioController);
                    break;
                default:
                    HomeView.mostrarMensaje("Opción no válida.");
            }
        }
        HomeView.closeScanner();
    }

    public DiarioController getDiarioController() {
        return diarioController;
    }
}
