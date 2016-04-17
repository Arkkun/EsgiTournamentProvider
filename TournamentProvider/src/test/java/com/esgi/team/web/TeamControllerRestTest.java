package com.esgi.team.web;

import com.esgi.AbstractRestTest;
import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.Token;
import com.esgi.account.authentication.TokenProvider;
import com.esgi.account.model.Account;
import com.esgi.account.repository.SqlDataAccount;
import com.esgi.team.model.Team;
import com.esgi.team.model.TeamCreation;
import lombok.Builder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.jayway.restassured.RestAssured.authentication;
import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpStatus.*;

/**
 * Created by Kevin DHORNE on 4/13/2016.
 */
@SqlDataAccount
public class TeamControllerRestTest extends AbstractRestTest {

    @Autowired
    TokenProvider tokenProvider;

    @Test
    public void should_should_create_team(){
        AuthenticatedRequestBody<TeamCreation> authenticatedRequestBody =  new AuthenticatedRequestBody<TeamCreation>();

        final TeamCreation teamCreation = TeamCreation.builder().name("clan de").tag("CLN").build();
        authenticatedRequestBody.setBody(teamCreation);


        String token = tokenProvider.getToken(Account.builder().login("login1").password("63f2ede6bd8eeda82e7f9443a9ca582c0254e912b7b1c32f0d51d5b314f3f663").isAdmin(true).build());
        authenticatedRequestBody.setToken(token);

        given()
                .contentType(JSON)
                .body(toJson(authenticatedRequestBody))
                .when()
                .post("/team")
                .then()
                .log().all()
                .statusCode(CREATED.value())
                //.body("tag",is("CLN"))
        ;
    }

    @Test
    public void should_should_not_create_team_because_of_name(){

    }

    @Test
    public void should_should_not_create_team_because_of_tag(){

    }
}
