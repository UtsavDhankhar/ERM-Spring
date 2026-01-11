package com.shreejee.Inventory.dto;

import com.shreejee.Inventory.enums.BrandStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BrandDto {

    private Long id;
    private String name;
    private String code;
    private String contactPerson;
    private String email;
    private String phone;

    @Enumerated(EnumType.STRING)
    private BrandStatus status;

}
