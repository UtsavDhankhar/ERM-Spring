package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepo extends JpaRepository<Store, Long> {

    Optional<Store> findByMaterialId(Long materialId);
}
