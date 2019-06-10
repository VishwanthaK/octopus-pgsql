package com.octopus.service.util.querygenerator;

/**
 * Factory class to create {@link SearchQueryVisitor} classes based on the
 * query generator class supplied, which extends {@link AbstractSearchQueryGenerator}.
 */
public class SearchQueryVisitorFactory {

    public <T, U extends AbstractSearchQueryGenerator<T>> SearchQueryVisitor<T, U> create(AbstractSearchQueryGenerator<T> searchQueryGenerator) {
        return new SearchQueryVisitor(searchQueryGenerator);
    }

}
