package com.shreejee.Inventory.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SalesOrderLineRs {

    Long id;
    Long articleId;
    Long articleColorId;
    String size;
    Integer quantityOrdered;
    LocalDate targetDispatchDate;
}
