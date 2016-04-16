package com.esgi.account.repository;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.model.Account;
import com.esgi.account.service.AccountService;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Andreï on 02/04/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TournamentProviderApplication.class)
@SqlDataAccount
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;

    @Test
    public void should_find_3_accounts() {
        assertThat(accountRepository.findAll(), hasSize(3));
    }

    @Test
    public void should_find_an_account() {
        String login = "login1";
        assertThat(accountRepository.findByLogin( login ), hasSize(1) );
    }

    @Test
    public void should_not_find_an_account() {
        String login = "notALogin1";
        assertThat(accountRepository.findByLogin( login ), hasSize(0) );
    }

    @Test
    public void should_find_an_account_by_id() {
        int id = 1;
        assertThat(accountRepository.findById( id ), hasSize(1) );
    }

    @Test
    public void should_not_find_an_account_by_id() {
        int id = 100;
        assertThat(accountRepository.findById( id ), hasSize(0) );
    }

}
