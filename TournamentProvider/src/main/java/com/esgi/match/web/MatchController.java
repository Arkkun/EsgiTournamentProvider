package com.esgi.match.web;

import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.AuthenticationManager;
import com.esgi.match.exceptions.MatchFieldNotValidException;
import com.esgi.match.model.MatchData;
import com.esgi.match.model.MatchPublic;
import com.esgi.team.exception.TeamNotFoundException;
import com.esgi.team.model.Team;
import com.esgi.match.exceptions.MatchNotFoundException;
import com.esgi.match.model.Match;
import com.esgi.match.service.MatchService;
import com.esgi.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

/**
 * Created by Andreï on 16/04/2016.
 */

@RestController
@RequestMapping("/match")
public class MatchController {
    private final MatchService matchService;
    private final TeamService teamService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public MatchController(MatchService matchService, TeamService teamService ) {
        this.matchService = matchService;
        this.teamService = teamService;
    }

    @RequestMapping(method = GET)
    public List<MatchPublic> getMatchs( ) {
        List<MatchPublic> matchList = matchService.matchListToMatchPublicList( matchService.getMatchs() );
        return matchList;
    }

    @RequestMapping(value="/{id}", method = GET)
    public MatchData getMatchById(@PathVariable(value="id") int id )
    {
        Match match = matchService.getMatchById( id );
        if( match == null )
        {
            throw new MatchNotFoundException();
        }

        MatchPublic matchPublic = new MatchPublic( match );

        return new MatchData( matchPublic );
    }


    @RequestMapping(value="/{idMatch}/result/{idTeam}", method = PUT)
    public MatchPublic setMatchResult(@RequestBody AuthenticatedRequestBody<Integer> score
                                    ,@PathVariable(value="idMatch")int idMatch
                                    , @PathVariable(value="idTeam") int idTeam)
    {
        Match match = matchService.getMatchById( idMatch );
        if( match == null )
        {
            throw new MatchNotFoundException();
        }

        Team team = teamService.getTeamById( idTeam );
        if( team == null )
        {
            throw new TeamNotFoundException();
        }
        if( score.getBody() == null )
        {
            throw new MatchFieldNotValidException("score has to be set");
        }
        authenticationManager.mustBeValidOwnerTeamToken( score.getToken(), idTeam );

        matchService.setMatchScore( match, team, score.getBody() );
        matchService.generateNextMatch( match );

        MatchPublic matchPublic = new MatchPublic( match );
        return matchPublic;
    }

}
