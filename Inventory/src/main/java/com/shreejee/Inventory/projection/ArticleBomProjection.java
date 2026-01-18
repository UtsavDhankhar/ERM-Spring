package com.shreejee.Inventory.projection;

import java.math.BigDecimal;

public interface ArticleBomProjection {

    Long getId();
    Long getArticleId();
    Long getArticleColorId();
    Long getMaterialId();
    String getMaterialName();
    String getMaterialColor();
    BigDecimal getQuantityPerUnit();
    BigDecimal getWastagePercent();
    String getUnitOfMeasure();
    String Remarks();
}
