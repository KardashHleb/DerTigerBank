package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class TransferCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public TransferCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override public String getDescription() { return "Перевод средств"; }

    @Override
    public void execute() {
        io.printTitle("Перевод между счетами");
        String from = io.getString("Счёт отправителя: ");
        String to = io.getString("Счёт получателя: ");
        double amount = io.getDouble("Сумма: ");

        if (amount <= 0) {
            System.out.println("Ошибка: Сумма должна быть > 0");
            return;
        }

        if (bank.transfer(from, to, amount)) {
            System.out.println("Перевод выполнен!");
        } else {
            System.out.println("Ошибка выполнения перевода.");
        }
    }
}