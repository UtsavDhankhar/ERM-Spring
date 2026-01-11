package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.ArticleDto;
import com.shreejee.Inventory.dto.request.ArticleCreateRq;
import com.shreejee.Inventory.entity.Article;
import com.shreejee.Inventory.enums.ArticleStatus;
import com.shreejee.Inventory.repo.ArticleRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    private final ArticleRepo articleRepo;

    public ArticleService(ArticleRepo articleRepo) {
        this.articleRepo = articleRepo;
    }

    public ArticleDto getArticleById(Long id) {

        Optional<Article> articleOptional = articleRepo.findById(id);
        return articleOptional.map(article -> ArticleDto.builder()
                .brandId(article.getBrandId())
                .name(article.getName())
                .code(article.getCode())
                .description(article.getDescription())
                .category(article.getCategory())
                .status(article.getStatus())
                .build()).orElse(null);
    }

    public List<ArticleDto> getArticlesByBrand(Long brandId) {

        List<Article> articleList = articleRepo.findByBrandId(brandId);
        return articleList.stream()
                .map(article -> ArticleDto.builder()
                        .id(article.getId())
                        .name(article.getName())
                        .code(article.getCode())
                        .brandId(article.getBrandId())
                        .category(article.getCode())
                        .status(article.getStatus())
                        .build())
                .toList();
    }

    public ArticleDto saveArticle(ArticleCreateRq articleDto) {

        Article article = Article.builder()
                .brandId(articleDto.brandId())
                .name(articleDto.name())
                .category(articleDto.category())
                .status(ArticleStatus.SAMPLING)
                .code(articleDto.code())
                .description(articleDto.description())
                .build();

        article = articleRepo.save(article);
        return ArticleDto.builder()
                .id(article.getId())
                .name(article.getName())
                .build();
    }
}
