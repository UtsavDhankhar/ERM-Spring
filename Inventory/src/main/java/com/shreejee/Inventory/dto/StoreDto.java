package com.shreejee.Inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class StoreDto {

    private Long id;

    private MaterialDto materialDto;

    private BigDecimal quantity;

}
