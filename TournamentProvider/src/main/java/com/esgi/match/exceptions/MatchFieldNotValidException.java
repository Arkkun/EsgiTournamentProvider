package com.esgi.match.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Andreï on 17/04/2016.
 */
@ResponseStatus(BAD_REQUEST)
public class MatchFieldNotValidException extends RuntimeException{
    public MatchFieldNotValidException(String message )
    {
        super( message );
    }
}