package org.example.ui;


public class Menu {

    private WelcomeScreen screen;

    public Menu(WelcomeScreen screen) {
        this.screen = screen;
    }
    public void show() {
        screen.show();
    }
}

