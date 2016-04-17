package com.esgi.team.repository;

import com.esgi.account.model.Account;
import com.esgi.team.model.Membership;
import com.esgi.team.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by user on 16/04/2016.
 */
@Repository
public interface MembershipRepository extends JpaRepository<Membership, String> {

    @Query("select m from Membership m where m.team = :team and m.account = :account")
    Membership findMembershipByTeamAndAccount(@Param("account") Account account, @Param("team") Team team);

    List<Membership> findAll();
    List<Membership> findByTeam(Team team);
    List<Membership> findById(int id);
}
