package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.request.ArticleBomCreateRq;
import com.shreejee.Inventory.dto.response.ArticleBomRs;
import com.shreejee.Inventory.entity.ArticleBom;
import com.shreejee.Inventory.entity.Material;
import com.shreejee.Inventory.projection.ArticleBomProjection;
import com.shreejee.Inventory.repo.ArticleBomRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleBomService {

    private final ArticleBomRepo articleBomRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public ArticleBomService(ArticleBomRepo articleBomRepo) {
        this.articleBomRepo = articleBomRepo;
    }

    public ArticleBomRs getArticleBomByArticleIdAndColorId(Long articleId, Long articleColorId) {

        List<ArticleBomProjection> articleBomProjections = articleBomRepo.findByArticleIdAndColorId(articleId, articleColorId);
        if (!articleBomProjections.isEmpty()) {
            ArticleBomRs articleBomRs = ArticleBomRs.builder()
                    .articleId(articleBomProjections.getFirst().getArticleId())
                    .articleColorId(articleBomProjections.getFirst().getArticleColorId())
                    .build();
            articleBomRs.setBomMaterialList(articleBomProjections);
            return articleBomRs;
        }

        return ArticleBomRs.builder().build();
    }

    public ArticleBomRs saveArticleBom(ArticleBomCreateRq articleBomCreateRq) {


        Long articleId = articleBomCreateRq.articleId();
        Long articleColorId = articleBomCreateRq.articleColorId();

        List<ArticleBom> articleBoms = articleBomCreateRq.bomMaterialCreateRqList().stream()
                .map(bomMaterial -> ArticleBom.builder()
                        .articleId(articleId)
                        .articleColorId(articleColorId)
                        .wastagePercent(bomMaterial.wastagePercent())
                        .quantityPerUnit(bomMaterial.quantityPerUnit())
                        .remarks(bomMaterial.remarks())
                        .material(entityManager.getReference(Material.class, bomMaterial.materialId()))
                        .build())
                .toList();

        List<ArticleBom> articleBomsRs = articleBomRepo.saveAll(articleBoms);

        return ArticleBomRs.builder()
                .articleId(articleBomsRs.getFirst().getArticleId())
                .build();
    }

}
