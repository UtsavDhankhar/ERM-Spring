package com.shreejee.Inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class MaterialCategoryDto {

    private Long id;
    private String name;
    private String category;
    private String specification;
    private String description; // keep simple for now
}