package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.MaterialPrice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialPriceRepo extends JpaRepository<MaterialPrice, Long> {

    List<MaterialPrice> findByMaterialId(Long materialId);
}
