package com.esgi.account.authentication;

import com.esgi.account.model.Account;
import com.esgi.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andreï on 10/04/2016.
 */
@Component
public class AuthenticationManager {
    @Autowired
    AccountService accountService;
    @Autowired
    TokenProvider tokenProvider;

    /*public AuthenticationManager(AccountService accountService ){
        this.accountService = accountService;
        this.tokenProvider = new TokenProvider( this.accountService );
    }*/

    /*public AuthenticationManager( ){
        this.tokenProvider = new TokenProvider( this.accountService );
    }*/

    public String getTokenByAuthentication( ConnectionRequestBody connectionRequestBody)
    {
        Account account = getAccountByAuthentication(connectionRequestBody);
        if ( account == null ) {
            return null;
        }

        return getTokenByAccount( account );
    }

    public String getTokenByAccount( Account account)
    {
        return tokenProvider.getToken( account );
    }

    public Account getAccountByAuthentication( ConnectionRequestBody connectionRequestBody)
    {
        return accountService.getAccountByLoginAndPassword( connectionRequestBody.getLogin(), connectionRequestBody.getPassword() );
    }

    public Account getAccountFromToken( String token )
    {
        return tokenProvider.getAccountFromToken( token );
    }
}
