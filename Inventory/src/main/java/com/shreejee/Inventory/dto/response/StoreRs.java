package com.shreejee.Inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
public class StoreRs {

    private Long id;
    private BigDecimal quantity;
    private Long materialId;
    private String materialName;
    private String materialColor;
    private String unitOfMeasure;
    private String subCategory;

}
