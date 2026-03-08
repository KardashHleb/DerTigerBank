package org.example.ui.commands.impl;

// новый интерфейс
import org.example.ui.InputOutput;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankOperations;

public class TransferCommand implements MenuCommand {
    private final BankOperations bank;
    private final InputOutput io;

    public TransferCommand(BankOperations bank, InputOutput io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Перевод средств";
    }

    @Override
    public void execute() {
        io.printTitle("Перевод между счетами");
        String from = io.getString("Счёт отправителя: ");
        String to = io.getString("Счёт получателя: ");
        double amount = io.getDouble("Сумма: ");

        if (amount <= 0) {
            io.printMessage("Ошибка: Сумма должна быть > 0");
            return;
        }

        if (bank.transfer(from, to, amount)) {
            io.printMessage("Перевод выполнен!");
        } else {
            io.printMessage("Ошибка выполнения перевода.");
        }
    }
}