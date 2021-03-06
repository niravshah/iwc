package com.infinityworks.test.nns.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class NoAuthoritiesFoundException extends RuntimeException {

    public NoAuthoritiesFoundException(String message) {
        super(message);
    }
}
