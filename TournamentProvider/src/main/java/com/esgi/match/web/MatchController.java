package com.esgi.match.web;

import com.esgi.account.authentication.AuthenticationManager;
import com.esgi.match.model.MatchData;
import com.esgi.match.model.MatchPublic;
import com.esgi.team.model.Team;
import com.esgi.match.exceptions.MatchNotFoundException;
import com.esgi.match.model.Match;
import com.esgi.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
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

    /*
    @RequestMapping(value="/{id}/result", method = PUT)
    public MatchData getMatchById(@PathVariable(value="score") Integer score )
    {

        return new MatchData( matchPublic );
    }
    */
}
