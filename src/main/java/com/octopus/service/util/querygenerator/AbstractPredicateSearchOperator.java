package com.octopus.service.util.querygenerator;

import java.time.DateTimeException;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;
import org.joda.time.LocalDateTime;

import com.octopus.service.exception.InvalidSearchOperatorException;
import com.octopus.service.exception.InvalidSearchTermValueException;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import cz.jirutka.rsql.parser.ast.ComparisonNode;

/**
 * This is the abstract base class for all database entity specific search operators.
 *
 * <p>It contains methods which generates {@link BooleanExpression} based on the comparison
 * operator specified. The operator is applied on {@link AbstractPredicateSearchOperator#entityPath}
 * field for generating the {@link BooleanExpression}</p>
 *
 * <p>The static methods specified in this class generates {@link BooleanExpression} based on the
 * path type of the {@link AbstractPredicateSearchOperator#entityPath} field, arguments and the operator.</p>
 *
 * @param <T> Any object which extends {@link EntityPathBase}, like Querydsl Q classes
 * @author visshh7
 */
public abstract class AbstractPredicateSearchOperator<T extends EntityPath> implements EntitySearchOperator<BooleanExpression> {

    protected T entityPath;

    public AbstractPredicateSearchOperator(T entityPath) {
        this.entityPath = entityPath;
    }

    /**
     * This method returns {@link BooleanExpression} based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link BooleanExpression} predicate.
     */
    public abstract BooleanExpression getComparisonOperation(final ComparisonNode comparisonNode);

    /**
     * This static method returns {@link BooleanExpression} based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param path {@link NumberPath} object, which corresponds to any database column of long type.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link BooleanExpression} predicate.
     * @exception InvalidSearchTermValueException For invalid search term value
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final BooleanExpression getLongOperation(final NumberPath<Long> path, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        List<Long> values;

        try {
            values = arguments.stream().map(arg -> Long.valueOf(arg)).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), comparisonNode.getArguments().get(0));
        }

        Long value = values.get(0);

        BooleanExpression predicate = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                predicate = path.eq(value);
                break;
            case GREATER_THAN:
                predicate = path.gt(value);
                break;
            case LESS_THAN:
                predicate = path.lt(value);
                break;
        }

        if (predicate == null) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return predicate;
    }

    /**
     * This static method returns {@link BooleanExpression} based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param path {@link NumberPath} object, which corresponds to any database column of integer type.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link BooleanExpression} predicate.
     * @exception InvalidSearchTermValueException For invalid search term value
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final BooleanExpression getIntegerOperation(final NumberPath<Integer> path, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        List<Integer> values;

        try {
            values = arguments.stream().map(arg -> Integer.valueOf(arg)).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), comparisonNode.getArguments().get(0));
        }

        Integer value = values.get(0);

        BooleanExpression predicate = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                predicate = path.eq(value);
                break;
            case GREATER_THAN:
                predicate = path.gt(value);
                break;
            case LESS_THAN:
                predicate = path.lt(value);
                break;
            case GREATER_THAN_OR_EQUAL:
                predicate = path.goe(value);
                break;
            case LESS_THAN_OR_EQUAL:
                predicate = path.loe(value);
                break;
        }

        if (predicate == null) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return predicate;
    }

    /**
     * This static method returns {@link BooleanExpression} based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param path {@link NumberPath} object, which corresponds to any database column of smallint(Short in entity) type.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link BooleanExpression} predicate.
     * @exception InvalidSearchTermValueException For invalid search term value
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final BooleanExpression getShortOperation(final NumberPath<Short> path, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        List<Short> values;

        try {
            values = arguments.stream().map(arg -> Short.valueOf(arg)).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), comparisonNode.getArguments().get(0));
        }

        Short value = values.get(0);

        BooleanExpression predicate = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                predicate = path.eq(value);
                break;
            case GREATER_THAN:
                predicate = path.gt(value);
                break;
            case LESS_THAN:
                predicate = path.lt(value);
                break;
            case GREATER_THAN_OR_EQUAL:
                predicate = path.goe(value);
                break;
            case LESS_THAN_OR_EQUAL:
                predicate = path.loe(value);
                break;
        }

        if (predicate == null) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return predicate;
    }

    /**
     * This static method returns {@link BooleanExpression} based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param path {@link StringPath} object, which corresponds to any database column of string type.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link BooleanExpression} predicate.
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final BooleanExpression getStringOperation(final StringPath path, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        String arg = arguments.get(0);

        BooleanExpression predicate = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                predicate = path.equalsIgnoreCase(arg);
                break;
            case LIKE:
                predicate = path.containsIgnoreCase(arg);
                break;
        }

        if (predicate == null) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return predicate;
    }

    /**
     * This static method returns {@link BooleanExpression} based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param path {@link BooleanPath} object, which corresponds to any database column of boolean type.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link BooleanExpression} predicate.
     * @exception InvalidSearchTermValueException For invalid search term value, i.e., for values other than
     *              'true', 'on', 'y', 't', 'yes', 'false', 'off', 'n', 'f', 'no' (CASE INSENSITIVE).
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final BooleanExpression getBooleanOperation(final BooleanPath path, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        String arg = arguments.get(0);
        Boolean booleanArg = BooleanUtils.toBooleanObject(arg);

        if (booleanArg == null) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), arg);
        }

        BooleanExpression predicate = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                predicate = path.eq(booleanArg);
                break;
        }

        if (predicate == null) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return predicate;
    }

    /**
     * This static method returns {@link BooleanExpression} based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param path {@link BooleanPath} object, which corresponds to any database column of datetime type.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link BooleanExpression} predicate.
     * @exception InvalidSearchTermValueException For invalid epoch values.
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final BooleanExpression getDateTimeOperation(final DateTimePath<LocalDateTime> path, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        String arg = arguments.get(0);
        LocalDateTime dateTimeArg;

        try {
            final long epochSeconds = Long.valueOf(arg);
            final Instant instant = Instant.ofEpochSecond(epochSeconds);
            dateTimeArg = new LocalDateTime(instant.toEpochMilli());
        } catch (NumberFormatException | DateTimeException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), arg);
        }

        BooleanExpression predicate = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                predicate = path.eq(dateTimeArg);
                break;
            case GREATER_THAN:
                predicate = path.gt(dateTimeArg);
                break;
            case LESS_THAN:
                predicate = path.lt(dateTimeArg);
                break;
            case GREATER_THAN_OR_EQUAL:
                predicate = path.goe(dateTimeArg);
                break;
            case LESS_THAN_OR_EQUAL:
                predicate = path.loe(dateTimeArg);
                break;
        }

        if (predicate == null) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return predicate;
    }

    /**
     * This static method returns {@link BooleanExpression} based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param path {@link StringPath} object, which corresponds to any database column of double type.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link BooleanExpression} predicate.
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final BooleanExpression getDoubleOperation(final NumberPath<Double> path, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        List<Double> values;

        try {
            values = arguments.stream().map(arg -> Double.valueOf(arg)).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), comparisonNode.getArguments().get(0));
        }

        Double value = values.get(0);

        BooleanExpression predicate = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
        case EQUAL:
            predicate = path.eq(value);
            break;
        case GREATER_THAN:
            predicate = path.gt(value);
            break;
        case GREATER_THAN_OR_EQUAL:
            predicate = path.goe(value);
            break;
        case LESS_THAN:
            predicate = path.lt(value);
            break;
        case LESS_THAN_OR_EQUAL:
            predicate = path.loe(value);
            break;
        }


        if (predicate == null) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return predicate;
    }

}
