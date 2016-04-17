package com.esgi.account.web;

import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.ConnectionRequestBody;
import com.esgi.account.authentication.AuthenticationManager;
import com.esgi.account.authentication.Token;
import com.esgi.account.exceptions.AccountFieldNotValidException;
import com.esgi.account.exceptions.AccountNotFoundException;
import com.esgi.account.model.Account;
import com.esgi.account.model.AccountData;
import com.esgi.account.model.AccountPublic;
import com.esgi.account.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 * Created by Andreï on 02/04/2016.
 */

@RestController
@RequestMapping("/account")
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
    public List<AccountPublic> getAccounts( ) {
        List<AccountPublic> accountPublicList = accountService.accountListToAccountPublicList( accountService.getAccounts() );
        return accountPublicList;
    }

    @RequestMapping(value="/{id}", method = GET)
    public AccountData getAccountById(@PathVariable(value="id") int id )
    {
        Account account = accountService.getAccountById( id );
        if( account == null )
        {
            throw new AccountNotFoundException();
        }

        AccountPublic accountP = new AccountPublic( account );
        AccountData accountD = new AccountData( accountP );

        return accountD;
    }

    @RequestMapping(value="/my-account", method = POST)
    public AccountData getMyAccount(@RequestBody Token token)
    {
        Account account = authenticationManager.getAccountFromToken( token.getToken() );

        AccountPublic accountP = new AccountPublic( account );
        AccountData accountD = new AccountData( accountP );

        return accountD;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public AccountPublic registerAccount(@RequestBody ConnectionRequestBody connectionRequestBody )
    {
        Account account;
        String login = connectionRequestBody.getLogin();
        String password = connectionRequestBody.getPassword();
        accountService.validateLogin( login );
        accountService.validatePassword( password );

        if( accountService.getAccountByLogin( login ) != null )
        {
            throw new AccountFieldNotValidException( "Account's login already in use" );
        }

        account = Account.builder().id( 0 ).login( login ).password( password ).isAdmin( false ).build();
        accountService.updateAccount( account );
        return new AccountPublic( account );
    }

    @RequestMapping(value = "/authenticate", method = POST)
    public Token authenticateAccount(@RequestBody ConnectionRequestBody connectionRequestBody )
    {
        return new Token( authenticationManager.getTokenByAuthentication(connectionRequestBody) );
    }


    //Exemple utilisation AuthenticatedRequestBody
    @RequestMapping(value = "/password", method = POST)
    public Token changePassword( @RequestBody AuthenticatedRequestBody<String> password ){
        authenticationManager.mustBeValidToken( password.getToken() );
        accountService.validatePassword( password.getBody() );

        Account account = authenticationManager.getAccountFromToken( password.getToken() );
        account.setPassword( password.getBody() );
        accountService.updateAccount( account );

        return new Token( authenticationManager.getTokenByAccount( account ) );
    }
}
