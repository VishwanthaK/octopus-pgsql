package com.octopus.service.util.querygenerator;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.Node;

/**
 * This is the abstract base class for all search query generator classes.
 *
 * <p>It contains methods which generates search query of type {@link T}.
 * {@link Node} parameter specifies whether its a comparison or logical operator.</p>
 *
 * @param <T> Type of the query which is returned by the methods.
 * @author visshh7
 */
public abstract class AbstractSearchQueryGenerator<T> implements QueryGenerator<T> {

    protected EntitySearchOperator<T> entitySearchOperation;

    AbstractSearchQueryGenerator(EntitySearchOperator entitySearchOperation) {
        this.entitySearchOperation = entitySearchOperation;
    }

    /**
     * This method returns a search query of type {@link T} based on whether the {@link Node} parameter
     * is a comparison node ({@link ComparisonNode}) or logical node ({@link LogicalNode}).
     *
     * @param node {@link Node} can be either a {@link ComparisonNode} or {@link LogicalNode}.
     * @return {@link T} search query.
     */
    public T getQuery(final Node node) {
        if (node instanceof LogicalNode) {
            return getLogicalQuery((LogicalNode) node);
        }

        if (node instanceof ComparisonNode) {
            return getComparisonQuery((ComparisonNode) node);
        }

        return null;
    }

    /**
     * This method returns a search query of type {@link T} based on the {@link ComparisonNode}
     * parameter specified.
     *
     * @param comparisonNode {@link ComparisonNode} comparison node.
     * @return {@link T} search query.
     */
    public abstract T getComparisonQuery(ComparisonNode comparisonNode);

    /**
     * This method returns a search query of type {@link T} based on the {@link LogicalNode}
     * parameter specified.
     *
     * @param logicalNode {@link LogicalNode} logical node.
     * @return {@link T} search query.
     */
    public abstract T getLogicalQuery(LogicalNode logicalNode);

}
