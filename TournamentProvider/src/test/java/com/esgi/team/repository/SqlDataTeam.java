package com.esgi.team.repository;

import org.springframework.test.context.jdbc.Sql;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

/**
 * Created by Kevin DHORNE on 17/04/2016.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Sql(
        statements = {
                "insert into ACCOUNT (login, password, isAdmin) values ('login1', '63f2ede6bd8eeda82e7f9443a9ca582c0254e912b7b1c32f0d51d5b314f3f663', true)"
                ,"insert into ACCOUNT (login, password, isAdmin) values ('login2', 'f305d3db71d65dbe09cbeaf24a7de7026ad8264b928b542c618a09015774a462', false)"
                ,"insert into ACCOUNT (login, password, isAdmin) values ('login3', '1a83aa26a86799ef9ecdca2e7b88e91aab9cc34174d1995ce3c79740fc29e860', false)"
                ,"insert into ACCOUNT (login, password, isAdmin) values ('login4', '1a83aa26a86799ef9aadca2e7b88e91aab9cc34174d1995ce3c79740fc29e860', false)"
                ,"insert into TEAM (name, tag, is_deleted) values ('Game earth 2', 'G2', false)"
                ,"insert into TEAM (name, tag, is_deleted) values ('Origen', 'OG', false)"
                ,"insert into MEMBERSHIP (team, account, is_owner, status) values ('1', '1',true,'accepted' )"
                ,"insert into MEMBERSHIP (team, account, is_owner, status) values ('1', '2',false,'accepted' )"
                ,"insert into MEMBERSHIP (team, account, is_owner, status) values ('1', '3',false,'applied' )"
                ,"insert into MEMBERSHIP (team, account, is_owner, status) values ('2', '1',true,'accepted' )"
                ,"insert into MEMBERSHIP (team, account, is_owner, status) values ('2', '2',false,'applied' )"
        },
        executionPhase = BEFORE_TEST_METHOD
)
public @interface SqlDataTeam {

}
