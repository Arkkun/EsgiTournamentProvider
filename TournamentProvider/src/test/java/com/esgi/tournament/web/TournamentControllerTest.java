package com.esgi.tournament.web;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.ConnectionRequestBody;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.model.TournamentCreation;
import com.esgi.tournament.repository.SqlDataTournament;
import com.esgi.tournament.service.TournamentService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

/**
 * Created by Thomas on 17/04/2016.
 */

@RunWith(MockitoJUnitRunner.class)
@SpringApplicationConfiguration(classes = TournamentProviderApplication.class)
@SqlDataTournament
public class TournamentControllerTest {
    @InjectMocks
    TournamentController tournamentController;
    @Mock
    TournamentService tournamentService;

    @BeforeMethod
    public void initMocks() { MockitoAnnotations.initMocks(this); }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    private Tournament tournament = new Tournament();
    private ConnectionRequestBody connectionRequestBody = new ConnectionRequestBody();
    private AuthenticatedRequestBody<TournamentCreation> authenticatedRequestBody = new AuthenticatedRequestBody<>();

    @Test
    public void should_call_getTournaments_by_Id() {
        int id = 1;
        tournamentController.getTournamentById(id);

        verify(tournamentController.getTournamentById(id));
    }

    @Test
    public void should_call_getTournaments() {
        tournamentController.getTournaments();

        verify(tournamentController.getTournaments());
    }

    @Test
    public void should_call_updateTournament() {
        authenticatedRequestBody.setName("Updated name");
        authenticatedRequestBody.setDescription("Updated description");

        tournamentController.createTournament(authenticatedRequestBody);

        verify(tournamentService).updateTournament(any());
    }

    @Test
    public void should_call_validation_functions() {
        authenticatedRequestBody.setName("Updated name");
        authenticatedRequestBody.setDescription("Updated description");

        tournamentController.createTournament(authenticatedRequestBody);

        verify(tournamentService).validateName(authenticatedRequestBody.getName());
        verify(tournamentService).validateDescription(authenticatedRequestBody.getDescription());
        verify(tournamentService).validateDate(authenticatedRequestBody.getDate());
        verify(tournamentService).validateTournamentSize(authenticatedRequestBody.getTournamentSize());
        verify(tournamentService).validateTeamSize(authenticatedRequestBody.getTeamSize());
    }

}
