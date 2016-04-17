package com.esgi.team.service;

import com.esgi.account.model.Account;
import com.esgi.team.model.Membership;
import com.esgi.team.model.Team;
import com.esgi.team.repository.MembershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by user on 16/04/2016.
 */
@Service
public class MembershipService {

    @Autowired
    public MembershipRepository membershipRepository;

    //@Autowired
    //public MembershipService(MembershipRepository membershipRepository){this.membershipRepository = membershipRepository;}

    public List<Membership> getMemberships(){return  membershipRepository.findAll();}

    public List<Membership> getMembershipsByTeam(Team team){
        return membershipRepository.findByTeam(team);
    }

    public List<Membership> getMembershipById(int idJoin){
        return membershipRepository.findById(idJoin);
    }


    public boolean updateMembership(Membership membership){
        membershipRepository.save(membership);
        return true;
    }

    public Membership getMembershipByTeamAndAccount(Account account, Team team){
        Membership membership;

        try{
            membership = membershipRepository.findMembershipByTeamAndAccount(account,team);
        }catch ( IndexOutOfBoundsException e ){
            return null;
        }
        return membership;
    }
}
