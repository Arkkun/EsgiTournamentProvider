package com.esgi.match.service;

import com.esgi.account.repository.AccountRepository;
import com.esgi.match.model.MatchPublic;
import com.esgi.team.model.Team;
import com.esgi.match.model.Match;
import com.esgi.match.model.MatchGenerate;
import com.esgi.match.repository.MatchRepository;
import com.esgi.tournament.model.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andreï on 16/04/2016.
 */

@Service
@Component
public class MatchService {
    private final MatchRepository matchRepository;

    @Autowired
    public MatchService( MatchRepository matchRepository ) {
        this.matchRepository = matchRepository;
    }

    public List<Match> getMatchs() {
        return matchRepository.findAll();
    }


    public Match getMatchById(int id) {
        Match match;
        try
        {
            match = matchRepository.findById( id ).get( 0 );
        }
        catch( IndexOutOfBoundsException e )
        {
            return null;
        }

        return match;
    }

    public List<Match> getMatchListByTournament(Tournament tournament) {
        List<Match> matchList;

        matchList = matchRepository.findByTournament( tournament );

        return matchList;
    }


    public Match getMatchByTournamentAndRoundAndPlace(Tournament tournament, int round, int place) {
        Match match;
        try
        {
            match = matchRepository.findByTournamentAndRoundAndPlace(tournament, round, place).get( 0 );
        }
        catch( IndexOutOfBoundsException e )
        {
            return null;
        }

        return match;
    }


    public boolean updateMatch(Match match) {
        matchRepository.save(match);
        return true;
    }

    public boolean updateMatchList(List<Match> matchList) {
        for( int i = 0; i < matchList.size(); i++ )
        {
            matchRepository.save(matchList.get(i));
        }
        return true;
    }

    public List<MatchPublic> matchListToMatchPublicList(List<Match> accountList )
    {
        List<MatchPublic> matchPublicList = new ArrayList<MatchPublic>();

        for( int i = 0 ; i < accountList.size() ; i++ ){
            matchPublicList.add( new MatchPublic( accountList.get( i ) ) );
        }

        return matchPublicList;
    }

    public boolean generateTournamentFirstRound(MatchGenerate matchGenerate) {

        List<Team> teamList = matchGenerate.getTeamList();
        Tournament tournament = matchGenerate.getTournament();

        List<Match> matchList = new ArrayList<Match>();

        int size = teamList.size();
        if((size & -size) == size)//check if power of 2
        {
            if( size > 1 )
            {
                for( int i = 0; i < (size/2); i++ )
                {
                    matchList.add(
                            Match.builder()
                                    .round( 0 )
                                    .place( i )
                                    .team1( teamList.get(2*i) )
                                    .team2( teamList.get(2*i + 1) )
                                    .score1( null )
                                    .score2( null )
                                    .tournament( tournament )
                                    .build()
                    );
                }
                this.updateMatchList( matchList );
                return true;
            }
        }
        return false;
    }

    public Match generateNextMatch(Match match) {

        if( match.getScore1() == null || match.getScore2() == null )
            return null;
        Team winnerMatch = match.getScore1() > match.getScore2() ? match.getTeam1() : match.getTeam2();
        int matchPlace = match.getPlace();
        int matchBrotherPlace;
        if( matchPlace % 2 == 0 )
            matchBrotherPlace = matchPlace + 1;
        else
            matchBrotherPlace = matchPlace -1;

        Match matchBrother = this.getMatchByTournamentAndRoundAndPlace( match.getTournament(), match.getRound(), matchBrotherPlace );

        if( matchBrother.getScore1() == null || matchBrother.getScore2() == null )
            return null;
        Team winnerMatchBrother = matchBrother.getScore1() > matchBrother.getScore2() ? matchBrother.getTeam1() : matchBrother.getTeam2();

        Match nextMatch = Match.builder()
                .round( match.getRound() )
                .place( matchPlace / 2 )
                .team1( winnerMatch )
                .team2( winnerMatchBrother )
                .score1( null )
                .score2( null )
                .tournament( match.getTournament() )
                .build();


        return nextMatch;
    }
}
