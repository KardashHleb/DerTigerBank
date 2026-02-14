package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class CreateCustomerCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public CreateCustomerCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override public String getDescription() { return "Создать аккаунт"; }

    @Override
    public void execute() {
        io.printTitle("Создание аккаунта клиента");
        String name = io.getString("Введите ФИО: ");
        var customer = bank.createCustomer(name);
        System.out.printf("Успешно! ID: %d%n", customer.getId());
    }
}