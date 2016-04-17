package com.esgi.team.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by user on 17/04/2016.
 */
@ResponseStatus(BAD_REQUEST)
public class NotValidTeamStatusException extends RuntimeException {
}
