package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {

    Optional<PurchaseOrder> findByPurchaseOrderNo(String purchaseOrder);
    List<PurchaseOrder> findBySupplierId(Long supplierId);
    List<PurchaseOrder> findByOrderDateBetween(LocalDate from, LocalDate to);
}
