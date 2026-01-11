package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepo extends JpaRepository<Brand, Long> {
}
