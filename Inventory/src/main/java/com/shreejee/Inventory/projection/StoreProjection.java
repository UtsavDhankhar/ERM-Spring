package com.shreejee.Inventory.projection;

import java.math.BigDecimal;

public interface StoreProjection {

    Long getId();
    BigDecimal getQuantity();
    Long getMaterialId();
    String getMaterialName();
    String getMaterialColor();
    String getUnitOfMeasure();
    String getSubCategory();
}
