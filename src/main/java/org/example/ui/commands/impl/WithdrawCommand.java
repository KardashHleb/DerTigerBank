package org.example.ui.commands.impl;

import org.example.ui.InputOutput;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankOperations;

public class WithdrawCommand implements MenuCommand {
    private final BankOperations bank;
    private final InputOutput io;

    public WithdrawCommand(BankOperations bank, InputOutput io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Снятие средств (расходы)";
    }

    @Override
    public void execute() {
        io.printTitle("Снятие со счёта");
        String accountNumber = io.getString("Введите номер счёта: ");
        double amount = io.getDouble("Введите сумму для снятия: ");

        if (amount <= 0) {
            io.printMessage("Ошибка: Сумма должна быть больше 0");
            return;
        }

        boolean success = bank.withdraw(accountNumber, amount);
        if (success) {
            io.printMessage("Операция выполнена успешно.");
        } else {
            io.printMessage("Ошибка при снятии со счёта (недостаточно средств или неверный номер).");
        }
    }
}