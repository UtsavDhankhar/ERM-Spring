package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.ArticleColorDto;
import com.shreejee.Inventory.dto.request.ArticleColorCreateRq;
import com.shreejee.Inventory.entity.ArticleColor;
import com.shreejee.Inventory.repo.ArticleColorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleColorService {

    private final ArticleColorRepo articleColorRepo;

    public ArticleColorService(ArticleColorRepo articleColorRepo) {
        this.articleColorRepo = articleColorRepo;
    }

    public ArticleColorDto getArticleColor(Long articleColorId) {

        Optional<ArticleColor> articleColorOptional = articleColorRepo.findById(articleColorId);
        return articleColorOptional.map(articleColor -> ArticleColorDto.builder()
                .id(articleColor.getId())
                .articleId(articleColor.getArticleId())
                .color(articleColor.getColor())
                .build()).orElse(null);
    }

    public ArticleColorDto saveArticleColor(ArticleColorCreateRq articleColorCreateRq) {

        ArticleColor articleColor = ArticleColor.builder()
                .color(articleColorCreateRq.color())
                .code(articleColorCreateRq.code())
                .articleId(articleColorCreateRq.articleId())
                .build();

        articleColor = articleColorRepo.save(articleColor);

        return ArticleColorDto.builder()
                .id(articleColor.getId())
                .color(articleColorCreateRq.color())
                .build();
    }

    public List<ArticleColorDto> getByArticle(Long articleId) {

        List<ArticleColor> articleColorList = articleColorRepo.findByArticleId(articleId);

        return articleColorList.stream()
                .map(articleColor -> ArticleColorDto.builder()
                        .articleId(articleColor.getArticleId())
                        .id(articleColor.getId())
                        .color(articleColor.getColor())
                        .isPrimary(articleColor.isPrimary())
                        .build())
                .toList();
    }
}
