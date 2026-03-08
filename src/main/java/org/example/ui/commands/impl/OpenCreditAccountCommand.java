package org.example.ui.commands.impl;


import org.example.ui.InputOutput;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankOperations;

public class OpenCreditAccountCommand implements MenuCommand {
    private final BankOperations bank;
    private final InputOutput io;

    public OpenCreditAccountCommand(BankOperations bank, InputOutput io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Добавить кредитный счёт";
    }

    @Override
    public void execute() {
        io.printTitle("Добавление кредитного счёта");

        int customerId = io.getInt("Введите ID клиента: ");
        double limit = io.getDouble("Введите кредитный лимит: ");

        if (limit <= 0) {
            io.printMessage("Ошибка: Кредитный лимит должен быть больше 0");
            return;
        }

        // Получаем строку с информацией о созданном счете
        String accountInfo = bank.openCreditAccount(customerId, limit);

        if (accountInfo != null) {
            io.printMessage("Кредитный счёт добавлен успешно!");
            io.printMessage(accountInfo);
        } else {
            io.printMessage("Ошибка: Клиент с ID=" + customerId + " не найден.");
        }
    }
}