package com.esgi.account.repository;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Created by Andreï on 02/04/2016.
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql(
        statements = {
                "insert into ACCOUNT (login, password, isAdmin) values ('login1', 'pass1', true)"
                ,"insert into ACCOUNT (login, password, isAdmin) values ('login2', 'pass2', true)"
                ,"insert into ACCOUNT (login, password, isAdmin) values ('login3', 'pass3', true)"
        },
        executionPhase = BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "delete from Account"
        },
        executionPhase = AFTER_TEST_METHOD
)
public @interface SqlDataAccount {

}
