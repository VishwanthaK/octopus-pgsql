package com.octopus.service.util.querygenerator;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.querydsl.core.types.dsl.BooleanExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;

/**
 * Concrete class which extends the abstract base class {@link AbstractSearchQueryGenerator}.
 * This class contains methods to generate {@link BooleanExpression} search predicates by joining
 * all the comparison and logical operations specified in the RSQL query.
 *
 * @see AbstractSearchQueryGenerator
 * @author visshh7
 */
public class PredicateSearchQueryGenerator extends AbstractSearchQueryGenerator<BooleanExpression> {

    public PredicateSearchQueryGenerator(AbstractPredicateSearchOperator entitySearchOperation) {
        super(entitySearchOperation);
    }

    /**
     * Returns a {@link BooleanExpression} predicate using the {@link AbstractPredicateSearchOperator#entityPath}
     * field.
     * @param comparisonNode {@link ComparisonNode} comparison node.
     * @return {@link BooleanExpression} predicate.
     * @see AbstractSearchQueryGenerator#getComparisonQuery(ComparisonNode)
     */
    @Override
    public BooleanExpression getComparisonQuery(final ComparisonNode comparisonNode) {
        return entitySearchOperation.getComparisonOperation(comparisonNode);
    }

    /**
     * Returns a {@link BooleanExpression} predicate using the {@link AbstractPredicateSearchOperator#entityPath}
     * field.
     * @param logicalNode {@link LogicalNode} logical node.
     * @return {@link BooleanExpression} predicate.
     * @see AbstractSearchQueryGenerator#getLogicalQuery(LogicalNode)
     */
    @Override
    public BooleanExpression getLogicalQuery(final LogicalNode logicalNode) {
        final List<BooleanExpression> predicates = logicalNode
                .getChildren()
                .stream()
                .map(node -> getQuery(node))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        BooleanExpression result = predicates.get(0);

        if (logicalNode.getOperator() == LogicalOperator.AND) {
            for (int i = 1; i < predicates.size(); i++) {
                result = result.and(predicates.get(i));
            }
        } else if (logicalNode.getOperator() == LogicalOperator.OR) {
            for (int i = 1; i < predicates.size(); i++) {
                result = result.or(predicates.get(i));
            }
        }

        return result;
    }
}
