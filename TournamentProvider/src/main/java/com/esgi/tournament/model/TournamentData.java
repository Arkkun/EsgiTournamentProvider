package com.esgi.tournament.model;

import lombok.*;

/**
 * Created by Andreï on 17/04/2016.
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TournamentData {
    private Tournament tournament;
    private int slotLeft;
}
