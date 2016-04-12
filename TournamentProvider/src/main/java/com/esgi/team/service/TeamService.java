package com.esgi.team.service;

import com.esgi.team.model.Team;
import com.esgi.team.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kevin DHORNE on 4/5/2016.
 */
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Autowired
    public TeamService(TeamRepository teamRepository){this.teamRepository = teamRepository;}

    public List<Team> getTeams(){return teamRepository.findAll();}

    public boolean updateTeam(Team team){
        teamRepository.save(team);
        return true;
    }

    public boolean addTeam(Team team){
        return false;
    }
}
