package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.request.ArticleBomCreateRq;
import com.shreejee.Inventory.dto.response.ArticleBomRs;
import com.shreejee.Inventory.service.ArticleBomService;
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
@RequestMapping("/api/articleBom")
public class ArticleBomController {

    private final ArticleBomService articleBomService;

    public ArticleBomController(ArticleBomService articleBomService) {
        this.articleBomService = articleBomService;
    }

    @GetMapping()
    public ResponseEntity<ArticleBomRs> getArticleBomList(@RequestParam Long articleId, Long articleColorId) {

        ArticleBomRs articleBom = articleBomService.getArticleBomByArticleIdAndColorId(articleId, articleColorId);
        return new ResponseEntity<>(articleBom, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ArticleBomRs> saveArticleBom(@RequestBody ArticleBomCreateRq articleBomCreateRq) {
        ArticleBomRs articleBomRs  = articleBomService.saveArticleBom(articleBomCreateRq);
        return new ResponseEntity<>(articleBomRs, HttpStatus.OK);
    }
}
