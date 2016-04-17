package com.esgi.team.service;

import com.esgi.team.repository.SqlDataTeam;
import com.esgi.team.repository.TeamRepository;
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
@SqlDataTeam
public class TeamServiceTest {
    @InjectMocks
    TeamService teamService;
    @Mock
    TeamRepository teamRepository;

    @Test
    public void should_call_findAll()
    {
        teamService.getTeams();
        verify(teamRepository).findAll();
    }

    @Test
    public void should_call_findById()
    {
        teamService.getTeamById( 1 );
        verify(teamRepository).findById( 1 );
    }

}

