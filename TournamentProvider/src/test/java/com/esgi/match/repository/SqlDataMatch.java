package com.esgi.match.repository;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Created by Andreï on 17/04/2016.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql(
        statements = {
                "insert into TOURNAMENT (tournament_size,team_size) values (4,1)"
                ,"insert into ACCOUNT (login, password, isAdmin) values ('login2', 'f305d3db71d65dbe09cbeaf24a7de7026ad8264b928b542c618a09015774a462', true)"
                ,"insert into TEAM (is_deleted) values (false)"
                ,"insert into TEAM (is_deleted) values (false)"
                ,"insert into TEAM (is_deleted) values (false)"
                ,"insert into TEAM (is_deleted) values (false)"
                ,"insert into MEMBERSHIP (team, account, is_owner, status) values ('1','2',true,'accepted')"
                ,"insert into MATCHE (round,place,tournament_id,team1_id,team2_id) values (0,0,1,1,2)"
                ,"insert into MATCHE (round,place,tournament_id,team1_id,team2_id) values (0,1,1,3,4)"
        },
        executionPhase = BEFORE_TEST_METHOD
)

@Sql(
        statements = {
                "delete from MATCHE"
        },
        executionPhase = AFTER_TEST_METHOD
)

public @interface SqlDataMatch {

}