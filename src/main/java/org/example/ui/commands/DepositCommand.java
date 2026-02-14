package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class DepositCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public DepositCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override public String getDescription() { return "Пополнить средства (доходы)"; }

    @Override
    public void execute() {
        io.printTitle("Пополнение счёта");
        String number = io.getString("Номер счёта: ");
        double amount = io.getDouble("Сумма: ");

        if (bank.deposit(number, amount)) {
            System.out.println("Успешно пополнено.");
        } else {
            System.out.println("Ошибка пополнения.");
        }
    }
}