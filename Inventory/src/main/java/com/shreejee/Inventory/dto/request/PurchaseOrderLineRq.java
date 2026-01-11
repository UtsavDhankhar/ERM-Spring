package com.shreejee.Inventory.dto.request;

import java.math.BigDecimal;

public record PurchaseOrderLineRq (
        Long materialId,
        BigDecimal orderedQty,
        BigDecimal pricePerUnit
) {}
