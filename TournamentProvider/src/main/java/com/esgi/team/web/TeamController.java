package com.esgi.team.web;

import com.esgi.team.TeamCreation;
import com.esgi.team.TeamData;
import com.esgi.team.exception.TeamNullNameException;
import com.esgi.team.model.Membership;
import com.esgi.team.model.Team;
import com.esgi.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

/**
 * Created by Kevin DHORNE on 4/5/2016.
 */
@RestController
@RequestMapping("/team")
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService){this.teamService = teamService;}

    /*
    @RequestMapping(method = POST, value = "/")
    @ResponseStatus(CREATED)
    public Team createTeam(@RequestBody RequestBody<TeamCreation>){
        if(team.getName().length() == 0)
            throw new TeamNullNameException("Team's name can't be null!");

        return team;
    }*/

    @RequestMapping(method = DELETE, value = "/{idTeam}")
    public boolean deleteTeam(/*@RequestBody RequestBody<>*/ @PathVariable("idTeam") int idTeam){
        return false;
    }

    @RequestMapping(method = GET, value = "/{idTeam}")
    public TeamData getInfoTeam(@PathVariable("idTeam") int idTeam){
        return null;
    }

    @RequestMapping(method = GET, value = "/search/{keyword}")
    public List<Team> searchTeam(@PathVariable("keyword") String keyword){
        return null;
    }

    @RequestMapping(method = POST, value = "membership/{idTeam}")
    public Membership applyTeam(@PathVariable("idTeam") int idTeam){
        return null;
    }

    @RequestMapping(method = PUT, value = "membership/{idTeam}")
    public List<Membership> getMemberships(@PathVariable("idTeam") int idTeam){
        return null;
    }

    @RequestMapping(method = PUT, value = "membership/{idJoin}")
    public Membership changeMembershipStatus(@PathVariable("idJoin") int idTeam){
        return null;
    }
}
