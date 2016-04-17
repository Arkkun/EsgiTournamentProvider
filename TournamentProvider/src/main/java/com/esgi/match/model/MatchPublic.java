package com.esgi.match.model;

import com.esgi.account.model.Account;
import lombok.*;

/**
 * Created by Andreï on 17/04/2016.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class MatchPublic {

    private int id;
    private int round;
    private int place;
    private String team1;
    private String team2;
    private int score1;
    private int score2;

    public MatchPublic( Match match )
    {
        this.id = match.getId();
        this.round = match.getRound();
        this.place = match.getPlace();
        this.team1 = match.getTeam1().getName();
        this.team2 = match.getTeam2().getName();
        this.score1 = match.getScore1() == null ? 0 : match.getScore1();
        this.score2 = match.getScore2() == null ? 0 : match.getScore2();
    }
}