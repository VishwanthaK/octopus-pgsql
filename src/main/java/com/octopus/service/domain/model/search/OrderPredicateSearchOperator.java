package com.octopus.service.domain.model.search;

import static org.apache.commons.lang3.StringUtils.lowerCase;

import java.util.Objects;

import com.octopus.service.domain.model.QOrderEntity;
import com.octopus.service.exception.InvalidSearchTermException;
import com.octopus.service.util.querygenerator.AbstractPredicateSearchOperator;
import com.querydsl.core.types.dsl.BooleanExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;

public class OrderPredicateSearchOperator extends AbstractPredicateSearchOperator<QOrderEntity> {

    public OrderPredicateSearchOperator() {
        super(QOrderEntity.orderEntity);
    }

    @Override
    public BooleanExpression getComparisonOperation(ComparisonNode comparisonNode) {
        BooleanExpression predicate = null;
        final String selector = lowerCase(comparisonNode.getSelector());

        switch (selector) {
        case "id":
            predicate = getLongOperation(entityPath.id, comparisonNode);
            break;
        case "locality":
            predicate = getStringOperation(entityPath.userAddress.locality, comparisonNode);
            break;
        case "street":
            predicate = getStringOperation(entityPath.userAddress.street, comparisonNode);
            break;
        case "landmark":
            predicate = getStringOperation(entityPath.userAddress.landmark, comparisonNode);
            break;
        case "city":
            predicate = getStringOperation(entityPath.userAddress.city, comparisonNode);
            break;
        case "pincode":
            predicate = getIntegerOperation(entityPath.userAddress.pincode, comparisonNode);
            break;
        case "ordernumber":
            predicate = getStringOperation(entityPath.orderNumber, comparisonNode);
            break;
        case "grandtotal":
            predicate = getDoubleOperation(entityPath.grandTotal, comparisonNode);
            break;
        case "delivered":
            predicate = getBooleanOperation(entityPath.delivered, comparisonNode);
            break;
        case "cancelled":
            predicate = getBooleanOperation(entityPath.cancelled, comparisonNode);
            break;
        case "modified":
            predicate = getBooleanOperation(entityPath.modified, comparisonNode);
            break;
        case "createdon":
            predicate = getDateTimeOperation(entityPath.createdOn, comparisonNode);
            break;
        case "deliveryscheduledon":
            predicate = getDateTimeOperation(entityPath.deliveryScheduledOn, comparisonNode);
            break;
        case "deliveredon":
            predicate = getDateTimeOperation(entityPath.deliveredOn, comparisonNode);
            break;
        }

        if (Objects.isNull(predicate)) {
            throw new InvalidSearchTermException(comparisonNode.getSelector());
        }

        return predicate;
    }
}
