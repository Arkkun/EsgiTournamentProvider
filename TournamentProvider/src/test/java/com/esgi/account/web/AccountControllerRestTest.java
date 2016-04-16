package com.esgi.account.web;

import com.esgi.AbstractRestTest;
import com.esgi.account.authentication.ConnectionRequestBody;
import com.esgi.account.model.Account;
import com.esgi.account.repository.SqlDataAccount;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static com.jayway.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.http.HttpStatus.*;

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
                .body("$", hasSize(4));
    }



    @Test
    public void should_throw_error_when_accountNotFound(){
        String id = "100";

        final String exception = com.esgi.account.exceptions.AccountNotFoundException.class.getName();
        given()
                .contentType(JSON)
                .when()
                .get("/accounts/" + id)
                .then()
                .log().all()
                .statusCode(BAD_REQUEST.value())
                .body("exception", is(exception))
        ;
    }

    @Test
    public void should_get_account_by_id(){

        /*String id = "1";
        String login = "root";
        Boolean admin = true;
        int idR = 1;
        given()
                .contentType(JSON)
                .when()
                .get("/accounts/" + id)
                .then()
                .log().all()
                .statusCode(OK.value())
                .body("id", is(idR))
                .body("login", is(login))
                .body("admin", is(admin))
        ;*/

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
        ;
    }

    @Test
    public void should_not_create_new_account_when_login_too_small(){
        final String login = "Ka";
        final String password = "myPassword123";
        final Account account = Account.builder().login(login).password(password).build();

        final String exception = com.esgi.account.exceptions.AccountFieldNotValidException.class.getName();
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

    @Test
    public void should_not_authenticate_when_bad_password(){
        final String login = "root";
        final String password = "rooted";
        final ConnectionRequestBody connectionRequestBody = ConnectionRequestBody.builder().login(login).password(password).build();

        final String exception = com.esgi.account.exceptions.AuthenticationNotValidException.class.getName();
        given()
                .contentType(JSON)
                .body(toJson(connectionRequestBody))
                .when()
                .post("/accounts/authenticate")
                .then()
                .log().all()
                .statusCode(FORBIDDEN.value())
                .body("exception", is(exception))
        ;
    }
}
