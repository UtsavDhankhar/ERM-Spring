package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepo extends JpaRepository<Material, Long> {

    List<Material> findBySubCategory(String category);
}
