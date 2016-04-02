package com.esgi.account.web;

import com.esgi.AbstractRestTest;
import com.esgi.account.model.Account;
import com.esgi.account.repository.SqlDataAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

/**
 * Created by Andreï on 02/04/2016.
 */
@SqlDataAccount
public class AccountControllerRestTest extends AbstractRestTest {
    @Test
    public void should_get_list_of_3_accounts() {
        when()
                .get("/accounts")
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("$", hasSize(3));
    }

    @Test
    public void should_create_new_account(){
        final String login = "Kavlo-24";
        final String password = "myPassword123";
        final Account account = Account.builder().login(login).password(password).build();

        given()
                .contentType(JSON)
                .body(toJson(account))
                .when()
                .post("/accounts")
                .then()
                .log().all()
                .statusCode(CREATED.value())
                .body("login", is(login))
                .body("password", is(password))
        ;
    }

    @Test
    public void should_not_create_new_account_when_login_too_small(){
        final String login = "Ka";
        final String password = "myPassword123";
        final Account account = Account.builder().login(login).password(password).build();

        final String exception = com.esgi.account.exceptions.AccountLoginTooShortException.class.getName();
        given()
                .contentType(JSON)
                .body(toJson(account))
                .when()
                .post("/accounts")
                .then()
                .log().all()
                .statusCode(BAD_REQUEST.value())
                .body("exception", is(exception))
        ;
    }
}
