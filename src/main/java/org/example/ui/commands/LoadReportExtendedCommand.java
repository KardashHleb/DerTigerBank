package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class LoadReportExtendedCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public LoadReportExtendedCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Загрузить отчёт из файла (JSON/YAML/CSV)";
    }

    @Override
    public void execute() {
        io.printTitle("Загрузка отчёта из файла");

        io.println("Поддерживаемые форматы:");
        io.println("  - JSON  (файлы .json)");
        io.println("  - YAML  (файлы .yaml или .yml)");
        io.println("  - CSV   (файлы .csv)");
        io.println("");

        String path = io.getString("Введите путь к файлу: ");

        String fileContent = bank.readReportFromFile(path);
        System.out.println("\n" + fileContent);
    }
}