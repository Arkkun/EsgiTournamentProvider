package com.esgi.account.web;

import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.ConnectionRequestBody;
import com.esgi.account.authentication.AuthenticationManager;
import com.esgi.account.exceptions.AccountLoginTooShortException;
import com.esgi.account.model.Account;
import com.esgi.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
    AuthenticationManager authenticationManager;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
        //this.authenticationManager = new AuthenticationManager( this.accountService );
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

    @RequestMapping(value = "/authenticate", method = POST)
    public String authenticateAccount(@RequestBody ConnectionRequestBody connectionRequestBody){
        String token = authenticationManager.getTokenByAuthentication(connectionRequestBody);

        return token;
    }


    //Exemple utilisation AuthenticatedRequestBody
    @RequestMapping(value = "/password", method = POST)
    public String changePassword( @Valid @RequestBody AuthenticatedRequestBody<String> password){
        Account account = authenticationManager.getAccountFromToken( password.getToken() );

        if( account == null )
        {
            return null;
        }
        account.setPassword( password.getBody() );
        accountService.updateAccount( account );

        return authenticationManager.getTokenByAccount( account );
    }
}
