package com.esgi.team.service;

import com.esgi.account.model.Account;
import com.esgi.team.model.Membership;
import com.esgi.team.model.Team;
import com.esgi.team.repository.MembershipRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Kevin DHORNE on 18/04/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class MembershipServiceTest {
    @InjectMocks
    MembershipService membershipService;
    @Mock
    MembershipRepository membershipRepository;

    @Test
    public void should_call_findAll(){

        membershipService.getMemberships();
        verify(membershipRepository).findAll();

    }

    @Test
    public void should_call_findByTeam(){

        Team team = Team.builder().name("Origen").tag("OG").build();
        membershipService.getMembershipsByTeam(team);
        verify(membershipRepository).findByTeam(team);

    }

    @Test
    public void should_call_findMembershipByTeamAndAccount(){

        Team team = Team.builder().name("Origen").tag("OG").build();
        Account account = Account.builder().login("root").password("password").isAdmin(true).build();
        membershipService.getMembershipByTeamAndAccount(account, team);
        verify(membershipRepository).findMembershipByTeamAndAccount(account, team);

    }
}
