package com.esgi.match.web;

import com.esgi.account.authentication.AuthenticationManager;
import com.esgi.match.Team;
import com.esgi.match.exceptions.MatchNotFoundException;
import com.esgi.match.model.Match;
import com.esgi.match.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

/**
 * Created by Andreï on 16/04/2016.
 */

@RestController
@RequestMapping("/matchs")
public class MatchController {
    private final MatchService matchService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @RequestMapping(value="/{id}", method = GET)
    public Match getMatchById(@PathVariable(value="id") int id )
    {
        Match match = matchService.getMatchById( id );
        if( match == null )
        {
            throw new MatchNotFoundException();
        }

        return match;
    }
}
