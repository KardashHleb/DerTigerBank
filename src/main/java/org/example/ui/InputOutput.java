package org.example.ui;

import java.time.LocalDate;

public interface InputOutput {
    String getString(String prompt);
    int getInt(String prompt);
    double getDouble(String prompt);
    LocalDate getDate(String prompt);
    void printTitle(String title);
    void println(String message);
    void printMessage(String message);  // если хочешь явно
    void waitForEnter();
}