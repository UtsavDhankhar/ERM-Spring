package com.shreejee.Inventory.dto.request;

import java.time.LocalDate;
import java.util.List;

public record SalesOrderRq (
        Long brandId,
        String orderNo,
        LocalDate orderDate,
        String remarks,
        List<SalesOrderLineRq> salesOrderLineList
){}
