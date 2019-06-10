package com.octopus.service.util.querygenerator;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;

/**
 * Search operators that can be used.
 *
 * @author visshh7
 */
public enum SearchOperator {
    EQUAL(RSQLOperators.EQUAL),
    NOT_EQUAL(RSQLOperators.NOT_EQUAL),
    GREATER_THAN(RSQLOperators.GREATER_THAN),
    GREATER_THAN_OR_EQUAL(RSQLOperators.GREATER_THAN_OR_EQUAL),
    LESS_THAN(RSQLOperators.LESS_THAN),
    LESS_THAN_OR_EQUAL(RSQLOperators.LESS_THAN_OR_EQUAL),
    IN(RSQLOperators.IN),
    NOT_IN(RSQLOperators.NOT_IN),
    LIKE(new ComparisonOperator("=like=", false));

    private ComparisonOperator operator;

    SearchOperator(final ComparisonOperator operator) {
        this.operator = operator;
    }

    public static SearchOperator getSimpleOperator(final ComparisonOperator operator) {
        return Arrays
                .stream(values())
                .filter(operation -> operation.getOperator() == operator)
                .findAny().orElse(null);
    }

    public ComparisonOperator getOperator() {
        return operator;
    }

    public static Set<ComparisonOperator> getAllOperators() {
        return Arrays
                .stream(values())
                .map(searchOperator -> searchOperator.getOperator())
                .collect(Collectors.toSet());
    }
}