package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.ArticleColorDto;
import com.shreejee.Inventory.dto.ArticleDto;
import com.shreejee.Inventory.dto.BrandDto;
import com.shreejee.Inventory.dto.request.ArticleColorCreateRq;
import com.shreejee.Inventory.dto.request.ArticleCreateRq;
import com.shreejee.Inventory.service.ArticleColorService;
import com.shreejee.Inventory.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/articles")
public class ArticleController {

    private final ArticleService articleService;
    private final ArticleColorService articleColorService;

    public ArticleController(ArticleService articleService, ArticleColorService articleColorService) {
        this.articleService = articleService;
        this.articleColorService = articleColorService;
    }

    @GetMapping("{articleId}")
    public ResponseEntity<ArticleDto> getArticle(@PathVariable long articleId) {
        ArticleDto articleDto = articleService.getArticleById(articleId);
        return new ResponseEntity<>(articleDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ArticleDto>> getArticleByBrand (@RequestParam(required = false) Long brandId) {

        List<ArticleDto> articleDtos = articleService.getArticlesByBrand(brandId);
        return new ResponseEntity<>(articleDtos, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ArticleDto> saveArticle(@RequestBody ArticleCreateRq articleCreateRq) {
        ArticleDto articleDto = articleService.saveArticle(articleCreateRq);
        return new ResponseEntity<>(articleDto, HttpStatus.OK);
    }

    @GetMapping("articleColors/{articleId}")
    public ResponseEntity<List<ArticleColorDto>> getArticleColorByArticle (@PathVariable Long articleId) {

        List<ArticleColorDto> articleColorDtos = articleColorService.getByArticle(articleId);
        return new ResponseEntity<>(articleColorDtos, HttpStatus.OK);
    }

    @GetMapping("articleColor/{colorId}")
    public ResponseEntity<ArticleColorDto> getArticleColor(@PathVariable long colorId) {
        ArticleColorDto articleColorDto = articleColorService.getArticleColor(colorId);
        return new ResponseEntity<>(articleColorDto, HttpStatus.OK);
    }

    @PostMapping("articleColors")
    public ResponseEntity<ArticleColorDto> saveArticleColor(@RequestBody ArticleColorCreateRq articleColorRq) {
        ArticleColorDto articleColorDto = articleColorService.saveArticleColor(articleColorRq);
        return new ResponseEntity<>(articleColorDto, HttpStatus.OK);
    }
}
