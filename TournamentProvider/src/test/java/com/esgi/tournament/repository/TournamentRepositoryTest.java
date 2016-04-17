package com.esgi.tournament.repository;

import com.esgi.TournamentProviderApplication;
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
@SqlDataTournament
public class TournamentRepositoryTest {

    @Autowired
    TournamentRepository tournamentRepository;

    @Test
    public void should_find_a_tournament_by_id() {
        int id = 1;
        assertThat(tournamentRepository.findById(id), hasSize(1));
    }

    @Test
    public void should_not_find_a_tournament_by_id() {
        int notAValidId = 999999999;
        assertThat(tournamentRepository.findById(notAValidId), hasSize(0));
    }

    @Test
    public void should_find_a_tournament_by_name() {
        String tournamentName = "Tournament 1";
        assertThat(tournamentRepository.findByName(tournamentName), hasSize(1));
    }

    @Test
    public void should_not_find_a_tournament_by_name() {
        String notATournamentName = "notATournamentName";
        assertThat(tournamentRepository.findByName(notATournamentName), hasSize(0));
    }


}
