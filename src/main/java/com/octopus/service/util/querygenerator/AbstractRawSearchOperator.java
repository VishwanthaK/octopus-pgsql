package com.octopus.service.util.querygenerator;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.StringUtils.wrap;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.BooleanUtils;

import com.octopus.service.exception.InvalidSearchOperatorException;
import com.octopus.service.exception.InvalidSearchTermValueException;
import cz.jirutka.rsql.parser.ast.ComparisonNode;

/**
 * This is the abstract base class for all database table specific search operators.
 * The methods will return raw MySQL query.
 *
 * <p>It contains methods which generates raw MySQL query based on the comparison
 * operator specified. The table name is passed as a constructor parameter and is
 * stored in {@link AbstractRawSearchOperator#tableAlias} field.</p>
 *
 * <p>The static methods specified in this class generates raw MySQL query based on the
 * column name, operator and the column values.</p>
 *
 * @author visshh7
 */
public abstract class AbstractRawSearchOperator implements EntitySearchOperator<String> {

    protected String tableAlias;

    public AbstractRawSearchOperator(String tableAlias) {
        this.tableAlias = tableAlias;
    }

    /**
     * This method returns raw MySQL query based on the selector (column name), operator and arguments
     * specified in the {@link ComparisonNode} object.
     *
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link String} raw MySQL query.
     */
    public abstract String getComparisonOperation(ComparisonNode comparisonNode);

    /**
     * This static method returns raw MySQL query based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object. The column should be of long type.
     *
     * @param field database column name.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link String} raw MySQL query.
     * @exception InvalidSearchTermValueException For invalid search term value
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final String getLongOperation(final String field, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        List<Long> values;

        try {
            values = arguments.stream().map(arg -> Long.valueOf(arg)).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), comparisonNode.getArguments().get(0));
        }

        Long value = values.get(0);

        String rawSql = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                rawSql = field + " = " + value;
                break;
            case GREATER_THAN:
                rawSql = field + " > " + value;
                break;
            case LESS_THAN:
                rawSql = field + " < " + value;
                break;
        }

        if (isBlank(rawSql)) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return rawSql;
    }

    /**
     * This static method returns raw MySQL query based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object. The column should be of integer type.
     *
     * @param field database column name.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link String} raw MySQL query.
     * @exception InvalidSearchTermValueException For invalid search term value
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final String getIntegerOperation(final String field, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        List<Integer> values;

        try {
            values = arguments.stream().map(arg -> Integer.valueOf(arg)).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), comparisonNode.getArguments().get(0));
        }

        Integer value = values.get(0);

        String rawSql = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                rawSql = field + " = " + value;
                break;
            case GREATER_THAN:
                rawSql = field + " > " + value;
                break;
            case LESS_THAN:
                rawSql = field + " < " + value;
                break;
            case GREATER_THAN_OR_EQUAL:
                rawSql = field + " >= " + value;
                break;
            case LESS_THAN_OR_EQUAL:
                rawSql = field + " <= " + value;
                break;
        }

        if (isBlank(rawSql)) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return rawSql;
    }

    /**
     * This static method returns raw MySQL query based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object. The column should be of smallint(Short in entity) type.
     *
     * @param field database column name.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link String} raw MySQL query.
     * @exception InvalidSearchTermValueException For invalid search term value
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final String getShortOperation(final String field, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        List<Short> values;

        try {
            values = arguments.stream().map(arg -> Short.valueOf(arg)).collect(Collectors.toList());
        } catch (NumberFormatException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), comparisonNode.getArguments().get(0));
        }

        Short value = values.get(0);

        String rawSql = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                rawSql = field + " = " + value;
                break;
            case GREATER_THAN:
                rawSql = field + " > " + value;
                break;
            case LESS_THAN:
                rawSql = field + " < " + value;
                break;
            case GREATER_THAN_OR_EQUAL:
                rawSql = field + " >= " + value;
                break;
            case LESS_THAN_OR_EQUAL:
                rawSql = field + " <= " + value;
                break;
        }

        if (isBlank(rawSql)) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return rawSql;
    }

    /**
     * This static method returns raw MySQL query based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object. The column should be of string type.
     *
     * @param field database column name.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link String} raw MySQL query.
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final String getStringOperation(final String field, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        String arg = arguments.get(0);

        String rawSql = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                rawSql = field + " = " + wrap(arg, "'");
                break;
            case LIKE:
                rawSql = field + " LIKE " + wrap(wrap(arg, "%"), "'");
                break;
        }

        if (isBlank(rawSql)) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return rawSql;
    }

    /**
     * This static method returns raw MySQL query based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object. The column should be of boolean type.
     *
     * @param field database column name.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link String} raw MySQL query.
     * @exception InvalidSearchTermValueException For invalid search term value
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final String getBooleanOperation(final String field, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        String arg = arguments.get(0);
        Boolean booleanArg = BooleanUtils.toBooleanObject(arg);

        if (booleanArg == null) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), arg);
        }

        String rawSql = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                rawSql = field + " = " + booleanArg.toString();
                break;
        }

        if (isBlank(rawSql)) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return rawSql;
    }

    /**
     * This static method returns raw MySQL query based on the selector, operator and arguments
     * specified in the {@link ComparisonNode} object. The column should be of datetime type.
     *
     * @param field database column name.
     * @param comparisonNode {@link ComparisonNode} object which contains selector, operator and arguments.
     * @return {@link String} raw MySQL query.
     * @exception InvalidSearchTermValueException For invalid search term value
     * @exception InvalidSearchOperatorException For invalid operators
     */
    protected static final String getDateTimeOperation(final String field, final ComparisonNode comparisonNode) {
        List<String> arguments = comparisonNode.getArguments();
        String arg = arguments.get(0);
        Long epochSeconds;

        try {
            epochSeconds = Long.valueOf(arg);
        } catch (NumberFormatException e) {
            throw new InvalidSearchTermValueException(comparisonNode.getSelector(), arg);
        }

        String rawSql = null;

        switch (SearchOperator.getSimpleOperator(comparisonNode.getOperator())) {
            case EQUAL:
                rawSql = field + " = " + "FROM_UNIXTIME(" + epochSeconds + ")";
                break;
            case GREATER_THAN:
                rawSql = field + " > " + "FROM_UNIXTIME(" + epochSeconds + ")";
                break;
            case LESS_THAN:
                rawSql = field + " < " + "FROM_UNIXTIME(" + epochSeconds + ")";
                break;
            case GREATER_THAN_OR_EQUAL:
                rawSql = field + " >= " + "FROM_UNIXTIME(" + epochSeconds + ")";
                break;
            case LESS_THAN_OR_EQUAL:
                rawSql = field + " <= " + "FROM_UNIXTIME(" + epochSeconds + ")";
                break;
        }

        if (isBlank(rawSql)) {
            throw new InvalidSearchOperatorException(comparisonNode.getSelector(), comparisonNode.getOperator().getSymbol());
        }

        return rawSql;
    }

}
