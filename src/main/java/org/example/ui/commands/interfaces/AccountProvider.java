package org.example.ui.commands.interfaces;


import org.example.model.Account;

import java.util.Collection;


public interface AccountProvider {
    Collection<Account> getAllAccounts();
}