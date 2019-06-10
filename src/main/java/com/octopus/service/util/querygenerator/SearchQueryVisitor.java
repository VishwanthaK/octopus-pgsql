package com.octopus.service.util.querygenerator;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.NoArgRSQLVisitorAdapter;
import cz.jirutka.rsql.parser.ast.OrNode;

/**
 * This class visits each {@link cz.jirutka.rsql.parser.ast.Node} of the parsed
 * RSQL query specified in the url search param, and then generate a search query.
 *
 * @param <T> Type of the query which is to be returned. For e.g. QueryDSL predicate, JPA criteria, raw query etc.
 * @author visshh7
 */
public class SearchQueryVisitor<T, U extends AbstractSearchQueryGenerator<T>> extends NoArgRSQLVisitorAdapter<T> {

    private U queryGenerator;

    public SearchQueryVisitor(U queryGenerator) {
        this.queryGenerator = queryGenerator;
    }

    /**
     * Generates query for logical AND operation.
     * @param andNode {@link AndNode} specifies LHS and RHS for AND operation
     * @return query of type {@link T}
     */
    @Override
    public T visit(AndNode andNode) {
        return queryGenerator.getQuery(andNode);
    }

    /**
     * Generates query for logical OR operation.
     * @param orNode {@link OrNode} specifies LHS and RHS for OR operation
     * @return query of type {@link T}
     */
    @Override
    public T visit(OrNode orNode) {
        return queryGenerator.getQuery(orNode);
    }

    /**
     * Generates query for any comparison operation.
     * @param comparisonNode {@link ComparisonNode} specifies selector, operator and arguments for any comparison operation
     * @return query of type {@link T}
     */
    @Override
    public T visit(ComparisonNode comparisonNode) {
        return queryGenerator.getQuery(comparisonNode);
    }

}