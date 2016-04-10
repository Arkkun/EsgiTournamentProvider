package com.esgi.account.authentication;

import com.esgi.account.model.Account;
import com.esgi.account.service.AccountService;

/**
 * Created by Andreï on 10/04/2016.
 */
public class AuthenticationManager {
    private final AccountService accountService;
    private final TokenProvider tokenProvider;

    public AuthenticationManager(AccountService accountService ){
        this.accountService = accountService;
        this.tokenProvider = new TokenProvider( this.accountService );
    }

    public String getTokenByAuthentication( ConnectionRequestBody connectionRequestBody)
    {
        Account account = getAccountByAuthentication(connectionRequestBody);
        if ( account == null ) {
            return null;
        }

        return tokenProvider.getToken( account );
    }

    public Account getAccountByAuthentication( ConnectionRequestBody connectionRequestBody)
    {
        return accountService.getAccountByLoginAndPassword( connectionRequestBody.getLogin(), connectionRequestBody.getPassword() );
    }
}
