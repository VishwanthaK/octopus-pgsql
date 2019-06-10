package com.octopus.service.util.querygenerator;

import cz.jirutka.rsql.parser.ast.ComparisonNode;

/**
 * Interface for all search query operator classes.
 *
 * @param <T> Type of the query which is returned by the methods.
 * @author visshh7
 */
public interface EntitySearchOperator<T> {

    T getComparisonOperation(ComparisonNode comparisonNode);

}
