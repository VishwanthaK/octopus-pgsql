package com.octopus.service.exception;

/**
 * This is a subclass of {@link SearchException}, which is thrown when
 * the operator for a search term is invalid.
 *
 * @author Dheeraj Gopinath
 */
public class InvalidSearchOperatorException extends SearchException {

    private String term;
    private String operator;

    public InvalidSearchOperatorException(String term, String operator) {
        super(String.format("Invalid operator for the search term \"%s\" : %s", term, operator));
        this.term = term;
        this.operator = operator;
    }

    /**
     * Get the search term
     * @return search term
     */
    public String getTerm() {
        return term;
    }

    /**
     * Get the invalid operator
     * @return invalid operator
     */
    public String getOperator() {
        return operator;
    }

}
