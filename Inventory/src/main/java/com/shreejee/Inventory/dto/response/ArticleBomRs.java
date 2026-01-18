package com.shreejee.Inventory.dto.response;

import com.shreejee.Inventory.projection.ArticleBomProjection;
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
public class ArticleBomRs {

        private Long articleId;
        private Long articleColorId;
        private List<BomMaterialRs> bomMaterialRsList;


        @Builder
        @NoArgsConstructor @AllArgsConstructor
        @Getter @Setter
        private static class BomMaterialRs {

            private Long materialId;
            private String materialName;
            private String materialColor;
            private BigDecimal quantityPerUnit;
            private BigDecimal wastagePercent;
            private String remarks;
        }

    public void setBomMaterialList(List<ArticleBomProjection> articleBomProjections){

            this.bomMaterialRsList = articleBomProjections.stream()
                    .map(articleBomProjection -> BomMaterialRs.builder()
                            .materialId(articleBomProjection.getMaterialId())
                            .materialName(articleBomProjection.getMaterialName())
                            .materialColor(articleBomProjection.getMaterialColor())
                            .quantityPerUnit(articleBomProjection.getQuantityPerUnit())
                            .wastagePercent(articleBomProjection.getWastagePercent())
                            .remarks(articleBomProjection.Remarks())
                            .build())
                    .toList();

    }
}
