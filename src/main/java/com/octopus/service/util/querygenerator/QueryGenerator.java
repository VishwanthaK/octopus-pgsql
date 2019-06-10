package com.octopus.service.util.querygenerator;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * Interface for all search query generator classes.
 *
 * @param <T> Type of the query which is returned by the methods.
 * @author visshh7
 */
public interface QueryGenerator<T> {

    T getQuery(Node node);

    T getComparisonQuery(ComparisonNode comparisonNode);

    T getLogicalQuery(LogicalNode logicalNode);

}
