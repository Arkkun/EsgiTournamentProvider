package com.esgi.account.service;

import com.esgi.account.model.Account;
import com.esgi.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Andreï on 02/04/2016.
 */

//kvlnote utiliser @transactional ?
@Service
public class AccountService {
    private final AccountRepository accountRepository;

    ShaPasswordEncoder encoder;
    private static final int ShaStrength = 256;//Todo variable globale ?
    private static final String secretKey = "SpringSecretKeyESGI";

    @Autowired
    public AccountService( AccountRepository accountRepository ) {
        this.accountRepository = accountRepository;
        encoder = new ShaPasswordEncoder( ShaStrength );
    }

    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }

    public boolean updateAccount(Account account) {
        account.setPassword( encoder.encodePassword( account.getPassword() , secretKey) );
        accountRepository.save(account);

        return true;
    }

    public Account getAccountByLogin(String login) {
        Account account;
        try
        {
            account = accountRepository.findByLogin( login ).get( 0 );
        }
        catch( IndexOutOfBoundsException e )
        {
            return null;
        }

        return account;
    }

    public Account getAccountByLoginAndPassword(String login, String password ) {
        Account account;
        String hashedPassword;

        account = getAccountByLogin( login );
        if( account == null ){
            return null;
        }

        hashedPassword = encoder.encodePassword( password , secretKey);
        if( !hashedPassword.equals( account.getPassword() ) ) {
            return null;
        }

        return account;
    }
}
