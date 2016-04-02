package com.esgi.account.service;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.repository.AccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Andreï on 02/04/2016.
 */

@RunWith(MockitoJUnitRunner.class)

public class AccountServiceTest {
    @InjectMocks
    AccountService accountService;
    @Mock
    AccountRepository accountRepository;

    @Test
    public void should_call_findall()
    {
        accountService.getAccounts();
        verify(accountRepository).findAll();
    }
}
