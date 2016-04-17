package com.esgi.team.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Kevin DHORNE on 16/04/2016.
 */
@ResponseStatus(BAD_REQUEST)
public class TeamFieldNotValidException extends RuntimeException {
    public TeamFieldNotValidException(String message )
    {
        super( message );
    }
}
