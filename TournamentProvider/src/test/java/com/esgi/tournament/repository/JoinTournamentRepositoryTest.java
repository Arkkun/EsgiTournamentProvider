package com.esgi.tournament.repository;

import com.esgi.TournamentProviderApplication;
import com.esgi.tournament.model.JoinTournament;
import com.esgi.tournament.model.Tournament;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by Thomas on 17/04/2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TournamentProviderApplication.class)
public class JoinTournamentRepositoryTest {

    @Autowired
    JoinTournamentRepository joinTournamentRepository;


    @Test
    public void should_find_by_tournament_and_team() {
        /*
        String tournamentName = "Tournament 1";
        int idTeam = 1;

        assertThat(joinTournamentRepository.findByTournamentAndTeam());
        */
    }

    @Test
    public void should_find_by_tournament() {


    }
}
