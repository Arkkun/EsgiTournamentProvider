package com.esgi.account.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * Created by Andreï on 11/04/2016.
 */
@ResponseStatus(FORBIDDEN)
public class MustBeAuthenticatedException extends RuntimeException{
}
