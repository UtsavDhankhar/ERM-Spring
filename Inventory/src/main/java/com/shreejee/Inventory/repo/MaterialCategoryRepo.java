package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialCategoryRepo extends JpaRepository<MaterialCategory, Long> {
}
