package org.example.ui.commands;

import org.example.service.*;
import org.example.service.exporter.ReportServiceStrategy;
import org.example.ui.ConsoleIO;
import java.util.ArrayList;
import java.util.List;

public class MainMenuScreen {
    private final Bank bank;
    private final ConsoleIO io;
    private final List<MenuCommand> commands;

    public MainMenuScreen() {
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        ReportServiceStrategy reportServiceStrategy = new ReportServiceStrategy();
        StorageService storageService = new StorageService();

        this.bank = new Bank(
                customerService,
                accountService,
                transactionService,
                reportServiceStrategy,
                storageService
        );
        this.io = new ConsoleIO();
        this.commands = new ArrayList<>();

        initializeCommands();
    }

    private void initializeCommands() {
        commands.add(new CreateCustomerCommand(bank, io));
        commands.add(new CreateAccountMenuCommand(bank, io));
        commands.add(new DepositCommand(bank, io));
        commands.add(new WithdrawCommand(bank, io));
        commands.add(new TransferCommand(bank, io));
        commands.add(new CustomerAccountsCommand(bank, io));
        commands.add(new DeleteAccountCommand(bank, io));
        commands.add(new BankReportCommand(bank, io));
        commands.add(new LoadReportExtendedCommand(bank, io));
        commands.add(new AnalyticsCommand(bank, io));
    }

    public void show() {
        while (true) {
            printMenu();
            int choice = io.getInt("Выберите пункт меню: ");

            // Логика выхода (последний пункт + 1)
            if (choice == commands.size() + 1) {
                System.out.println("Выход из программы...");
                break;
            }

            if (choice > 0 && choice <= commands.size()) {
                MenuCommand command = commands.get(choice - 1);
                command.execute();
                io.waitForEnter();
            } else {
                System.out.println("Неверный пункт меню. Попробуйте снова.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n=== СИСТЕМА ТИГР-БАНКИНГ ===");
        for (int i = 0; i < commands.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, commands.get(i).getDescription());
        }
        System.out.printf("%d. Выход%n", commands.size() + 1);
        System.out.println("==========================");
    }
}