package org.example.ui.commands.impl;

import org.example.ui.ConsoleIO;
import org.example.ui.commands.MenuCommand;
import org.example.ui.commands.interfaces.BankService;

public class DeleteAccountCommand implements MenuCommand {

    private final BankService bank;
    private final ConsoleIO io;

    public DeleteAccountCommand(BankService bank, ConsoleIO io) {  // меняем Bank на BankService
        this.bank = bank;
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Удалить счет";
    }

    @Override
    public void execute() {
        io.printTitle("Удаление счета");

        String accountNumber = io.getString("Введите номер счета для удаления: ");

        // Получаем информацию о счете через сервис
        String accountInfo = bank.getAccountInfo(accountNumber);
        if (accountInfo == null) {
            io.printMessage("Счет с таким номером не найден.");
            return;
        }

        // Показываем информацию
        io.printMessage("\n" + accountInfo);

        // Проверяем возможность удаления
        String deletionCheck = bank.canDeleteAccount(accountNumber);
        if (deletionCheck != null) {
            io.printMessage(deletionCheck);
            return;
        }

        // Подтверждение удаления
        io.printMessage("\nВы уверены, что хотите удалить этот счет? (да/нет): ");
        String confirm = io.getString("").toLowerCase();

        if (confirm.equals("да") || confirm.equals("yes") || confirm.equals("y")) {
            boolean deleted = bank.deleteAccount(accountNumber);
            if (deleted) {
                io.printMessage("Счет успешно удален.");
            } else {
                io.printMessage("Ошибка при удалении счета.");
            }
        } else {
            io.printMessage("Удаление отменено.");
        }
    }
}