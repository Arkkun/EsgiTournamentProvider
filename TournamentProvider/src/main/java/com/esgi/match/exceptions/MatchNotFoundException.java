package com.esgi.match.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * Created by Andreï on 11/04/2016.
 */
@ResponseStatus(BAD_REQUEST)
public class MatchNotFoundException extends RuntimeException{
}
