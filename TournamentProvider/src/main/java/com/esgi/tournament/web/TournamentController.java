package com.esgi.tournament.web;

import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.AuthenticationManager;
import com.esgi.account.authentication.ConnectionRequestBody;
import com.esgi.account.authentication.Token;
import com.esgi.account.exceptions.AccountFieldNotValidException;
import com.esgi.match.model.MatchGenerate;
import com.esgi.match.service.MatchService;
import com.esgi.team.exception.TeamNotFoundException;
import com.esgi.team.model.Team;
import com.esgi.team.service.TeamService;
import com.esgi.tournament.exceptions.TournamentCantJoinException;
import com.esgi.tournament.exceptions.TournamentNotFoundException;
import com.esgi.account.model.Account;
import com.esgi.account.model.AccountPublic;
import com.esgi.account.service.AccountService;
import com.esgi.tournament.model.JoinTournament;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.model.TournamentCreation;
import com.esgi.tournament.model.TournamentData;
import com.esgi.tournament.service.JoinTournamentService;
import com.esgi.tournament.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by Andreï on 17/04/2016.
 */
@RestController
@RequestMapping("/tournament")
public class TournamentController {
    private final TournamentService tournamentService;
    private final JoinTournamentService joinTournamentService;
    private final TeamService teamService;
    private final MatchService matchService;
    @Autowired
    AuthenticationManager authenticationManager;
    //@Autowired
    //JoinTournamentService joinTournamentService;

    @Autowired
    public TournamentController(TournamentService tournamentService
            , JoinTournamentService joinTournamentService
            , MatchService matchService
            , TeamService teamService) {
        this.tournamentService = tournamentService;
        this.joinTournamentService = joinTournamentService;
        this.teamService = teamService;
        this.matchService = matchService;
    }

    @RequestMapping(method = GET)
    public List<Tournament> getTournaments( ) {
        List<Tournament> tournamentList = tournamentService.getTournaments();
        return tournamentList;
    }

    @RequestMapping(value="/{id}", method = GET)
    public TournamentData getTournamentById(@PathVariable(value="id") int id )
    {
        Tournament tournament = tournamentService.getTournamentById( id );
        if( tournament == null )
        {
            throw new TournamentNotFoundException();
        }
        TournamentData tournamentData = new TournamentData( tournament );

        return tournamentData;
    }

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Tournament createTournament(@RequestBody AuthenticatedRequestBody<TournamentCreation> tournamentCreation )
    {
        authenticationManager.mustBeValidAdminToken( tournamentCreation.getToken() );

        tournamentService.validateName( tournamentCreation.getBody().getName() );
        tournamentService.validateDescription( tournamentCreation.getBody().getDescription() );
        tournamentService.validateDate( tournamentCreation.getBody().getDate() );
        tournamentService.validateTournamentSize( tournamentCreation.getBody().getTournamentSize() );
        tournamentService.validateTeamSize( tournamentCreation.getBody().getTeamSize() );

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
        authenticationManager.mustBeValidOwnerTeamToken( token.getToken(), idTeam );
        Tournament tournament = tournamentService.getTournamentById( idTournament );
        if( tournament == null )
        {
            throw new TournamentNotFoundException();
        }

        Team team = teamService.getTeamById( idTeam );
        if( team == null )
        {
            throw new TeamNotFoundException();
        }

        joinTournamentService.validateJoinTournament( tournament, team );

        JoinTournament joinTournament = JoinTournament.builder().tournament( tournament ).team( team ).build();

        joinTournamentService.updateJoinTournament( joinTournament );
        return tournament;
    }

    @RequestMapping(value="/launch/{idTournament}", method = PUT)
    public Tournament launchTournament( @RequestBody Token token
            ,@PathVariable(value="idTournament") int idTournament )
    {
        authenticationManager.mustBeValidAdminToken( token.getToken() );

        Tournament tournament = tournamentService.getTournamentById( idTournament );
        if( tournament == null )
        {
            throw new TournamentNotFoundException();
        }

        List<JoinTournament> joinTournamentList = joinTournamentService.getJoinTournamentsByTournament( tournament );
        List<Team> teamList = new ArrayList<Team>();
        for( int i = 0 ; i < joinTournamentList.size() ; i++ )
        {
            teamList.add( joinTournamentList.get( i ).getTeam() );
        }
        matchService.generateTournamentFirstRound( new MatchGenerate(tournament, teamList) );
        return tournament;
    }
}
