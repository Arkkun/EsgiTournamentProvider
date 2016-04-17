package com.esgi.tournament.model;

import com.esgi.match.Team;
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
@Table(name = "JOINTOURNAMENT")
public class JoinTournament {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "tournament_id")
    private Tournament tournament;

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;
}
