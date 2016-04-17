package com.esgi.tournament.service;

import com.esgi.account.model.Account;
import com.esgi.account.repository.AccountRepository;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andreï on 17/04/2016.
 */
@Service
public class TournamentService {
    private final TournamentRepository tournamentRepository;

    @Autowired
    public TournamentService( TournamentRepository tournamentRepository ) {
        this.tournamentRepository = tournamentRepository;
    }

    public List<Tournament> getTournaments() {
        return tournamentRepository.findAll();
    }

    public boolean updateTournament(Tournament tournament) {
        tournamentRepository.save(tournament);

        return true;
    }

    public Tournament getTournamentById(int id) {
        Tournament tournament;
        try
        {
            tournament = tournamentRepository.findById( id ).get( 0 );
        }
        catch( IndexOutOfBoundsException e )
        {
            return null;
        }

        return tournament;
    }

    public Tournament getTournamentByName(String name) {
        Tournament tournament;
        try
        {
            tournament = tournamentRepository.findByName( name ).get( 0 );
        }
        catch( IndexOutOfBoundsException e )
        {
            return null;
        }

        return tournament;
    }
}
