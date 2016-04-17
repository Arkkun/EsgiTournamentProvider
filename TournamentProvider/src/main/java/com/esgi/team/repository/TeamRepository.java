package com.esgi.team.repository;

import com.esgi.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Kevin DHORNE on 4/5/2016.
 */
@Repository
public interface TeamRepository extends JpaRepository<Team, String> {

    List<Team> findAll();
    List<Team> findById( int id );
}
