package com.esgi.tournament.web;

import com.esgi.AbstractRestTest;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.repository.SqlDataTournament;
import com.jayway.restassured.http.ContentType;
import org.junit.Test;

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

    @Test
    public void should_get_a_tournament() {
        when()
                .get("/tournament")
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(1));
    }

    @Test
    public void should_create_new_tournament() {
        final String name = "Tournament 95";
        final String description = "Tournament description for tournament 95";
        final String date = "19/04/2016";
        final int tournamentSize = 16;
        final int teamSize = 5;

        final Tournament tournament = Tournament.builder()
                .name(name)
                .description(description)
                .date(date)
                .tournamentSize(tournamentSize)
                .teamSize(teamSize);

        given()
                .contentType(ContentType.JSON)
                .body(toJson(tournament))
                .when()
                .post("/tournament")
                .then()
                .log().all()
                .statusCode(CREATED.value())
                .body("name", is(name));

    }

}
