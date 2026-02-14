package org.example.model;

public class DebitAccount extends Account {
    public DebitAccount(Customer owner) {
        super(owner);
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма снятия должна быть больше 0");
            addTransaction(TransactionFactory.createFailedWithdrawal(
                    getAccountNumber(), amount, "Сумма снятия должна быть больше 0"));
            return false;
        }

        if (amount > this.balance) {
            System.out.println("Ошибка: Недостаточно средств на дебетовом счёте");
            System.out.printf("Текущий баланс: %.2f, запрошено: %.2f%n", balance, amount);
            addTransaction(TransactionFactory.createFailedWithdrawal(
                    getAccountNumber(), amount, "Недостаточно средств"));
            return false;
        }

        this.balance -= amount;
        addTransaction(TransactionFactory.createSuccessWithdrawal(getAccountNumber(), amount));

        System.out.printf("Со счёта %s снято %.2f. Новый баланс: %.2f%n",
                getAccountNumber(), amount, balance);
        return true;
    }

    @Override
    public String toString() {
        return String.format("[Дебетовый] %s", super.toString());
    }
}