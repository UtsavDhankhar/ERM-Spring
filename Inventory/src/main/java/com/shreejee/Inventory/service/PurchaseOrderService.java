package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.request.PurchaseOrderRq;
import com.shreejee.Inventory.dto.response.PurchaseOrderLineRs;
import com.shreejee.Inventory.dto.response.PurchaseOrderRs;
import com.shreejee.Inventory.entity.Material;
import com.shreejee.Inventory.entity.PurchaseOrder;
import com.shreejee.Inventory.entity.PurchaseOrderLine;
import com.shreejee.Inventory.enums.PurchaseOrderStatus;
import com.shreejee.Inventory.repo.MaterialRepo;
import com.shreejee.Inventory.repo.PurchaseOrderRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PurchaseOrderService {

    private final PurchaseOrderRepo purchaseOrderRepo;
    private final MaterialRepo materialRepo;

    @Transactional
    public PurchaseOrderRs save(PurchaseOrderRq purchaseOrderRq) {

        String purchaseOrderNo = (purchaseOrderRq.purchaseOrder() == null || purchaseOrderRq.purchaseOrder().isBlank())
                ? generatePurchaseOrder()
                : purchaseOrderRq.purchaseOrder();

        PurchaseOrder purchaseOrder = PurchaseOrder.builder()
                .purchaseOrderNo(purchaseOrderNo)
                .supplierId(purchaseOrderRq.supplierId())
                .orderDate(purchaseOrderRq.orderDate() != null ? purchaseOrderRq.orderDate() : LocalDate.now())
                .expectedDeliveryDate(purchaseOrderRq.expectedDeliveryDate())
                .status(PurchaseOrderStatus.CREATED)
                .remarks(purchaseOrderRq.remarks())
                .build();

        List<PurchaseOrderLine> purchaseOrderLineList = purchaseOrderRq.purchaseOrderLineRqList().stream()
                .map(purchaseOrderLine -> {

                    Material material = materialRepo.findById(purchaseOrderLine.materialId())
                            .orElseThrow(() -> new IllegalArgumentException("Material not found: " + purchaseOrderLine.materialId()));

                    return PurchaseOrderLine.builder()
                            .purchaseOrder(purchaseOrder)
                            .material(material)
                            .orderedQty(purchaseOrderLine.orderedQty())
                            .receivedQty(BigDecimal.ZERO)
                            .pricePerUnit(purchaseOrderLine.pricePerUnit())
                            .build();
                })
                .toList();

        purchaseOrder.setPurchaseOrderLines(purchaseOrderLineList);

        PurchaseOrder purchaseOrderSaved = purchaseOrderRepo.save(purchaseOrder);

        return PurchaseOrderRs.builder()
                .id(purchaseOrderSaved.getId())
                .purchaseOrderNo(purchaseOrderSaved.getPurchaseOrderNo())
                .build();
    }

    public PurchaseOrderRs getById(Long id) {
        PurchaseOrder purchaseOrder = purchaseOrderRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Purchase order not found: " + id));
        return generatePurchaseOrderResponse(purchaseOrder);
    }

    public List<PurchaseOrderRs> listBySupplier(Long supplierId) {
        return purchaseOrderRepo.findBySupplierId(supplierId).stream()
                .map(this::generatePurchaseOrderResponse)
                .toList();
    }

    private String generatePurchaseOrder() {
        return "PO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private PurchaseOrderRs generatePurchaseOrderResponse(PurchaseOrder purchaseOrder) {
        List<PurchaseOrderLineRs> purchaseOrderLineRs = purchaseOrder.getPurchaseOrderLines().stream()
                .map(purchaseOrderLine -> new PurchaseOrderLineRs(
                        purchaseOrderLine.getId(),
                        purchaseOrderLine.getMaterial().getId(),
                        purchaseOrderLine.getMaterial().getCode(),
                        purchaseOrderLine.getMaterial().getName(),
                        purchaseOrderLine.getOrderedQty(),
                        purchaseOrderLine.getReceivedQty(),
                        purchaseOrderLine.getPricePerUnit()
                ))
                .toList();

        return PurchaseOrderRs.builder()
                .id(purchaseOrder.getId())
                .orderDate(purchaseOrder.getOrderDate())
                .purchaseOrderNo(purchaseOrder.getPurchaseOrderNo())
                .status(purchaseOrder.getStatus())
                .purchaseOrderLineRs(purchaseOrderLineRs)
                .expectedDeliveryDate(purchaseOrder.getExpectedDeliveryDate())
                .remarks(purchaseOrder.getRemarks())
                .build();
    }
}

