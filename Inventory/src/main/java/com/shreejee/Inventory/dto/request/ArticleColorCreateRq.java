package com.shreejee.Inventory.dto.request;

public record ArticleColorCreateRq(
     Long articleId,
     String color,
     String code,
     Boolean isPrimary
){ }
