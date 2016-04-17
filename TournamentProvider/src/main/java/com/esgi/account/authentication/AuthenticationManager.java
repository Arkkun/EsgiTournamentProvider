package com.esgi.account.authentication;

import com.esgi.account.exceptions.AuthenticationNotValidException;
import com.esgi.account.exceptions.MustBeAuthenticatedAsAdminException;
import com.esgi.account.exceptions.MustBeAuthenticatedException;
import com.esgi.account.exceptions.MustBeMembershipAsOwnerException;
import com.esgi.account.model.Account;
import com.esgi.account.service.AccountService;
import com.esgi.team.model.Membership;
import com.esgi.team.model.Team;
import com.esgi.team.service.MembershipService;
import com.esgi.team.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Andreï on 10/04/2016.
 */
@Component
public class AuthenticationManager {
    @Autowired
    AccountService accountService;
    @Autowired
    TeamService teamService;
    @Autowired
    MembershipService membershipService;
    @Autowired
    TokenProvider tokenProvider;

    /*public AuthenticationManager(AccountService accountService ){
        this.accountService = accountService;
        this.tokenProvider = new TokenProvider( this.accountService );
    }*/

    /*public AuthenticationManager( ){
        this.tokenProvider = new TokenProvider( this.accountService );
    }*/

    public String getTokenByAuthentication( ConnectionRequestBody connectionRequestBody)
    {
        Account account = getAccountByAuthentication(connectionRequestBody);
        if ( account == null ) {
            throw new AuthenticationNotValidException();
        }

        return getTokenByAccount( account );
    }

    public String getTokenByAccount( Account account)
    {
        return tokenProvider.getToken( account );
    }

    public Account getAccountByAuthentication( ConnectionRequestBody connectionRequestBody)
    {
        return accountService.getAccountByLoginAndPassword( connectionRequestBody.getLogin(), connectionRequestBody.getPassword() );
    }

    public Account getAccountFromToken( String token )
    {
        return tokenProvider.getAccountFromToken( token );
    }

    public void mustBeValidToken( String token )
    {
        Account account = getAccountFromToken( token  );
        if( account == null  )
        {
            throw new MustBeAuthenticatedException();
        }
    }

    public void mustBeValidOwnerTeamToken(String token, int idTeam)
    {
        this.mustBeValidToken(token);

        Account account = this.getAccountFromToken(token);
        Team team = teamService.getTeamById(idTeam);

        Membership membership = membershipService.getMembershipByTeamAndAccount(account,team);

        if( membership == null || membership.isOwner() == false  )
        {
            throw new MustBeMembershipAsOwnerException();
        }
    }

    public void mustBeValidAdminToken( String token )
    {
        Account account = getAccountFromToken( token  );
        if( account == null || account.isAdmin() == false  )
        {
            throw new MustBeAuthenticatedAsAdminException();
        }
    }
}
