package com.shreejee.Inventory.service;

import com.shreejee.Inventory.entity.Supplier;
import com.shreejee.Inventory.repo.SupplierRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepo supplierRepo;

    public SupplierService(SupplierRepo supplierRepo) {
        this.supplierRepo = supplierRepo;
    }

//    List<Supplier> fetchAllSupplier() {
//
//        List<Supplier> supplierList = supplierRepo.findAll();
//    }
}
