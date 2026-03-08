package org.example.ui.commands.interfaces;

public interface BankService extends BankOperations, BankReporting, AccountInfoProvider {
    // пустой — объединяет все три
}