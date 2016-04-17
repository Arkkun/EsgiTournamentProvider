package com.esgi.team.web;

import com.esgi.AbstractRestTest;
import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.Token;
import com.esgi.account.authentication.TokenProvider;
import com.esgi.account.model.Account;
import com.esgi.account.model.AccountPublic;
import com.esgi.account.repository.SqlDataAccount;
import com.esgi.team.model.PublicMembership;
import com.esgi.team.model.Team;
import com.esgi.team.model.TeamCreation;
import com.esgi.team.repository.SqlDataTeam;
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
@SqlDataTeam
public class TeamControllerRestTest extends AbstractRestTest {

    @Autowired
    TokenProvider tokenProvider;

    @Test
    public void should_create_team(){
        AuthenticatedRequestBody<TeamCreation> authenticatedRequestBody =  new AuthenticatedRequestBody<TeamCreation>();

        final TeamCreation teamCreation = TeamCreation.builder().name("Origenn").tag("OGR").build();
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
                .body("tag",is("OGR"))
        ;
    }

    @Test
    public void should_not_create_team_because_of_name(){
        AuthenticatedRequestBody<TeamCreation> authenticatedRequestBody =  new AuthenticatedRequestBody<TeamCreation>();

        final TeamCreation teamCreation = TeamCreation.builder().name("Or").tag("OG").build();
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
                .statusCode(BAD_REQUEST.value())
        ;
    }

    @Test
    public void should_not_create_team_because_of_tag(){
        AuthenticatedRequestBody<TeamCreation> authenticatedRequestBody =  new AuthenticatedRequestBody<TeamCreation>();

        final TeamCreation teamCreation = TeamCreation.builder().name("Origen").tag("O").build();
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
                .statusCode(BAD_REQUEST.value())
        ;
    }

    @Test
    public void should_get_memberships_from_team(){

        given()
                .contentType(JSON)
                .when()
                .get("/team/membership/1")
                .then()
                .log().all()
                .body("[0].status",is("accepted"))
        ;
    }

    @Test
    public void should_apply_to_a_team(){
        final String token = tokenProvider.getToken(Account.builder().login("login4").password("1a83aa26a86799ef9aadca2e7b88e91aab9cc34174d1995ce3c79740fc29e860").isAdmin(true).build());

        given()
                .contentType(JSON)
                .body(toJson(token))
                .when()
                .post("/team/membership/1")
                .then()
                .log().all()
                .body("status",is("applied"))
        ;
    }

    @Test
    public void should_accept_a_membership(){
        final String token = tokenProvider.getToken(Account.builder().login("root").password("cca3f3e49836d44d6b5fc66de2bcd2e99769a9018d410d0f6297c001881fc7d4").isAdmin(true).build());
        AuthenticatedRequestBody<String> authenticatedRequestBody =  new AuthenticatedRequestBody<>();
        authenticatedRequestBody.setBody("accepted");
        authenticatedRequestBody.setToken(token);

        given()
                .contentType(JSON)
                .body(toJson(authenticatedRequestBody))
                .when()
                .put("/team/membership/4")
                .then()
                .log().all()
                .body("status",is("accepted"))
        ;
    }

    @Test
    public void should_delete_a_team(){
        final String token = tokenProvider.getToken(Account.builder().login("root").password("cca3f3e49836d44d6b5fc66de2bcd2e99769a9018d410d0f6297c001881fc7d4").isAdmin(true).build());

        given()
                .contentType(JSON)
                .body(toJson(token))
                .when()
                .delete("/team/1")
                .then()
                .log().all()
                .body(is("true"))
        ;
    }

    @Test
    public void should_show_list_apply_for_team_id_1(){
        final String token = tokenProvider.getToken(Account.builder().login("root").password("cca3f3e49836d44d6b5fc66de2bcd2e99769a9018d410d0f6297c001881fc7d4").isAdmin(true).build());

        given()
                .contentType(JSON)
                .body(toJson(token))
                .when()
                .put("team/membership/list/1")
                .then()
                .log().all()
                .body("[0].status",is("applied"))
        ;
    }

}
