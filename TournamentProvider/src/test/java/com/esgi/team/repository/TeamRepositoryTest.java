package com.esgi.team.repository;

import com.esgi.TournamentProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by Kevin DHORNE on 17/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TournamentProviderApplication.class)
@SqlDataTeam
public class TeamRepositoryTest {
    @Autowired
    TeamRepository teamRepository;

    @Test
    public void should_find_2_teams(){
        assertThat(teamRepository.findAll(), hasSize(2));
    }

    @Test
    public void should_find_an_account_by_id() {
        int id = 1;
        assertThat(teamRepository.findById( id ), hasSize(1) );
    }

    @Test
    public void should_not_find_an_account_by_id() {
        int id = 100;
        assertThat(teamRepository.findById( id ), hasSize(0) );
    }
}
