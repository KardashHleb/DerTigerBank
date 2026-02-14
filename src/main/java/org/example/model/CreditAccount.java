package org.example.model;

public class CreditAccount extends Account {
    private final double creditLimit;
    private double availableCredit;

    public CreditAccount(Customer owner, double creditLimit) {
        super(owner);
        this.creditLimit = creditLimit;
        this.availableCredit = creditLimit;
    }

    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма снятия должна быть больше 0");
            addTransaction(TransactionFactory.createFailedWithdrawal(
                    getAccountNumber(), amount, "Сумма снятия должна быть больше 0"));
            return false;
        }

        double totalAvailable = this.balance + availableCredit;

        if (amount > totalAvailable) {
            System.out.println("Ошибка: Превышен доступный лимит");
            System.out.printf("Баланс: %.2f, Кредитный лимит: %.2f, Доступно: %.2f, Запрошено: %.2f%n",
                    balance, creditLimit, totalAvailable, amount);
            addTransaction(TransactionFactory.createFailedWithdrawal(
                    getAccountNumber(), amount, "Превышен доступный лимит"));
            return false;
        }

        if (amount <= this.balance) {
            this.balance -= amount;
        } else {
            double creditNeeded = amount - this.balance;
            this.balance = 0;
            this.availableCredit -= creditNeeded;
        }

        addTransaction(TransactionFactory.createSuccessWithdrawal(getAccountNumber(), amount));
        System.out.printf("Со счёта %s снято %.2f. Баланс: %.2f, Доступный кредит: %.2f%n",
                getAccountNumber(), amount, balance, availableCredit);
        return true;
    }

    @Override
    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма пополнения должна быть больше 0");
            addTransaction(TransactionFactory.createFailedDeposit(
                    getAccountNumber(), amount, "Сумма пополнения должна быть больше 0"));
            return false;
        }

        double creditUsed = creditLimit - availableCredit;

        if (creditUsed > 0) {
            if (amount <= creditUsed) {
                availableCredit += amount;
                addTransaction(TransactionFactory.createDeposit(
                        getAccountNumber(), amount, true, "Погашена часть кредитной задолженности"));
                System.out.printf("Погашена кредитная задолженность на %.2f%n", amount);
            } else {
                availableCredit = creditLimit;
                double remainingAmount = amount - creditUsed;
                this.balance += remainingAmount;
                addTransaction(TransactionFactory.createDeposit(
                        getAccountNumber(), amount, true,
                        "Погашена кредитная задолженность и остаток зачислен на баланс"));
                System.out.printf("Погашена кредитная задолженность на %.2f. Остаток %.2f зачислен на баланс%n",
                        creditUsed, remainingAmount);
            }
        } else {
            this.balance += amount;
            addTransaction(TransactionFactory.createSuccessDeposit(getAccountNumber(), amount));
        }

        System.out.printf("Счёт %s пополнен на %.2f. Баланс: %.2f, Доступный кредит: %.2f%n",
                getAccountNumber(), amount, balance, availableCredit);
        return true;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public double getAvailableCredit() {
        return availableCredit;
    }

    public double getCreditUsed() {
        return creditLimit - availableCredit;
    }

    @Override
    public String toString() {
        return String.format("[Кредитный] %s, Лимит: %.2f, Доступно: %.2f",
                super.toString(), creditLimit, availableCredit);
    }
}