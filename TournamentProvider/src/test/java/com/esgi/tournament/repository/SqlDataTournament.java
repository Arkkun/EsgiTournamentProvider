package com.esgi.tournament.repository;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Created by Thomas on 17/04/2016.
 */


@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql(
        statements = {
                "insert into TOURNAMENT (tournament_name, description, tournament_date, tournament_size, team_size) values ('Tournament 1', 'The first tournament', '01/01/1901', 16, 5)"
                ,"insert into TOURNAMENT (tournament_name, description, tournament_date, tournament_size, team_size) values ('Tournament 2', 'The second tournament', '02/02/1902', 32, 5)"
                ,"insert into TOURNAMENT (tournament_name, description, tournament_date, tournament_size, team_size) values ('Tournament 3', 'The third tournament', '03/03/1903', 64, 5)"
        },
        executionPhase = BEFORE_TEST_METHOD
)
@Sql(
        statements = {
                "delete from Tournament"
        },
        executionPhase = AFTER_TEST_METHOD
)
public @interface SqlDataTournament {

}
