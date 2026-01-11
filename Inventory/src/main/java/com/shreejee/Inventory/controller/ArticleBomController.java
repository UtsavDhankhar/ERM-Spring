package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.ArticleBomDto;
import com.shreejee.Inventory.entity.ArticleBom;
import com.shreejee.Inventory.service.ArticleBomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ArticleBomController {

    private final ArticleBomService articleBomService;

    public ArticleBomController(ArticleBomService articleBomService) {
        this.articleBomService = articleBomService;
    }

    @GetMapping("articleBom/{articleColorId}")
    public ResponseEntity<List<ArticleBomDto>> getArticleBomList(@PathVariable Long articleColorId) {

        List<ArticleBomDto> articleColorBomDto = articleBomService.getArticleColorBom(articleColorId);
        return new ResponseEntity<>(articleColorBomDto, HttpStatus.OK);
    }

    @PostMapping("articleBom")
    public ResponseEntity<ArticleBomDto> saveArticleBom(@RequestBody ArticleBomDto articleBomDto) {
        articleBomDto = articleBomService.saveArticleBom(articleBomDto);
        return new ResponseEntity<>(articleBomDto, HttpStatus.OK);
    }
}
