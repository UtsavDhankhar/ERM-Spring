package com.shreejee.Inventory.dto.request;

import java.time.LocalDate;
import java.util.List;

public record PurchaseOrderRq (
        Long supplierId,
        String purchaseOrder,
        LocalDate orderDate,
        LocalDate expectedDeliveryDate,
        String remarks,
        List<PurchaseOrderLineRq> purchaseOrderLineRqList
){}
