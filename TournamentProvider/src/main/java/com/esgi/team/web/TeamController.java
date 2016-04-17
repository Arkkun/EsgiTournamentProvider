package com.esgi.team.web;

import com.esgi.account.authentication.*;
import com.esgi.account.model.Account;
import com.esgi.team.exception.NotValidTeamStatusException;
import com.esgi.team.exception.TeamFieldNotValidException;
import com.esgi.team.model.*;
import com.esgi.team.repository.MembershipRepository;
import com.esgi.team.service.MembershipService;
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
    AuthenticationManager authenticationManager;
    @Autowired
    MembershipService membershipService;

    @Autowired
    public TeamController(TeamService teamService){this.teamService = teamService;}

    @RequestMapping(method = POST)
    @ResponseStatus(CREATED)
    public Team createTeam(@RequestBody AuthenticatedRequestBody<TeamCreation> authentication){
        authenticationManager.mustBeValidToken(authentication.getToken());

        TeamCreation teamCreation = (TeamCreation) authentication.getBody();

        if(teamCreation.getName().length() < 3 || teamCreation.getName() == null || teamCreation.getName().length() > 25)
            throw new TeamFieldNotValidException("The team name is not valid! ");

        if(teamCreation.getTag().length() < 2 || teamCreation.getTag() == null || teamCreation.getTag().length() > 5)
            throw new TeamFieldNotValidException("The team tag is not valid! ");

        List<Team> existingTeam = teamService.getTeams();
        for(int i = 0;i < existingTeam.size(); i++){
            if (existingTeam.get(i).getTag().compareTo(teamCreation.getTag()) == 0 )
                throw new TeamFieldNotValidException("The team tag is already used!");
            if (existingTeam.get(i).getName().compareTo(teamCreation.getName()) == 0 )
                throw new TeamFieldNotValidException("The team name is already used! ");
        }

        Team team = Team.builder().name(teamCreation.getName()).tag(teamCreation.getTag()).isDeleted(false).build();

        Account account = authenticationManager.getAccountFromToken(authentication.getToken());

        Membership membership = Membership.builder().team(team).account(account).isOwner(true).status("accepted").build();

        teamService.updateTeam(team);
        membershipService.updateMembership(membership);

        return team;
    }

    @RequestMapping(method = GET, value = "membership/{idTeam}")
    public List<Membership> getMemberships(@PathVariable("idTeam") int idTeam){
        Team team = teamService.getTeamById(idTeam);
        return membershipService.getMembershipsByTeam(team);
    }

    @RequestMapping(method = POST, value = "membership/{idTeam}")
    public PublicMembership applyTeam(@RequestBody Token authentication, @PathVariable("idTeam") int idTeam) {
        authenticationManager.mustBeValidToken(authentication.getToken());
        Team team = teamService.getTeamById(idTeam);
        Account account = authenticationManager.getAccountFromToken(authentication.getToken());

        List<Membership> memberships = membershipService.getMemberships();
        for(int i = 0;i < memberships.size(); i++){
            if (memberships.get(i).getAccount().getId() == account.getId())
                throw new TeamFieldNotValidException("You already part of this team!");
        }

        Membership membership = Membership.builder().account(account).team(team).isOwner(false).status("applied").build();

        membershipService.updateMembership(membership);

        return new PublicMembership(membership);
    }

    @RequestMapping(method = PUT, value = "membership/{idJoin}")
    public Membership changeMembershipStatus(@RequestBody AuthenticatedRequestBody<String> authentication, @PathVariable("idJoin") int idJoin){
        Membership membership = membershipService.getMembershipById(idJoin).get(0);
        authenticationManager.mustBeValidOwnerTeamToken(authentication.getToken(), membership.getTeam().getId());

        if(authentication.getBody().compareTo("accepted") != 0 && authentication.getBody().compareTo("refused") != 0)
            throw new NotValidTeamStatusException();
        membership.setStatus(authentication.getBody());

        membershipService.updateMembership(membership);

        return membership;
    }

    @RequestMapping(method = GET, value = "/{idTeam}")
    public TeamData getInfoTeam(@PathVariable("idTeam") int idTeam){
        Team team = teamService.getTeamById(idTeam);
        TeamData teamData = TeamData.builder().team(team).build();
        return teamData;
    }

    @RequestMapping(method = GET, value = "/search/{keyword}")
    public List<Team> searchTeam(@PathVariable("keyword") String keyword){
        return null;
    }

    @RequestMapping(method = DELETE, value = "/{idTeam}")
    public boolean deleteTeam(@RequestBody Token authentication, @PathVariable("idTeam") int idTeam){

        return false;
    }
}
