package com.esgi.account.repository;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.model.Account;
import org.hamcrest.Matcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

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

}
