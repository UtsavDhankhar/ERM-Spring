package com.shreejee.Inventory.dto.response;

import com.shreejee.Inventory.enums.SalesOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class SalesOrderRs {

    Long id;
    String orderNo;
    Long brandId;
    LocalDate orderDate;
    SalesOrderStatus status;
    String remarks;
    List<SalesOrderLineRs> salesOrderLineRs;
}
