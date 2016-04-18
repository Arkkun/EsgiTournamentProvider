package com.esgi.tournament.web;

import com.esgi.AbstractRestTest;
import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.TokenProvider;
import com.esgi.account.repository.AccountRepository;
import com.esgi.team.model.TeamCreation;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.model.TournamentCreation;
import com.esgi.tournament.repository.SqlDataTournament;
import com.jayway.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Thomas on 17/04/2016.
 */

@SqlDataTournament
public class TournamentControllerRestTest extends AbstractRestTest {
    @Autowired
    TokenProvider tokenProvider;
    @Autowired
    AccountRepository accountRepository;
    @Test
    public void should_get_a_tournament() {
        when()
                .get("/tournament")
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(3));
    }

    @Test
    public void should_create_new_tournament() {
        final String name = "Tournament 95";
        final String description = "Tournament description for tournament 95";
        final String date = "19/04/2016";
        final int tournamentSize = 16;
        final int teamSize = 5;

        final TournamentCreation tournamentCreation = TournamentCreation.builder()
                .name(name)
                .description(description)
                .date(date)
                .tournamentSize(tournamentSize)
                .teamSize(teamSize)
                .build();

        final String token = tokenProvider.getToken( accountRepository.findByLogin ("root" ).get(0) );
        final AuthenticatedRequestBody<TournamentCreation> authTournamentCreation = new AuthenticatedRequestBody<TournamentCreation>();
        authTournamentCreation.setBody( tournamentCreation );
        authTournamentCreation.setToken( token );

        given()
                .contentType(ContentType.JSON)
                .body(toJson(authTournamentCreation))
                .when()
                .post("/tournament")
                .then()
                .log().all()
                .statusCode(CREATED.value())
                .body("name", is(name));

    }

}
