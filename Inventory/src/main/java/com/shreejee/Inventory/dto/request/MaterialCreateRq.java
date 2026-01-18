package com.shreejee.Inventory.dto.request;


public record MaterialCreateRq (
    String code,
    String name,
    String subCategory,
    String unitOfMeasure,
    String color,
    Long materialCategoryId
) {

}
