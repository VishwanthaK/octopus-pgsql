package com.octopus.service.validator;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.octopus.service.domain.Error;
import com.octopus.service.dto.OrderItemDTO;

@Component
public class OrderValidator {

    public List<Error> validateOrder(List<OrderItemDTO> orders) {
        List<Error> errors = new ArrayList<>();

        return errors;
    }
}
