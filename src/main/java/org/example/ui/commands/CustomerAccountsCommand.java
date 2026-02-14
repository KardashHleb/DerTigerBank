package org.example.ui.commands;

import org.example.model.CreditAccount;
import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class CustomerAccountsCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public CustomerAccountsCommand(Bank bank, ConsoleIO io) {
        this.bank = bank;
        this.io = io;
    }

    @Override public String getDescription() { return "Показать счета клиента"; }

    @Override
    public void execute() {
        io.printTitle("Счета клиента");
        int id = io.getInt("Введите ID клиента: ");
        var accounts = bank.getCustomerAccounts(id);

        if (accounts.isEmpty()) {
            System.out.println("Счета не найдены.");
            return;
        }

        for (var acc : accounts) {
            System.out.println(acc);
            if (acc instanceof CreditAccount credit) {
                System.out.printf("   Лимит: %.2f, Доступно: %.2f%n",
                        credit.getCreditLimit(), credit.getAvailableCredit());
            }
        }
    }
}