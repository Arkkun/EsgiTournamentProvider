package com.esgi.account.service;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.exceptions.AccountFieldNotValidException;
import com.esgi.account.model.Account;
import com.esgi.account.repository.AccountRepository;
import com.esgi.account.repository.SqlDataAccount;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Matchers.isNotNull;
import static org.mockito.Mockito.verify;

/**
 * Created by Andreï on 02/04/2016.
 */

@RunWith(MockitoJUnitRunner.class)

@SqlDataAccount
public class AccountServiceTest {
    @InjectMocks
    AccountService accountService;
    @Mock
    AccountRepository accountRepository;

    @Test
    public void should_call_findAll()
    {
        accountService.getAccounts();
        verify(accountRepository).findAll();
    }

    @Test
    public void should_call_findById()
    {
        accountService.getAccountById( 1 );
        verify(accountRepository).findById( 1 );
    }

    @Test
    public void should_call_findByLogin()
    {
        String login = "login1";
        accountService.getAccountByLogin( login );
        verify(accountRepository).findByLogin( login );
    }

    @Test
    public void should_call_getAccountByLoginAndPassword()
    {
        String login = "login1";
        String password = "pass1";
        accountService.getAccountByLoginAndPassword( login, password );
        verify(accountRepository).findByLogin( login );
    }

    @Test
    public void should_transform_3_account_in_3_accountPublic()
    {
        List<Account> accountList = new ArrayList<Account>();
        accountList.add( new Account( 1, "login1", "pass1", false ) );
        accountList.add( new Account( 2, "login2", "pass2", false ) );

        assertThat( accountService.accountListToAccountPublicList( accountList )
        ,hasSize( 2 ) );
    }

    @Test()
    public void should_validate_login()
    {
        accountService.validateLogin( "goodLogin" );
    }
    @Test(expected=AccountFieldNotValidException.class)
    public void should_not_validate_when_login_not_set()
    {
        accountService.validateLogin( null );
    }
    @Test(expected=AccountFieldNotValidException.class)
    public void should_not_validate_when_login_too_short()
    {
        accountService.validateLogin( "to" );
    }
    @Test(expected=AccountFieldNotValidException.class)
    public void should_not_validate_when_login_too_long()
    {
        accountService.validateLogin( "aLoginWayTooLongToBeStockedInOurDatabaseBecauseOurDatabaseIsNotThatGoodLol" );
    }

    @Test()
    public void should_validate_password()
    {
        accountService.validatePassword( "goodPassword" );
    }
    @Test(expected=AccountFieldNotValidException.class)
    public void should_not_validate_when_password_not_set()
    {
        accountService.validatePassword( null );
    }
    @Test(expected=AccountFieldNotValidException.class)
    public void should_not_validate_when_password_too_short()
    {
        accountService.validatePassword( "to" );
    }
    @Test(expected=AccountFieldNotValidException.class)
    public void should_not_validate_when_password_too_long()
    {
        accountService.validatePassword( "aPasswordWayTooLongToBeStockedInOurDatabaseBecauseOurDatabaseIsNotThatGoodLol" );
    }
}
