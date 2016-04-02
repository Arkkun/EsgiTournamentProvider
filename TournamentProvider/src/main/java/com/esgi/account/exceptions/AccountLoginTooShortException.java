package com.esgi.account.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Andreï on 02/04/2016.
 */
@ResponseStatus(BAD_REQUEST)
public class AccountLoginTooShortException extends RuntimeException{
    public AccountLoginTooShortException(String message )
    {
        super( message );
    }
}
