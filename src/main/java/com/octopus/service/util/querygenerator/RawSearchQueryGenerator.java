package com.octopus.service.util.querygenerator;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;

/**
 * Concrete class which extends the abstract base class {@link AbstractSearchQueryGenerator}.
 * This class contains methods to generate raw MySQL search query by joining
 * all the comparison and logical operations specified in the RSQL query.
 *
 * @see AbstractSearchQueryGenerator
 * @author visshh7
 */
public class RawSearchQueryGenerator extends AbstractSearchQueryGenerator<String> {

    public RawSearchQueryGenerator(AbstractRawSearchOperator operator) {
        super(operator);
    }

    /**
     * This method returns a raw MySQL comparison operation query based on the {@link ComparisonNode}
     * parameter specified.
     *
     * @param comparisonNode {@link ComparisonNode} comparison node.
     * @return {@link String} raw MySQL query.
     */
    @Override
    public String getComparisonQuery(ComparisonNode comparisonNode) {
        return entitySearchOperation.getComparisonOperation(comparisonNode);
    }

    /**
     * This method returns a raw MySQL logical operation query based on the {@link LogicalNode}
     * parameter specified.
     *
     * @param logicalNode {@link LogicalNode} logical node.
     * @return {@link String} raw MySQL query.
     */
    @Override
    public String getLogicalQuery(LogicalNode logicalNode) {
        final List<String> conditions = logicalNode
                .getChildren()
                .stream()
                .map(node -> getQuery(node))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());

        String result = conditions.get(0);

        if (logicalNode.getOperator() == LogicalOperator.AND) {
            for (int i = 1; i < conditions.size(); i++) {
                result = "(" + result + ") AND (" + conditions.get(i) + ")";
            }
        } else if (logicalNode.getOperator() == LogicalOperator.OR) {
            for (int i = 1; i < conditions.size(); i++) {
                result = "(" + result + ") OR (" + conditions.get(i) + ")";
            }
        }

        return result;
    }
}
