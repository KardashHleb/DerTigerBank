package org.example.model;

public class TransactionFactory {

    public static Transaction createDeposit(String accountNumber, double amount,
                                            boolean success, String message) {
        return new Transaction(
                accountNumber,
                accountNumber,
                amount,
                TransactionType.DEPOSIT,
                success,
                message
        );
    }

    public static Transaction createWithdrawal(String accountNumber, double amount,
                                               boolean success, String message) {
        return new Transaction(
                accountNumber,
                accountNumber,
                amount,
                TransactionType.WITHDRAWAL,
                success,
                message
        );
    }

    public static Transaction createTransfer(String fromAccount, String toAccount,
                                             double amount, boolean success, String message) {
        return new Transaction(
                fromAccount,
                toAccount,
                amount,
                TransactionType.TRANSFER,
                success,
                message
        );
    }

    public static Transaction createSuccessDeposit(String accountNumber, double amount) {
        return createDeposit(accountNumber, amount, true,
                TransactionType.DEPOSIT.getDescription());
    }

    public static Transaction createSuccessWithdrawal(String accountNumber, double amount) {
        return createWithdrawal(accountNumber, amount, true,
                TransactionType.WITHDRAWAL.getDescription());
    }

    public static Transaction createFailedDeposit(String accountNumber, double amount,
                                                  String reason) {
        return createDeposit(accountNumber, amount, false, reason);
    }

    public static Transaction createFailedWithdrawal(String accountNumber, double amount,
                                                     String reason) {
        return createWithdrawal(accountNumber, amount, false, reason);
    }
}