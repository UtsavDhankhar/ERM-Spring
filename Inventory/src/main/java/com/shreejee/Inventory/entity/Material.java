package com.shreejee.Inventory.entity;

import com.shreejee.Inventory.enums.UnitOfMeasure;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

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

    private String category;

    @Column(name = "unit_of_measure", nullable = false)
    @JdbcTypeCode(SqlTypes.NAMED_ENUM)
    @Enumerated(EnumType.STRING)
    private UnitOfMeasure unitOfMeasure; // DB enum uom as text

    private String color;

//    private String shade; // Tan/Bright etc

    @Column(name = "supplier_id")
    private Long supplierId; // keep simple for now
}