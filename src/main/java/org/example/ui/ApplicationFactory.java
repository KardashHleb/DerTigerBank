package org.example.ui;

import org.example.service.*;
import org.example.service.exporter.ReportServiceStrategy;
import org.example.service.importer.StorageService;

import org.example.ui.commands.MainMenuScreen;




public class ApplicationFactory {

    public static MainMenuScreen createMainMenu() {
        // 1. Сервисы
        CustomerService customerService = new CustomerService();
        AccountService accountService = new AccountService();
        TransactionService transactionService = new TransactionService();
        ReportServiceStrategy reportServiceStrategy = new ReportServiceStrategy();
        StorageService storageService = new StorageService();

        // 2. Bank
        Bank bank = new Bank(
                customerService,
                accountService,
                transactionService,
                reportServiceStrategy,
                storageService
        );

        // 3. IO
        ConsoleIO io = new ConsoleIO();

        // 4. Меню
        return new MainMenuScreen(bank, bank, io);
    }
}