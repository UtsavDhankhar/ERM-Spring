package com.shreejee.Inventory.utils;

import com.shreejee.Inventory.dto.MaterialDto;
import com.shreejee.Inventory.entity.Material;

public class DtoConvertors {

    public static MaterialDto generateMaterialDto(Material material) {

        return MaterialDto.builder()
                .color(material.getColor())
                .code(material.getCode())
                .id(material.getId())
                .category(material.getCategory())
                .build();
    }

    public static Material generateMaterialFromDto(MaterialDto materialDto) {

        return Material.builder()
                .id(materialDto.getId())
                .build();
    }
}
