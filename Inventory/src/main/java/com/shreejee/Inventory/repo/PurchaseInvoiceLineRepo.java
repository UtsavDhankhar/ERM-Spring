package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.PurchaseInvoiceLine;
import com.shreejee.Inventory.entity.PurchaseOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseInvoiceLineRepo extends JpaRepository<PurchaseInvoiceLine, Long> {

    List<PurchaseOrderLine> findByPurchaseInvoiceId(Long invoiceId);
}
