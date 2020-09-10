package ru.yastrebova.thebestrest.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "Already voted today")
public class AlreadyVotedException extends RuntimeException{
    public AlreadyVotedException(String message) {
        super(message);
    }
}
