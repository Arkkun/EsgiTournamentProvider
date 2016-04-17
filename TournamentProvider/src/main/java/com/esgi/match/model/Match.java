package com.esgi.match.model;

import com.esgi.match.Team;
import com.esgi.tournament.model.Tournament;
import lombok.*;

import javax.persistence.*;

/**
 * Created by Andreï on 16/04/2016.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "MATCHE")
public class Match {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "dateMatch")
    private String date;

    @Column(name = "round")
    private int round;

    @Column(name = "place")
    private int place;

    @Column(name = "score1")
    private Integer score1;

    @Column(name = "score2")
    private Integer score2;

    @ManyToOne
    @JoinColumn(name = "team1_id")
    private Team team1;

    @ManyToOne
    @JoinColumn(name = "team2_id")
    private Team team2;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;
}
