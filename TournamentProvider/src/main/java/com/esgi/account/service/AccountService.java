package com.esgi.account.service;

import com.esgi.account.model.Account;
import com.esgi.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andreï on 02/04/2016.
 */

//kvlnote utiliser @transactional ?
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    @Autowired
    public AccountService( AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public boolean updateAccount(Account account) {
        accountRepository.save(account);

        return true;
    }
}
