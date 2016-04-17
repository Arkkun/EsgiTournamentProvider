package com.esgi.tournament.service;

import com.esgi.account.exceptions.AccountFieldNotValidException;
import com.esgi.account.model.Account;
import com.esgi.account.repository.AccountRepository;
import com.esgi.tournament.exceptions.TournamentFieldNotValidException;
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

    public void validateName(String name ) {
        if( name == null )
            throw new TournamentFieldNotValidException("Tournament's name has to be set");
        if( name.length() < 3 )
            throw new TournamentFieldNotValidException("Tournament's name is too short");
        if( name.length() > 50 )
            throw new TournamentFieldNotValidException("Tournament's name is too long");
    }

    public void validateDescription(String description ) {
        if( description == null )
            throw new TournamentFieldNotValidException("Tournament's description has to be set");
        if( description.length() < 3 )
            throw new TournamentFieldNotValidException("Tournament's description is too short");
        if( description.length() > 250 )
            throw new TournamentFieldNotValidException("Tournament's description is too long");
    }

    public void validateDate(String date ) {
        if( date == null )
            throw new TournamentFieldNotValidException("Tournament's date has to be set");
    }

    public void validateTournamentSize(int size ) {
        if((size & -size) == size)//check if power of 2
        {
            if( size > 1 ){
                return;
            }
        }
            throw new TournamentFieldNotValidException("Tournament's size has to be a power of 2");
    }

    public void validateTeamSize(int size ) {
        if( size <= 0)
            throw new TournamentFieldNotValidException("Tournament's team's size has to be greater than 0");

    }
}
