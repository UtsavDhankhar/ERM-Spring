package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.service.SupplierService;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }
}
