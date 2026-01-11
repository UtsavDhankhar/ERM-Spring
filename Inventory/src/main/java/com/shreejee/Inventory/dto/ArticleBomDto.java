package com.shreejee.Inventory.dto;

import com.shreejee.Inventory.entity.Material;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class ArticleBomDto {

    private Long id;
    private Long articleId;
    private Long articleColorId;
    private MaterialDto material;
    private BigDecimal quantityPerUnit;
    private BigDecimal wastagePercent;
    private String remarks;
}
