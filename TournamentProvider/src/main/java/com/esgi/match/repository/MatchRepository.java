package com.esgi.match.repository;

import com.esgi.account.model.Account;
import com.esgi.match.model.Match;
import com.esgi.tournament.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andreï on 16/04/2016.
 */
@Repository
public interface MatchRepository extends JpaRepository<Match, String> {

    List<Match> findByTournament(  Tournament tournament  );
    List<Match> findById(  int id );
    List<Match> findAll();

    @Query("select m from Match m where m.tournament = :tournament and m.round = :round and m.place = :place")
    List<Match> findByTournamentAndRoundAndPlace( @Param("tournament") Tournament tournament, @Param("round") int round, @Param("place") int place );
}
