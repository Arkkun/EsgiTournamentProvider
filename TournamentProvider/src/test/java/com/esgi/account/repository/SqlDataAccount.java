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
                "insert into ACCOUNT (login, password, isAdmin) values ('login1', '63f2ede6bd8eeda82e7f9443a9ca582c0254e912b7b1c32f0d51d5b314f3f663', true)"
                ,"insert into ACCOUNT (login, password, isAdmin) values ('login2', 'f305d3db71d65dbe09cbeaf24a7de7026ad8264b928b542c618a09015774a462', true)"
                ,"insert into ACCOUNT (login, password, isAdmin) values ('login3', '1a83aa26a86799ef9ecdca2e7b88e91aab9cc34174d1995ce3c79740fc29e860', true)"
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
