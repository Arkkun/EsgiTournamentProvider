package com.esgi.match.web;

import com.esgi.AbstractRestTest;
import com.esgi.TournamentProviderApplication;
import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.TokenProvider;
import com.esgi.account.model.Account;
import com.esgi.account.repository.AccountRepository;
import com.esgi.account.repository.SqlDataAccount;
import com.esgi.match.repository.SqlDataMatch;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Andreï on 17/04/2016.
 */

@SqlDataMatch
public class MatchControllerRestTest extends AbstractRestTest {
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    AccountRepository accountRepository;

    @Test
    public void should_get_list_of_2_matchs() {
        when()
                .get("/match")
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(2));
    }

    @Test
    public void should_throw_matchnotfound_when_bad_id() {
        int id = 1337;
        final String exception = com.esgi.match.exceptions.MatchNotFoundException.class.getName();

        given()
                .contentType(JSON)
                .when()
                .get("/match/" + id)
                .then()
                .log().all()
                .statusCode(BAD_REQUEST.value())
                .body("exception", is(exception))
        ;
    }

    @Test
    public void should_set_match_result() {
        int idTeam = 1;
        int idMatch = 1;
        int score = 1;
        Account account = accountRepository.findByLogin( "login2" ).get( 0 );
        String token = tokenProvider.getToken( account );
        System.out.println( token );
        AuthenticatedRequestBody<Integer> authScore = new AuthenticatedRequestBody<Integer>();
        authScore.setBody( score );
        authScore.setToken( token );

        given()
                .contentType(JSON)
                .body(toJson(authScore))
                .when()
                .put("/match/" + idMatch + "/result/" + idTeam )
                .then()
                .log().all()
                .body("score1", is(score))
        ;
    }


}
