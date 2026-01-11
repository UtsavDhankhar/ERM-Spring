package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.PurchaseInvoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PurchaseInvoiceRepo extends JpaRepository<PurchaseInvoice, Long> {

    Optional<PurchaseInvoice> findBySupplierIdAndInvoiceNo(Long supplierId, String invoiceNo);
}
