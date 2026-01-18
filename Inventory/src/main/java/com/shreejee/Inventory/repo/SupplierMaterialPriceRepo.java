package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.SupplierMaterialPrice;
import com.shreejee.Inventory.projection.MaterialSupplierPriceProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SupplierMaterialPriceRepo extends JpaRepository<SupplierMaterialPrice, Long> {


    @Query(value = """
     Select id, s.id as supplierId, s.name as supplierName, smp.price as pricePerUnit
     FROM supplier s
     JOIN supplier_material_price smp ON s.id == smp.supplier_id
     WHERE smp.material_id == :materialId
     AND smp.valid_to is NULL
     ORDER BY s.name
     """, nativeQuery = true)
    List<MaterialSupplierPriceProjection> getMaterialSupplierPrice(Long materialId);
}
