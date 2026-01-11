package com.shreejee.Inventory.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "material_price")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MaterialPrice extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_id")
    private Material material;

    @Column(nullable = false)
    private String currency; // default INR

    @Column(name = "price_per_unit", nullable = false)
    private java.math.BigDecimal pricePerUnit;

    @Column(name = "valid_from", nullable = false)
    private LocalDate validFrom;

    @Column(name = "valid_to")
    private LocalDate validTo;
}
