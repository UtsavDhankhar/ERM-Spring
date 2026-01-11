package com.shreejee.Inventory.dto.request;

import com.shreejee.Inventory.enums.ArticleStatus;

public record ArticleCreateRq (
         Long brandId,
         String code,
         String name,
         String description,
         String category
){}
