package dev.saul;

import dev.saul.controllers.HomeController;

//view, solo iniciar (llamaria a HomeController -> Homeview.printMenu())
public class App {
    public static void main(String[] args) {
        new HomeController();
    }

}
