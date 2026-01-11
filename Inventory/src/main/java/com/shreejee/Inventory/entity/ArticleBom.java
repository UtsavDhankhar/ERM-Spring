package com.shreejee.Inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "article_bom_line")
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class ArticleBom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to Article
    @Column(name = "article_id", nullable = false)
    private Long articleId;

    // OPTIONAL — if BOM is color-specific
    @Column(name = "article_color_id", nullable = false)
    private Long articleColorId;

    // Link to Material
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_id", nullable = false)
    private Material material;

    // Required quantity for ONE unit of the article
    @Column(name = "quantity_per_unit", nullable = false, precision = 14, scale = 6)
    private BigDecimal quantityPerUnit;

    // Wastage % applied on required qty
    // e.g., 5.0 means 5% wastage
    @Column(name = "wastage_percent", nullable = false, precision = 5, scale = 2)
    private BigDecimal wastagePercent;

    @Column(name = "remarks")
    private String remarks;
}

