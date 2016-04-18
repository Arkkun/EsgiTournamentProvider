package com.esgi.tournament.web;

import com.esgi.TournamentProviderApplication;
import com.esgi.account.authentication.AuthenticatedRequestBody;
import com.esgi.account.authentication.ConnectionRequestBody;
import com.esgi.tournament.model.Tournament;
import com.esgi.tournament.model.TournamentCreation;
import com.esgi.tournament.repository.SqlDataTournament;
import com.esgi.tournament.repository.TournamentRepository;
import com.esgi.tournament.service.TournamentService;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
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
    private AuthenticatedRequestBody<TournamentCreation> authenticatedRequestBody = new AuthenticatedRequestBody<TournamentCreation>();

    /*
    @Test
    public void should_call_getTournaments_by_Id() {
        Tournament tournament = tournamentRepository.findAll().get(0);
        int id = 1;
        tournamentController.getTournamentById(id);

        verify(tournamentService.getTournamentById(id));
    }*/

    @Test
    public void should_call_getTournaments() {
        tournamentController.getTournaments();

        verify(tournamentService).getTournaments();
    }

    /*
    @Test
    public void should_call_updateTournament() {
        TournamentCreation tournamentCreation = TournamentCreation.builder().name("test").description("testdesc").date("06/04/09").teamSize(2).tournamentSize(4).build();
        authenticatedRequestBody.setBody(tournamentCreation);

        tournamentController.createTournament(authenticatedRequestBody);

        verify(tournamentService).updateTournament(any());
    }
    */

    /*
    @Test
    public void should_call_validation_functions() {
        authenticatedRequestBody.getBody().setName("Updated name");
        authenticatedRequestBody.getBody().setDescription("Updated description");

        tournamentController.createTournament(authenticatedRequestBody);

        verify(tournamentService).validateName(authenticatedRequestBody.getBody().getName());
        verify(tournamentService).validateDescription(authenticatedRequestBody.getBody().getDescription());
        verify(tournamentService).validateDate(authenticatedRequestBody.getBody().getDate());
        verify(tournamentService).validateTournamentSize(authenticatedRequestBody.getBody().getTournamentSize());
        verify(tournamentService).validateTeamSize(authenticatedRequestBody.getBody().getTeamSize());
    }
    */

}
