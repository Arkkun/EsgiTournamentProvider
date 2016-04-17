package com.esgi.match.model;

import com.esgi.team.model.Team;
import com.esgi.tournament.model.Tournament;
import lombok.*;

import java.util.List;

/**
 * Created by Andreï on 17/04/2016.
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MatchGenerate {
    private Tournament tournament;
    private List<Team> teamList;
}
