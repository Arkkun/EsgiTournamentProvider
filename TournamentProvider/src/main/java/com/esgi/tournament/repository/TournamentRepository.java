package com.esgi.tournament.repository;

import com.esgi.account.model.Account;
import com.esgi.tournament.model.Tournament;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andreï on 02/04/2016.
 */

@Repository
public interface TournamentRepository extends JpaRepository<Tournament, String>{

    List<Tournament> findAll();
    List<Tournament> findById( int id );
    List<Tournament> findByName( String name );
}
