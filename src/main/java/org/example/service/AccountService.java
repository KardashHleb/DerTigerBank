package org.example.service;

import org.example.model.*;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;

public class AccountService {

    public DebitAccount openDebitAccount(Customer customer) {
        if (customer == null) return null;
        DebitAccount account = new DebitAccount(customer);
        customer.addAccount(account);
        return account;
    }

    public CreditAccount openCreditAccount(Customer customer, double limit) {
        if (customer == null) return null;
        CreditAccount account = new CreditAccount(customer, limit);
        customer.addAccount(account);
        return account;
    }

    public Account findByNumber(String number, CustomerService customerService) {
        for (Customer customer : customerService.getAllCustomers()) {
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(number)) {
                    return account;
                }
            }
        }
        return null;
    }

    public Collection<Account> getAllAccounts(CustomerService customerService) {
        List<Account> allAccounts = new ArrayList<>();
        for (Customer customer : customerService.getAllCustomers()) {
            allAccounts.addAll(customer.getAccounts());
        }
        return allAccounts;
    }

    public boolean removeAccount(String accountNumber, CustomerService customerService) {
        for (Customer customer : customerService.getAllCustomers()) {
            Account found = null;
            for (Account account : customer.getAccounts()) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    found = account;
                    break;
                }
            }
            if (found != null) {
                return customer.getAccounts().remove(found);
            }
        }
        return false;
    }
}