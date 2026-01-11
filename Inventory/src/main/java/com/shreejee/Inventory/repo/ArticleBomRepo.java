package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.ArticleBom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleBomRepo extends JpaRepository<ArticleBom, Long> {

    List<ArticleBom> findByArticleColorId(Long articleColorId);
}
