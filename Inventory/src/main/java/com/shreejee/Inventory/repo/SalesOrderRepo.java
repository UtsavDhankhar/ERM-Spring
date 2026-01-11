package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface SalesOrderRepo extends JpaRepository<SalesOrder, Long> {
    Optional<SalesOrder> findByOrderNo(String orderNo);

    List<SalesOrder> findByBrandId(Long brandId);
    List<SalesOrder> findByOrderDateBetween(LocalDate from, LocalDate to);
}