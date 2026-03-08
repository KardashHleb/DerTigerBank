package org.example.ui.commands.impl;


import org.example.ui.InputOutput;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankOperations;

public class DepositCommand implements MenuCommand {
    private final BankOperations bank;
    private final InputOutput io;

    public DepositCommand(BankOperations bank, InputOutput io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Пополнить средства (доходы)";
    }

    @Override
    public void execute() {
        io.printTitle("Пополнение счёта");
        String number = io.getString("Номер счёта: ");
        double amount = io.getDouble("Сумма: ");

        if (bank.deposit(number, amount)) {
            io.printMessage("Успешно пополнено.");
        } else {
            io.printMessage("Ошибка пополнения.");
        }
    }
}