package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class OpenDebitAccountCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public OpenDebitAccountCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override public String getDescription() { return "Добавить дебетовый счёт"; }

    @Override
    public void execute() {
        io.printTitle("Добавление дебетового счёта");
        var customers = bank.getCustomers();
        if (customers.isEmpty()) {
            System.out.println("Сначала создайте клиента.");
            return;
        }

        customers.forEach(c -> System.out.printf("ID: %d, ФИО: %s%n", c.getId(), c.getFullName()));
        int id = io.getInt("Введите ID клиента: ");

        var account = bank.openDebitAccount(id);
        if (account != null) {
            System.out.println("Счёт открыт: " + account.getAccountNumber());
        } else {
            System.out.println("Клиент не найден.");
        }
    }
}