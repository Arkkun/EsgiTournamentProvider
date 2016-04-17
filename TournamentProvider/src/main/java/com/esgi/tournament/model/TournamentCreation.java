package com.esgi.tournament.model;

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

public class TournamentCreation {
    private String name;
    private String description;
    private String date;
    private int tournamentSize;
    private int teamSize;
}
