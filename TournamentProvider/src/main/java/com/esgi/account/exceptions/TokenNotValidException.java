package com.esgi.account.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by Andreï on 11/04/2016.
 */
@ResponseStatus(BAD_REQUEST)
public class TokenNotValidException extends RuntimeException{
}
