package com.shreejee.Inventory.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "article_color")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class ArticleColor extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="article_id", nullable = false)
    private Long articleId;

    @Column(name = "name", nullable = false)
    private String color;

    private String code;

    @Column(name="is_primary", nullable=false)
    private boolean isPrimary;
}
