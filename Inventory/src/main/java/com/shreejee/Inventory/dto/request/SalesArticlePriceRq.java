package com.shreejee.Inventory.dto.request;

import java.math.BigDecimal;

public record SalesArticlePriceRq (
        long articleId,
        BigDecimal price
) {
}
