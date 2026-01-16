package com.shreejee.Inventory.entity;

import com.shreejee.Inventory.enums.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Set;

@Entity
@Table(name = "material")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Material extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    @Column(nullable = false)
    private String name;

    @Column(name = "sub_category", nullable = false)
    private String subCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "material_id")
    private MaterialCategory materialCategory;

    @Column(name = "unit_of_measure", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure; // DB enum uom as text

    private String color;

//    private String shade; // Tan/Bright etc

    @OneToMany(mappedBy = "material", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SupplierMaterialPrice> supplierPrices;
}