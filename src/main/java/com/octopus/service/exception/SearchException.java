package com.octopus.service.exception;

/**
 * This is the base class for all search related exception classes
 * in phoenix. It extends {@link RuntimeException}.
 *
 * @author Dheeraj Gopinath
 */
public class SearchException extends RuntimeException {

    private static final long serialVersionUID = -5130337607356154884L;

    public SearchException(String message) {
        super(message);
    }

}
