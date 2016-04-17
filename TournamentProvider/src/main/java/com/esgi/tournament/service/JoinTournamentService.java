package com.esgi.tournament.service;

import com.esgi.tournament.model.JoinTournament;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.repository.JoinTournamentRepository;
import com.esgi.tournament.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Andreï on 17/04/2016.
 */
@Service
public class JoinTournamentService {
    private final JoinTournamentRepository joinTournamentRepository;

    @Autowired
    public JoinTournamentService( JoinTournamentRepository joinTournamentRepository ) {
        this.joinTournamentRepository = joinTournamentRepository;
    }

    public boolean updateJoinTournament(JoinTournament joinTournament) {
        joinTournamentRepository.save(joinTournament);

        return true;
    }
}
