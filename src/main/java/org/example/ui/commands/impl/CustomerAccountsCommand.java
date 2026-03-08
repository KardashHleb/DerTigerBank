package org.example.ui.commands.impl;


import org.example.ui.InputOutput;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankService;

public class CustomerAccountsCommand implements MenuCommand {
    private final BankService bank;
    private final InputOutput io;

    public CustomerAccountsCommand(BankService bank, InputOutput io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Показать счета клиента";
    }

    @Override
    public void execute() {
        io.printTitle("Счета клиента");
        int id = io.getInt("Введите ID клиента: ");

        // Получаем строковое представление счетов
        String accountsInfo = bank.getCustomerAccountsInfo(id);

        if (accountsInfo.isEmpty()) {
            io.printMessage("Счета не найдены.");
            return;
        }

        io.printMessage(accountsInfo);
    }
}