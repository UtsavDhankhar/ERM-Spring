package com.shreejee.Inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class PurchaseOrderLineRs {

    Long id;
    Long materialId;
    String materialCode;
    String materialName;
    BigDecimal orderedQty;
    BigDecimal receivedQty;
    BigDecimal pricePerUnit;
}
