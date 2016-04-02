package com.esgi.account.web;

import com.esgi.account.exceptions.AccountLoginTooShortException;
import com.esgi.account.model.Account;
import com.esgi.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by Andreï on 02/04/2016.
 */

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(method = GET)
    public List<Account> getAccounts() {
        return accountService.getAccounts();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Account registerAccount(@RequestBody Account account ){

        if( account.getLogin().length() < 3 )
            throw new AccountLoginTooShortException("Account's login is too short");
        accountService.updateAccount( account );
        return account;
    }
}
