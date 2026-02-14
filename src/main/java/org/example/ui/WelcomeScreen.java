package org.example.ui;


import java.io.PrintStream;
import java.io.UnsupportedEncodingException;


public class WelcomeScreen implements Screen {
    @Override
    public void show() {
        try {
            PrintStream utf8Out = new PrintStream(System.out, true, "UTF-8");

            utf8Out.println("##############################################");
            utf8Out.println("⠄⠄⠄⠄⣠⣤⣤⣄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⢀⣤⣤⣤⠄");
            utf8Out.println("⠄⠄⠄⢰⣿⡿⠿⠿⣷⣄⡀⠄⣀⡤⠤⠶⢤⣤⣤⣤⣤⣤⡤⠴⠦⢤⣀⡀⠄⣠⣴⠿⠿⢿⣿⡇");
            utf8Out.println("⠄⠄⠄⢸⣿⣧⣀⣀⣀⠉⠛⢋⣩⣴⠶⠶⣶⣾⣿⣿⣿⣷⣶⡶⠶⢶⣬⡉⠛⠉⢀⣀⣀⣠⣿⣷");
            utf8Out.println("⠄⠄⠄⢸⣿⣿⠋⠉⠉⣤⣴⡟⠁⣠⣤⣤⣶⣿⣿⣿⣿⣿⣶⣦⣤⣄⡈⠻⣦⣤⡈⠉⠙⢿⣿⡏");
            utf8Out.println("⠄⠄⠄⠘⣿⡇⠄⠄⣴⡿⠋⣰⠟⠛⠉⠉⢉⣻⣿⣿⣿⣟⡋⠉⠉⠙⠻⢦⡈⠻⣷⡀⠄⠄⣿⡇");
            utf8Out.println("⠄⠄⠄⠄⢸⠄⠐⠚⠉⠄⠐⠁⣠⣶⡶⠿⠿⢿⣿⣿⣿⡿⠿⠿⢷⣶⣤⡀⠁⠄⠈⠙⠒⠄⣿");
            utf8Out.println("⠄⠄⠄⣠⠈⠄⡠⠄⣴⣆⠄⢸⠋⣁⣤⣶⣶⠿⣿⣿⣿⡿⣷⣶⣤⣌⡉⢿⠄⢠⣶⡀⠠⡀⠘⢠");
            utf8Out.println("⠄⣀⣴⡏⠄⣾⠁⣼⣿⣿⡀⠈⠄⠻⢯⡀⢀⣼⣿⣿⣿⣷⡀⠄⣼⠿⠁⠈⠄⣸⣿⣿⡀⢹⡀⢸⣷⣄");
            utf8Out.println("⠙⢿⣿⠇⢰⡏⢸⡿⠁⠙⠷⣶⠶⢶⢤⡁⢸⣿⠃⣿⡏⢿⡿⠄⡤⣴⠶⢶⡾⠟⠄⢹⣇⠘⣷⠈⢿⣿⠟");
            utf8Out.println("⣴⣾⡟⠄⢸⡇⢸⡇⢰⠰⣦⣀⠠⣈⣠⣿⡀⢻⠄⣿⡇⢸⠁⣾⣧⣀⠴⢀⣴⡆⣷⠸⣿⠄⣿⠄⠘⣿⣦⣄");
            utf8Out.println("⣻⡿⠄⠄⢸⣧⢸⡇⢿⡀⢯⡉⠳⣦⠉⢻⣧⠈⠄⣿⠄⠄⢰⣿⠉⣱⡶⢋⡽⠃⣿⢀⡏⢰⣿⠄⠄⠹⣿⠋");
            utf8Out.println("⣿⠁⠄⠰⣿⣿⡄⢣⠸⣷⡄⢹⡄⢸⡇⢸⡇⠄⠄⢻⠄⠄⠘⡇⠄⡏⠄⣿⢁⣼⡟⠸⢁⣾⣿⡧⠄⠄⢿⣧");
            utf8Out.println("⣿⣶⣦⠄⠈⣿⣿⣄⠄⢻⣷⠄⣀⡾⠃⠸⡇⠄⠄⠘⠄⠄⠄⡇⠄⠻⣄⠃⣸⣿⠁⢀⣾⣿⠋⠄⢠⣶⣾⣿");
            utf8Out.println("⠁⣾⠏⠄⠄⠿⠿⣿⣦⠄⢻⣿⠋⠄⠄⠄⠇⠄⠄⠄⠄⠄⠄⠃⠄⠄⠙⣿⡿⠁⣠⣿⠿⠿⠄⠄⠈⣿⡀⠹");
            utf8Out.println("⠄⣿⢀⣀⣀⣀⠄⠄⣿⣷⣄⡙⣷⣄⠄⠄⠲⣦⣄⣀⣀⣤⡶⠄⠄⣀⣴⡟⣡⣼⣿⠁⠄⣀⣀⣀⡀⣿⡇");
            utf8Out.println("⠄⣿⡿⠛⠉⣿⡇⠄⠉⠉⠙⠻⣿⣟⠛⠒⠦⠝⢿⣿⡿⠋⠤⠖⠛⢻⣿⡿⠋⠉⠉⠂⠄⣿⠉⠙⢿⣿⡇");
            utf8Out.println("⠄⠛⠄⠄⠄⣿⣷⡿⠟⣿⠁⠄⠈⠻⣗⠢⠤⠤⠄⣿⡀⠠⠤⠴⢺⡿⠋⠄⠄⣻⡟⠿⣷⣿⠄⠄⠄⠙⠄");
            utf8Out.println("⠄⠄⠄⠄⠄⠻⠁⠄⠄⣿⣷⡿⠷⠶⢮⣉⠒⠒⠚⠋⠛⠒⠒⢊⣥⠶⠾⠿⣷⣿⡇⠄⠈⠻");
            utf8Out.println("⠄⠄⠄⠄⠄⠄⠄⠄⠄⠻⠃⠄⠄⠄⠄⠙⠷⣦⣀⣀⣀⣤⡶⠋⠁⠄⠄⠄⠈⠿");
            utf8Out.println("⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠄⠈⠉⠉⠉⠉");
            utf8Out.println("##############################################");
            utf8Out.println("######    WELCOME TO TIGER BANKING    ########");
            utf8Out.println("######      Модуль учета финансов     ########");
            utf8Out.println("##############################################");


        } catch (UnsupportedEncodingException e) {
            System.out.println("##############################################");
            System.out.println("######    WELCOME TO TIGER BANKING    ########");
            System.out.println("##############################################");
            System.out.println("######      Модуль учета финансов     ########");
        }
    }
}