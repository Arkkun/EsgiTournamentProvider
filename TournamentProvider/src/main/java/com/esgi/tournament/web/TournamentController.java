package com.esgi.tournament.web;

import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.AuthenticationManager;
import com.esgi.account.authentication.ConnectionRequestBody;
import com.esgi.account.authentication.Token;
import com.esgi.account.exceptions.AccountFieldNotValidException;
import com.esgi.match.Team;
import com.esgi.tournament.exceptions.TournamentNotFoundException;
import com.esgi.account.model.Account;
import com.esgi.account.model.AccountPublic;
import com.esgi.account.service.AccountService;
import com.esgi.tournament.model.JoinTournament;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.model.TournamentCreation;
import com.esgi.tournament.service.JoinTournamentService;
import com.esgi.tournament.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Andreï on 17/04/2016.
 */
@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    private final JoinTournamentService joinTournamentService;
    @Autowired
    AuthenticationManager authenticationManager;
    //@Autowired
    //JoinTournamentService joinTournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService, JoinTournamentService joinTournamentService) {
        this.tournamentService = tournamentService;
        this.joinTournamentService = joinTournamentService;
    }

    @RequestMapping(method = GET)
    public List<Tournament> getTournaments( ) {
        List<Tournament> tournamentList = tournamentService.getTournaments();
        return tournamentList;
    }

    @RequestMapping(value="/{id}", method = GET)
    public Tournament getTournamentById(@PathVariable(value="id") int id )
    {
        Tournament tournament = tournamentService.getTournamentById( id );
        if( tournament == null )
        {
            throw new TournamentNotFoundException();
        }

        return tournament;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Tournament createTournament(@RequestBody AuthenticatedRequestBody<TournamentCreation> tournamentCreation )
    {
        authenticationManager.mustBeValidAdminToken( tournamentCreation.getToken() );
        Tournament tournament;

        if( tournamentService.getTournamentByName( tournamentCreation.getBody().getName() ) != null )
        {
            throw new AccountFieldNotValidException( "Tournament's name already in use" );
        }

        tournament = new Tournament( tournamentCreation.getBody() );
        tournamentService.updateTournament( tournament );
        return tournament;
    }

    @RequestMapping(value="/{idTournament}/join/{idTeam}", method = POST)
    public Tournament joinTournament( @RequestBody Token token
            ,@PathVariable(value="idTournament") int idTournament
            , @PathVariable(value="idTeam") int idTeam)
    {
        //authenticationManager.mustBeValidOwnerToken( token.getToken() );
        Tournament tournament = tournamentService.getTournamentById( idTournament );
        if( tournament == null )
        {
            throw new TournamentNotFoundException();
        }
        /*
        Team team = teamService.getTeamById( idTeam );
        if( team == null )
        {
            throw new TeamNotFoundException();
        }
        */
        Team team = Team.builder().id( idTeam ).build();

        JoinTournament joinTournament = JoinTournament.builder().tournament( tournament ).team( team ).build();

        joinTournamentService.updateJoinTournament( joinTournament );
        return tournament;
    }
}
