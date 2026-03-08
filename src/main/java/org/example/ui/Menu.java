package org.example.ui;


import org.example.ui.POJO.WelcomeScreen;

public class Menu {

    private WelcomeScreen screen;

    public Menu(WelcomeScreen screen) {
        this.screen = screen;
    }
    public void show() {
        screen.show();
    }
}

