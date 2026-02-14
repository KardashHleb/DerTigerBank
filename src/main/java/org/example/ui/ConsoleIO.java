package org.example.ui;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ConsoleIO {
    private final Scanner scanner = new Scanner(System.in);

    public String getString(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int getInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.print("Ошибка! Введите целое число: ");
            scanner.next();
        }
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }
    public LocalDate getDate(String prompt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        while (true) {
            try {
                String input = getString(prompt);
                if (input.trim().isEmpty()) {
                    return LocalDate.now();
                }
                return LocalDate.parse(input, formatter);
            } catch (Exception e) {
                System.out.println("Ошибка: Неверный формат даты. Используйте дд.мм.гггг");
            }
        }
    }
    public double getDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.print("Ошибка! Введите число: ");
            scanner.next();
        }
        double input = scanner.nextDouble();
        scanner.nextLine();
        return input;
    }

    public void printTitle(String title) {
        System.out.println("\n--- " + title + " ---");
    }

    public void waitForEnter() {
        System.out.println("\nНажмите Enter для продолжения...");
        scanner.nextLine();
    }

    public void println(String message) {
        System.out.println(message);
    }
}