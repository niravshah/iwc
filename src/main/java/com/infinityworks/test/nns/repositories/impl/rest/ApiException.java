package com.infinityworks.test.nns.repositories.impl.rest;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class ApiException extends RuntimeException {

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }
}
