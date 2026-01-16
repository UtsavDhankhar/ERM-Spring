package com.shreejee.Inventory.dto.response;

import com.shreejee.Inventory.entity.MaterialCategory;
import com.shreejee.Inventory.entity.SupplierMaterialPrice;
import com.shreejee.Inventory.enums.UnitOfMeasure;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class MaterialRs {

    private Long id;
    private String code;
    private String name;
    private String subCategory;
    private MaterialCategoryRs materialCategoryRs;
    private UnitOfMeasure unitOfMeasure; // DB enum uom as text
    private String color;
    private Set<MaterialSupplierPriceRs> materialSupplierPrices; // keep simple for now


    @Builder
    @Getter @Setter
    private class MaterialSupplierPriceRs {

        private Long id;
        private String supplierName;
        private Long supplierId;
        private BigDecimal pricePerUnit;
    }

    public MaterialSupplierPriceRs generateMaterialSupplierPrice(SupplierMaterialPrice supplierMaterialPrice) {

        return MaterialSupplierPriceRs.builder().build();
    }
}