package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class WithdrawCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public WithdrawCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Снятие средств (расходы)";
    }

    @Override
    public void execute() {
        System.out.println("\n--- Снятие со счёта ---");
        String accountNumber = io.getString("Введите номер счёта: ");
        double amount = io.getDouble("Введите сумму для снятия: ");

        if (amount <= 0) {
            System.out.println("Ошибка: Сумма должна быть больше 0");
            return;
        }

        boolean success = bank.withdraw(accountNumber, amount);
        if (success) {
            System.out.println("Операция выполнена успешно.");
        } else {
            System.out.println("Ошибка при снятии со счёта (недостаточно средств или неверный номер).");
        }
    }
}