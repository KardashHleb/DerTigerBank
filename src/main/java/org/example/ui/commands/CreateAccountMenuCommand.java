package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class CreateAccountMenuCommand implements MenuCommand {
    private final ConsoleIO io;
    private final OpenDebitAccountCommand debitCommand;
    private final OpenCreditAccountCommand creditCommand;

    public CreateAccountMenuCommand(Bank bank, ConsoleIO io) {
        this.io = io;
        this.debitCommand = new OpenDebitAccountCommand(bank, io);
        this.creditCommand = new OpenCreditAccountCommand(bank, io);
    }

    @Override
    public String getDescription() {
        return "Открыть счет";
    }

    @Override
    public void execute() {
        io.printTitle("Открытие нового счета");

        System.out.println("Выберите тип счета:");
        System.out.println("1. Дебетовый счет");
        System.out.println("2. Кредитный счет");
        System.out.println("0. Отмена");

        int choice = io.getInt("Ваш выбор: ");

        switch (choice) {
            case 1:
                debitCommand.execute();
                break;
            case 2:
                creditCommand.execute();
                break;
            case 0:
                System.out.println("Операция отменена.");
                break;
            default:
                System.out.println("Неверный выбор. Операция отменена.");
        }
    }
}