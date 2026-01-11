package com.shreejee.Inventory.dto.request;

public record BrandCreateRq (
        Long id,
        String name,
        String code,
        String contactPerson,
        String email,
        String phone
) {
}
