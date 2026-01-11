package com.shreejee.Inventory.dto.request;

import java.math.BigDecimal;
import java.time.LocalDate;

public record SalesOrderLineRq (
        Long articleId,
        Long articleColorId,
        String size,
        Integer quantityOrdered,
        LocalDate targetDispatchDate,
        BigDecimal price
){ }
