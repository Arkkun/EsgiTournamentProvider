package com.esgi.tournament.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Andreï on 17/04/2016.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "TOURNAMENT")
public class Tournament {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "tournament_name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "tournament_date")
    private String date;

    @Column(name = "tournament_size")
    private int tournamentSize;

    @Column(name = "team_size")
    private int teamSize;

    public Tournament( TournamentCreation tournamentCreation )
    {
        this.name = tournamentCreation.getName();
        this.description = tournamentCreation.getDescription();
        this.date = tournamentCreation.getDate();
        this.tournamentSize = tournamentCreation.getTournamentSize();
        this.teamSize = tournamentCreation.getTeamSize();
    }
}