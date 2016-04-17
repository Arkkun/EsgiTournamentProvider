package com.esgi.account.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.FORBIDDEN;

/**
 * Created by user on 17/04/2016.
 */
@ResponseStatus(FORBIDDEN)
public class MustBeMembershipAsOwnerException extends RuntimeException {
}
