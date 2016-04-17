package com.esgi.tournament.repository;

import com.esgi.team.model.Team;
import com.esgi.tournament.model.JoinTournament;
import com.esgi.tournament.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andreï on 17/04/2016.
 */

@Repository
public interface JoinTournamentRepository extends JpaRepository<JoinTournament, String> {
    @Query("select jt from JoinTournament jt where jt.tournament = :tournament and jt.team = :team")
    List<JoinTournament> findByTournamentAndTeam(@Param("tournament") Tournament tournament, @Param("team") Team team );

    List<JoinTournament> findByTournament(Tournament tournament);

}
