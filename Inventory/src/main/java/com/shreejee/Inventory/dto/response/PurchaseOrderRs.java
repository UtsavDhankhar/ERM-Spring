package com.shreejee.Inventory.dto.response;

import com.shreejee.Inventory.enums.PurchaseOrderStatus;
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
public class PurchaseOrderRs {

    Long id;
    String purchaseOrderNo;
    Long supplierId;
    LocalDate orderDate;
    LocalDate expectedDeliveryDate;
    PurchaseOrderStatus status;
    String remarks;
    List<PurchaseOrderLineRs> purchaseOrderLineRs;
}
