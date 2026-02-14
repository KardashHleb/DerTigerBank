package org.example;

import org.example.ui.commands.MainMenuScreen;
import org.example.ui.Menu;
import org.example.ui.WelcomeScreen;

public class Main {
    public static void main(String[] args) {
        WelcomeScreen screen = new WelcomeScreen();
        Menu menu = new Menu(screen);
        menu.show();

        MainMenuScreen menu1 = new MainMenuScreen();
        menu1.show();
    }
}