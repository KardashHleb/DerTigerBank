package org.example.ui.commands.impl;


import org.example.ui.InputOutput;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankOperations;

public class OpenDebitAccountCommand implements MenuCommand {
    private final BankOperations bank;
    private final InputOutput io;

    public OpenDebitAccountCommand(BankOperations bank, InputOutput io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Добавить дебетовый счёт";
    }

    @Override
    public void execute() {
        io.printTitle("Добавление дебетового счёта");

        // Получаем список клиентов через банк
        String customersList = bank.getAllCustomersInfo();
        if (customersList.isEmpty()) {
            io.printMessage("Сначала создайте клиента.");
            return;
        }

        io.printMessage(customersList);
        int id = io.getInt("Введите ID клиента: ");

        String accountInfo = bank.openDebitAccount(id);

        if (accountInfo != null) {
            io.printMessage("Счёт открыт: " + accountInfo);
        } else {
            io.printMessage("Клиент не найден.");
        }
    }
}