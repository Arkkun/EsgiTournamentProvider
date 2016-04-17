package com.esgi.tournament.repository;

import com.esgi.tournament.model.JoinTournament;
import com.esgi.tournament.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Andreï on 17/04/2016.
 */

@Repository
public interface JoinTournamentRepository extends JpaRepository<JoinTournament, String> {

}
