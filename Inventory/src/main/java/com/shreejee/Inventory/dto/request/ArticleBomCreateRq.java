package com.shreejee.Inventory.dto.request;

import java.util.List;

public record ArticleBomCreateRq (

        Long articleId,
        Long articleColorId,
        List<BomMaterialCreateRq> bomMaterialCreateRqList
) {
}
