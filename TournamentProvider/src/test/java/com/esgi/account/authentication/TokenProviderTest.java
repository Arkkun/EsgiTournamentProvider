package com.esgi.account.authentication;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.model.Account;
import com.esgi.account.repository.AccountRepository;
import com.esgi.account.repository.SqlDataAccount;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by Andreï on 07/04/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TournamentProviderApplication.class)

@SqlDataAccount
public class TokenProviderTest {
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void should_return_the_expected_token()
    {
        final String expectedToken = "bG9naW5UZXN0ISEhNzgzNjA3MDIwMDAwISEhZTQzYmVhYWVkNWIzYmI5YjViYzgyY2ZlNjhiYWM3ZmYwM2QyYTJhMjE0N2IzNDJmYTMyMTViMWVkMTBkMGMzMQ==";

        final long dateTime = new DateTime( 1994, 10, 31, 13, 37 ).getMillis();
        final String login = "loginTest";
        final String password = "passwordTest";

        Account account = Account.builder()
                .id( 1337 )
                .login( login )
                .password( password )
                .isAdmin(false).build();

        String token = tokenProvider.getToken( account, dateTime );
        assertThat( token, is(expectedToken) );
    }

    @Test
    public void should_say_token_is_valid()
    {
        Account account = accountRepository.findByLogin( "login1" ).get( 0 );
        final long dateTime = DateTime.now().plusDays( 1 ).getMillis();

        String token = tokenProvider.getToken( account, dateTime );
        assertThat( tokenProvider.isTokenValid( token ), is( true ) );
    }

    @Test
    public void should_not_validate_token_when_altered()
    {
        Account account = accountRepository.findByLogin( "login1" ).get( 0 );
        account.setLogin( "login2" );
        final long dateTime = DateTime.now().plusDays( 1 ).getMillis();

        String token = tokenProvider.getToken( account, dateTime );
        assertThat( tokenProvider.isTokenValid( token ), is( false ) );
    }

    @Test
    public void should_not_validate_token_when_expired()
    {
        Account account = accountRepository.findByLogin( "login1" ).get( 0 );
        final long dateTime = new DateTime( 1994, 10, 31, 13, 37 ).getMillis();

        String token = tokenProvider.getToken( account, dateTime );
        assertThat( tokenProvider.isTokenValid( token ), is( false ) );
    }
}
