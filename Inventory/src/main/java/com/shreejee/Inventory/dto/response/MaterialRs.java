package com.shreejee.Inventory.dto.response;

import com.shreejee.Inventory.enums.UnitOfMeasure;
import com.shreejee.Inventory.projection.MaterialSupplierPriceProjection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

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
    private List<MaterialSupplierPriceRs> materialSupplierPrices; // keep simple for now


    @Builder
    @Getter @Setter
    private static class MaterialSupplierPriceRs {

        private Long id;
        private String supplierName;
        private Long supplierId;
        private BigDecimal pricePerUnit;
    }

    public void generateMaterialSupplierPrices(List<MaterialSupplierPriceProjection> supplierMaterialPrices) {

        this.materialSupplierPrices = supplierMaterialPrices.stream()
                .map(supplierMaterialPrice -> MaterialSupplierPriceRs.builder().build()).toList();
    }
}