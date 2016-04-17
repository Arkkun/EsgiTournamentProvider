package com.esgi.tournament.service;

import com.esgi.team.model.Membership;
import com.esgi.team.model.Team;
import com.esgi.team.service.MembershipService;
import com.esgi.tournament.exceptions.TournamentCantJoinException;
import com.esgi.tournament.model.JoinTournament;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.repository.JoinTournamentRepository;
import com.esgi.tournament.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andreï on 17/04/2016.
 */
@Service
public class JoinTournamentService {
    private final JoinTournamentRepository joinTournamentRepository;
    @Autowired
    MembershipService membershipService;

    @Autowired
    public JoinTournamentService( JoinTournamentRepository joinTournamentRepository ) {
        this.joinTournamentRepository = joinTournamentRepository;
    }

    public boolean updateJoinTournament(JoinTournament joinTournament) {
        joinTournamentRepository.save(joinTournament);

        return true;
    }

    public JoinTournament getJoinTournamentByTournamentAndTeam(Tournament tournament, Team team) {

        JoinTournament joinTournament;
        try
        {
            joinTournament = joinTournamentRepository.findByTournamentAndTeam(tournament, team).get(0);
        }
        catch( IndexOutOfBoundsException e )
        {
            return null;
        }

        return joinTournament;
    }

    public List<JoinTournament> getJoinTournamentsByTournament(Tournament tournament ) {

        List<JoinTournament> joinTournamentList;
        joinTournamentList = joinTournamentRepository.findByTournament( tournament );

        return joinTournamentList;
    }

    public void validateJoinTournament(Tournament tournament, Team team ) {

        JoinTournament joinTournament = this.getJoinTournamentByTournamentAndTeam( tournament, team );
        if( joinTournament != null )
        {
            throw new TournamentCantJoinException("Tournament already joined");
        }

        int numberTournamentJoint = this.getJoinTournamentsByTournament( tournament ).size();
        if( numberTournamentJoint >= tournament.getTournamentSize() )
        {
            throw new TournamentCantJoinException("Tournament is full");
        }

        int numberTeamMembership = membershipService.getMembershipsByTeam( team ).size();
        if( numberTeamMembership < tournament.getTeamSize() )
        {
            throw new TournamentCantJoinException("Team has not enough players");
        }
    }
}
