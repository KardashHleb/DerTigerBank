package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

import java.io.IOException;

public class BankReportCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public BankReportCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Отчёт по ТИГР-БАНКИНГУ (Экспорт)";
    }

    @Override
    public void execute() {
        io.printTitle("Генерация отчёта");
        System.out.println("Выберите формат вывода:");
        System.out.println("1. Экран (Текст)");
        System.out.println("2. Файл JSON");
        System.out.println("3. Файл YAML");
        System.out.println("4. Файл CSV (Excel)");
        System.out.println("0. Отмена");

        int choice = io.getInt("> ");

        try {
            switch (choice) {
                case 1 -> {
                    System.out.println("\n" + bank.generateBankReport());
                }
                case 2 -> {
                    bank.exportReport("json", "bank_report.json");
                    System.out.println("Успешно сохранено в bank_report.json");
                }
                case 3 -> {
                    bank.exportReport("yaml", "bank_report.yaml");
                    System.out.println("Успешно сохранено в bank_report.yaml");
                }
                case 4 -> {
                    bank.exportReport("csv", "bank_report.csv");
                    System.out.println("Успешно сохранено в bank_report.csv");
                }
                case 0 -> System.out.println("Выход из меню отчетов.");
                default -> System.out.println("Неверный выбор.");
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }
}