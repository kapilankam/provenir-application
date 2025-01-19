package com.test.provenir.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class RoboBobServiceException extends RuntimeException {
    public RoboBobServiceException(String message) {
        super(message);
    }

    public RoboBobServiceException(String message, Throwable e) {
        super(message, e);
    }
}
