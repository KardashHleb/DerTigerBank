package org.example.ui.commands;

import org.example.model.CreditAccount;
import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class OpenCreditAccountCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public OpenCreditAccountCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Добавить кредитный счёт";
    }

    @Override
    public void execute() {
        System.out.println("\n--- Добавление кредитного счёта ---");

        int customerId = io.getInt("Введите ID клиента: ");
        double limit = io.getDouble("Введите кредитный лимит: ");

        if (limit <= 0) {
            System.out.println("Ошибка: Кредитный лимит должен быть больше 0");
            return;
        }

        CreditAccount account = bank.openCreditAccount(customerId, limit);

        if (account != null) {
            System.out.println("Кредитный счёт добавлен успешно!");
            System.out.printf("Номер счёта: %s%n", account.getAccountNumber());
            System.out.printf("Владелец: %s%n", account.getOwner().getFullName());
            System.out.printf("Кредитный лимит: %.2f%n", account.getCreditLimit());
        } else {
            System.out.println("Ошибка: Клиент с ID=" + customerId + " не найден.");
        }
    }
}