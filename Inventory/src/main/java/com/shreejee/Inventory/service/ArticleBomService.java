package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.ArticleBomDto;
import com.shreejee.Inventory.entity.ArticleBom;
import com.shreejee.Inventory.repo.ArticleBomRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import static com.shreejee.Inventory.utils.DtoConvertors.generateMaterialDto;
import static com.shreejee.Inventory.utils.DtoConvertors.generateMaterialFromDto;

@Service
public class ArticleBomService {

    private final ArticleBomRepo articleBomRepo;

    public ArticleBomService(ArticleBomRepo articleBomRepo) {
        this.articleBomRepo = articleBomRepo;
    }

    public List<ArticleBomDto> getArticleColorBom(Long articleColorId) {

        List<ArticleBom> articleBomList = articleBomRepo.findByArticleColorId(articleColorId);

        return articleBomList.stream()
                .map(articleBom -> ArticleBomDto.builder()
                        .id(articleBom.getId())
                        .articleColorId(articleColorId)
                        .articleId(articleBom.getArticleId())
                        .material(generateMaterialDto(articleBom.getMaterial()))
                        .quantityPerUnit(articleBom.getQuantityPerUnit())
                        .wastagePercent(articleBom.getWastagePercent())
                        .build())
                .toList();
    }

    public ArticleBomDto saveArticleBom(ArticleBomDto articleBomDto) {

        ArticleBom articleBom = ArticleBom.builder()
                .articleId(articleBomDto.getArticleId())
                .articleColorId(articleBomDto.getArticleColorId())
                .material(generateMaterialFromDto(articleBomDto.getMaterial()))
                .quantityPerUnit(articleBomDto.getQuantityPerUnit())
                .remarks(articleBomDto.getRemarks())
                .wastagePercent(articleBomDto.getWastagePercent())
                .build();

        articleBom = articleBomRepo.save(articleBom);

        return ArticleBomDto.builder()
                .id(articleBom.getId())
                .build();
    }

}
