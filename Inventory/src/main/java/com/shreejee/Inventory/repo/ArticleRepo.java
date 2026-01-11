package com.shreejee.Inventory.repo;

import com.shreejee.Inventory.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepo extends JpaRepository<Article, Long> {

    List<Article> findByBrandId(Long id);
}
