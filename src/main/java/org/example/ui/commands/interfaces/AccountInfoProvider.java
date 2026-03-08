package org.example.ui.commands.interfaces;

public interface AccountInfoProvider {
    String getAccountInfo(String accountNumber);
    String canDeleteAccount(String accountNumber);
    String getCustomerAccountsInfo(int customerId);
}
