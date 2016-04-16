package com.esgi.account.web;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.AuthenticationManager;
import com.esgi.account.authentication.ConnectionRequestBody;
import com.esgi.account.authentication.TokenProvider;
import com.esgi.account.exceptions.AccountFieldNotValidException;
import com.esgi.account.model.Account;
import com.esgi.account.repository.AccountRepository;
import com.esgi.account.repository.SqlDataAccount;
import com.esgi.account.service.AccountService;
import org.joda.time.DateTime;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.staticmock.MockStaticEntityMethods;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Andreï on 02/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = TournamentProviderApplication.class)
@SqlDataAccount
public class AccountControllerTest {
    @InjectMocks
    AccountController accountController;
    @Mock
    AccountService accountService;
    @Mock
    AuthenticationManager authenticationManager;

    @BeforeMethod
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    private Account account = new Account();
    private ConnectionRequestBody connectionRequestBody = new ConnectionRequestBody();
    private AuthenticatedRequestBody<Account> requestBody = new AuthenticatedRequestBody<Account>();

    @Test
    public void should_call_getAccounts() {
        accountController.getAccounts();

        verify(accountService).getAccounts();
    }

    @Test
    public void should_call_updateAccount() {
        connectionRequestBody.setLogin("Login1");
        connectionRequestBody.setPassword("p@ssword123");

        accountController.registerAccount(connectionRequestBody);

        verify(accountService).updateAccount(any());
    }

    @Test
    public void register_should_call_validation_functions() {
        connectionRequestBody.setLogin("Login1");
        connectionRequestBody.setPassword("p@ssword123");

        accountController.registerAccount(connectionRequestBody);

        verify(accountService).validateLogin(connectionRequestBody.getLogin());
        verify(accountService).validatePassword(connectionRequestBody.getPassword());
        verify(accountService).getAccountByLogin(connectionRequestBody.getLogin());
    }

    @Test(expected = com.esgi.account.exceptions.AccountNotFoundException.class)
    public void should_call_getAccountById() {
        int id = 1;

        accountController.getAccountById(id);

        verify(accountService).getAccountById(id);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void should_call_getTokenByAuthentication() {
        connectionRequestBody.setLogin("Login1");
        connectionRequestBody.setPassword("p@ssword123");

        accountController.authenticateAccount(connectionRequestBody);

        verify(authenticationManager).getTokenByAuthentication(connectionRequestBody);
    }

    @Test(expected = java.lang.NullPointerException.class)
    public void changePassword_should_call_mustBeValidToken() {
        String passwordStr = "p@ssword123";
        String token = "token";

        AuthenticatedRequestBody<String> password = new AuthenticatedRequestBody<String>();
        password.setBody(passwordStr);
        password.setToken(token);

        accountController.changePassword(password);

        verify(authenticationManager).mustBeValidToken(password.getToken());
    }
}
