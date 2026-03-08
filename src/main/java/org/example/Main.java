package org.example;

import org.example.ui.ApplicationFactory;
import org.example.ui.Menu;
import org.example.ui.POJO.WelcomeScreen;
import org.example.ui.commands.MainMenuScreen;

public class Main {
    public static void main(String[] args) {
        // Приветствие
        new Menu(new WelcomeScreen()).show();

        // Всё остальное через фабрику
        MainMenuScreen mainMenu = ApplicationFactory.createMainMenu();
        mainMenu.show();
    }
}