package com.octopus.service.exception;

import java.util.List;

import com.octopus.service.domain.Error;

public class BadRequestexception extends RuntimeException {

    private static final long serialVersionUID = -3222577258061614651L;
    private String message;
    private List<Error> errors;

    public BadRequestexception(String message) {
        super(message);
    }

    public BadRequestexception(String message, List<Error> errors) {
        super(message);
        this.errors = errors;
    }

    public String getMessage() {
        return message;
    }

    public List<Error> getErrors() {
        return errors;
    }
}
