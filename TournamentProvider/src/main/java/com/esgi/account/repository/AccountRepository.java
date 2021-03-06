package com.esgi.account.repository;

import com.esgi.account.model.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Andreï on 02/04/2016.
 */

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

    List<Account> findAll();
    List<Account> findByLogin( String login );
    List<Account> findById( int id );
}
