package com.shreejee.Inventory.dto.request;

import java.math.BigDecimal;

public record StoreRq(
        Long id,
        Long materialId,
        BigDecimal quantity
) {}
