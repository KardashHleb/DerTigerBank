package org.example.ui.commands;

import org.example.service.Bank;
import org.example.ui.ConsoleIO;

public class DeleteAccountCommand implements MenuCommand {
    private final Bank bank;
    private final ConsoleIO io;

    public DeleteAccountCommand(Bank bank, ConsoleIO io) {
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

        // Проверяем существование счета
        var account = bank.findAccount(accountNumber);
        if (account == null) {
            System.out.println("Счет с таким номером не найден.");
            return;
        }

        // Показываем информацию о счете
        System.out.println("\nИнформация о счете:");
        System.out.printf("Владелец: %s%n", account.getOwner().getFullName());
        System.out.printf("Тип: %s%n", account.getClass().getSimpleName());
        System.out.printf("Баланс: %.2f%n", account.getBalance());

        // Проверяем баланс
        if (account.getBalance() != 0) {
            System.out.println("Невозможно удалить счет с ненулевым балансом.");
            return;
        }
        // Подтверждение удаления
        System.out.print("\nВы уверены, что хотите удалить этот счет? (да/нет): ");
        String confirm = io.getString("").toLowerCase();

        if (confirm.equals("да") || confirm.equals("yes") || confirm.equals("y")) {
            boolean deleted = bank.deleteAccount(accountNumber);
            if (deleted) {
                System.out.println("Счет успешно удален.");
            } else {
                System.out.println("Ошибка при удалении счета.");
            }
        } else {
            System.out.println("Удаление отменено.");
        }
    }
}