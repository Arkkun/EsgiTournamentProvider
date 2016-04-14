package com.esgi.team.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by 212416808 on 4/12/2016.
 */
@ResponseStatus(BAD_REQUEST)
public class TeamNullNameException extends NullPointerException{
    public TeamNullNameException(String message){super(message);}
}
