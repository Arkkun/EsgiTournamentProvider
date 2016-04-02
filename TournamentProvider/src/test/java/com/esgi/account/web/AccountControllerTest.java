package com.esgi.account.web;

import com.esgi.account.exceptions.AccountLoginTooShortException;
import com.esgi.account.model.Account;
import com.esgi.account.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.staticmock.MockStaticEntityMethods;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

/**
 * Created by Andreï on 02/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {
    @InjectMocks
    AccountController accountController;
    @Mock
    AccountService accountService;

    private Account account = new Account();

    @Test
    public void should_call_getaccounts(){
        accountController.getAccounts();

        verify( accountService ).getAccounts();
    }

    @Test
    public void should_call_updateaccount(){
        account.setLogin( "Login1" );
        account.setPassword("p@ssword123");

        accountController.registerAccount( account );

        verify( accountService ).updateAccount( account );
    }

    @Test( expected = AccountLoginTooShortException.class )
    public void should_throw_logintooshort(){
        account.setLogin( "Lo" );
        account.setPassword("p@ssword123");

        accountController.registerAccount( account );
    }
}
