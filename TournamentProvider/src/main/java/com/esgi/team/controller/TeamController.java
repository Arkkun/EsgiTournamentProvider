package com.esgi.team.controller;

import com.esgi.team.model.Team;
import com.esgi.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

/**
 * Created by Kevin DHORNE on 4/5/2016.
 */
public class TeamController {

    private final TeamService teamService;

    @Autowired
    public TeamController(TeamService teamService){this.teamService = teamService;}

    @RequestMapping(method = GET)
    public List<Team> getTeams(){
        return teamService.getTeams();
    }


    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Team createTeaam(@RequestBody Team team){

        return null;
    }

    //@ResponseStatus()
    public boolean joinTeam(){
        return false;
    }

    public boolean inviteAccount(){
        return false;
    }
}
