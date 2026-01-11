package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.request.PurchaseOrderRq;
import com.shreejee.Inventory.dto.response.PurchaseOrderRs;
import com.shreejee.Inventory.service.PurchaseOrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    public PurchaseOrderController(PurchaseOrderService purchaseOrderService) {
        this.purchaseOrderService = purchaseOrderService;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrderRs> create(@RequestBody PurchaseOrderRq purchaseOrderCreateRq) {
        PurchaseOrderRs purchaseOrderRs = purchaseOrderService.save(purchaseOrderCreateRq);
        return ResponseEntity
                .created(URI.create("/api/purchase-orders/" + purchaseOrderRs.getId()))
                .body(purchaseOrderRs);
    }

    @GetMapping("/{id}")
    public PurchaseOrderRs getById(@PathVariable Long id) {
        return purchaseOrderService.getById(id);
    }

    @GetMapping
    public List<PurchaseOrderRs> listBySupplier(@RequestParam(required = false) Long supplierId) {
        if (supplierId != null) {
            return purchaseOrderService.listBySupplier(supplierId);
        }
        // list all if no filter
        return purchaseOrderService.listBySupplier(null); // or add listAll in service and branch here
    }
}

