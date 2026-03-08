package org.example.ui.commands.impl;


import org.example.ui.InputOutput;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankOperations;

public class CreateCustomerCommand implements MenuCommand {
    private final BankOperations bank;
    private final InputOutput io;

    public CreateCustomerCommand(BankOperations bank, InputOutput io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Создать аккаунт";
    }

    @Override
    public void execute() {
        io.printTitle("Создание аккаунта клиента");
        String name = io.getString("Введите ФИО: ");  // сначала получаем имя
        String customerName = bank.createCustomer(name);  // потом передаём в банк

        if (customerName != null) {
            io.printMessage("Успешно! Клиент: " + customerName);
        } else {
            io.printMessage("Ошибка при создании клиента.");
        }
    }
}