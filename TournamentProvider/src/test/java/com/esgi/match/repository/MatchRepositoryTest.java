package com.esgi.match.repository;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.repository.AccountRepository;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.repository.TournamentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

/**
 * Created by Andreï on 17/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TournamentProviderApplication.class)
@SqlDataMatch
public class MatchRepositoryTest {
    @Autowired
    MatchRepository matchRepository;
    @Autowired
    TournamentRepository tournamentRepository;

    @Test
    public void should_find_2_matchs() {
        assertThat(matchRepository.findAll(), hasSize(2));
    }

    @Test
    public void should_find_1_match_by_id() {
        assertThat(matchRepository.findById(3), hasSize(1));
    }

    @Test
    public void should_find_0_match_when_bad_id() {
        assertThat(matchRepository.findById(1337), hasSize(0));
    }

    @Test
    public void should_find_2_match_by_tournament() {
        Tournament tournament = tournamentRepository.findById(1).get(0);
        assertThat(matchRepository.findByTournament( tournament ), hasSize(2));
    }
}
