package com.esgi.account.service;

import com.esgi.account.exceptions.AccountFieldNotValidException;
import com.esgi.account.model.Account;
import com.esgi.account.model.AccountPublic;
import com.esgi.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Account getAccountById(int id) {
        Account account;
        try
        {
            account = accountRepository.findById( id ).get( 0 );
        }
        catch( IndexOutOfBoundsException e )
        {
            return null;
        }

        return account;
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

    public List<AccountPublic> accountListToAccountPublicList( List<Account> accountList )
    {
        List<AccountPublic> accountPublicList = new ArrayList<AccountPublic>();

        for( int i = 0 ; i < accountList.size() ; i++ ){
            accountPublicList.add( new AccountPublic( accountList.get( i ) ) );
        }

        return accountPublicList;
    }

    public void validateLogin(String login ) {
        if( login == null )
            throw new AccountFieldNotValidException("Account's login has to be set");
        if( login.length() < 3 )
            throw new AccountFieldNotValidException("Account's login is too short");
        if( login.length() > 25 )
            throw new AccountFieldNotValidException("Account's login is too long");
    }

    public void validatePassword(String password ) {
        if( password == null )
            throw new AccountFieldNotValidException("Account's password has to be set");
        if( password.length() < 3 )
            throw new AccountFieldNotValidException("Account's password is too short");
        if( password.length() > 25 )
            throw new AccountFieldNotValidException("Account's password is too long");
    }
}
