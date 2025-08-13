package dev.saul;

import dev.saul.controllers.HomeController;

//view, solo iniciar (llamaria a HomeController -> Homeview.printMenu())
public class App {
    public static void main(String[] args) {
        HomeController home = new HomeController();
        home.run(); // ahora el bucle está aquí, no en el constructor
    }
}

