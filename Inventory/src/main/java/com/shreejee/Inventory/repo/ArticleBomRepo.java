package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.ArticleBom;
import com.shreejee.Inventory.projection.ArticleBomProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleBomRepo extends JpaRepository<ArticleBom, Long> {

    List<ArticleBom> findByArticleColorId(Long articleColorId);

    @Query(value = """            
            SELECT a.id AS id,
             a.article_id AS articleId,
             a.article_color_id AS articleColorId,
             a.quantity_per_unit AS quantityPerUnit,
             a.wastage_percent AS wastePercent
             a.remarks AS remarks,
             m.id AS materialId,
             m.code As materialCode,
             m.name AS materialName,
             m.color AS materialColor,
             m.unit_of_measure AS unitOfMeasure,
             FROM article_bom_line a
             JOIN material m ON a.materialId == m.id
             WHERE a.articleId == :articleId
             AND a.articleColorId == :articleColorId
            """, nativeQuery = true)
    List<ArticleBomProjection> findByArticleIdAndColorId(Long articleId, Long articleColorId);
}
