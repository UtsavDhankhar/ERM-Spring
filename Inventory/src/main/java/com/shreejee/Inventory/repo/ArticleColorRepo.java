package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.ArticleColor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleColorRepo extends JpaRepository<ArticleColor, Long> {

    List<ArticleColor> findByArticleId(Long id);
}
