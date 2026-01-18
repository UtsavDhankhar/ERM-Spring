package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.Store;
import com.shreejee.Inventory.projection.StoreProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StoreRepo extends JpaRepository<Store, Long> {

    Optional<Store> findByMaterialId(Long materialId);

    @Query(value = """
            SELECT s.id AS id,
            s.quantity AS quantity,
            m.id AS materialId,
            m.name AS materialName,
            m.color AS materialColor,
            m.unit_of_measure AS unitOfMeasure
            m.sub_category AS subCategory
            FROM store s
            JOIN material m ON s.material_id = m.id
            """, nativeQuery = true)
    List<StoreProjection> findAllMaterials();


    @Query(value = """
            SELECT s.id AS id,
            s.quantity AS quantity,
            m.id AS materialId,
            m.name AS materialName,
            m.color AS materialColor,
            m.unit_of_measure AS unitOfMeasure
            m.sub_category AS subCategory
            FROM store s
            JOIN material m ON s.material_id = m.id
            WHERE m.material_category_id = :materialCategoryId
            """, nativeQuery = true)
    List<StoreProjection> findAllByMaterialCategoryId(Long materialCategoryId);



    @Query(value = """
            SELECT s.id AS id,
            s.quantity AS quantity,
            m.id AS materialId,
            m.name AS materialName,
            m.color AS materialColor,
            m.unit_of_measure AS unitOfMeasure
            m.sub_category AS subCategory
            FROM store s
            JOIN material m ON s.material_id = m.id
            WHERE m.sub_category equal :subCategory
            """, nativeQuery = true)
    List<StoreProjection> findAllByMaterialSubCategory(String subCategory);
}
