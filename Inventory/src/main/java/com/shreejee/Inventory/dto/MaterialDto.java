package com.shreejee.Inventory.dto;

import com.shreejee.Inventory.enums.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class MaterialDto {

    private Long id;
    private String code;
    private String name;
    private String category;
    private UnitOfMeasure unitOfMeasure; // DB enum uom as text
    private String color;
    private Long supplierId; // keep simple for now
}
