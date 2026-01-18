package com.shreejee.Inventory.dto.request;

import java.math.BigDecimal;

public record BomMaterialCreateRq (
        BigDecimal quantityPerUnit,
        BigDecimal wastagePercent,
        String remarks,
        Long materialId
){}
