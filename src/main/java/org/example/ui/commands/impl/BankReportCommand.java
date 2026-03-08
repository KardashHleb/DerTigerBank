package org.example.ui.commands.impl;


import org.example.ui.ConsoleIO;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankService;

import java.io.IOException;
import java.util.Map;

public class BankReportCommand implements MenuCommand {
    private final BankService bank;
    private final ConsoleIO io;
    private final Map<Integer, String> formats = Map.of(
            2, "json",
            3, "yaml",
            4, "csv"
    );

    public BankReportCommand(BankService bank, ConsoleIO io) {
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
        printMenu();

        int choice = io.getInt("> ");

        try {
            switch (choice) {
                case 0 -> System.out.println("Выход из меню отчетов.");
                case 1 -> System.out.println("\n" + bank.generateBankReport());
                default -> export(choice);
            }
        } catch (IOException e) {
            System.err.println("Ошибка при сохранении файла: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Произошла непредвиденная ошибка: " + e.getMessage());
        }
    }

    private void printMenu() {
        System.out.println("1. Экран (Текст)");
        System.out.println("2. Файл JSON");
        System.out.println("3. Файл YAML");
        System.out.println("4. Файл CSV (Excel)");
        System.out.println("0. Отмена");
    }

    private void export(int choice) throws IOException {
        String format = formats.get(choice);

        if (format == null) {
            System.out.println("Неверный выбор.");
            return;
        }

        String filename = "bank_report." + format;
        bank.exportReport(format, filename);
        System.out.println("Успешно сохранено в " + filename);
    }
}