package com.shreejee.Inventory.projection;

import java.math.BigDecimal;

public interface MaterialSupplierPriceProjection {

    Long getId();
    Long getSupplierId();
    String getSupplierName();
    BigDecimal getPricePerUnit();

}
