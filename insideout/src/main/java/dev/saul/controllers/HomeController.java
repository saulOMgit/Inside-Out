package dev.saul.controllers;

import dev.saul.views.HomeView;

public class HomeController {

    public HomeController() {
        index();
    }
    
    public void index() {
        HomeView.printMenu();
    }
}
