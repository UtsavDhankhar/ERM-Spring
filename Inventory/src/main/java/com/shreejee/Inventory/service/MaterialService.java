package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.MaterialCategoryDto;
import com.shreejee.Inventory.dto.request.MaterialCreateRq;
import com.shreejee.Inventory.dto.response.MaterialCategoryRs;
import com.shreejee.Inventory.dto.response.MaterialRs;
import com.shreejee.Inventory.entity.Material;
import com.shreejee.Inventory.entity.MaterialCategory;
import com.shreejee.Inventory.entity.SupplierMaterialPrice;
import com.shreejee.Inventory.enums.UnitOfMeasure;
import com.shreejee.Inventory.projection.MaterialSupplierPriceProjection;
import com.shreejee.Inventory.repo.MaterialCategoryRepo;
import com.shreejee.Inventory.repo.MaterialRepo;
import com.shreejee.Inventory.repo.SupplierMaterialPriceRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class MaterialService {

    private final MaterialRepo materialRepo;
    private final MaterialCategoryRepo materialCategoryRepo;
    private final SupplierMaterialPriceRepo supplierMaterialPriceRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public MaterialService(MaterialRepo materialRepo, MaterialCategoryRepo materialCategoryRepo, SupplierMaterialPriceRepo supplierMaterialPriceRepo) {
        this.materialRepo = materialRepo;
        this.materialCategoryRepo = materialCategoryRepo;
        this.supplierMaterialPriceRepo = supplierMaterialPriceRepo;
    }

    public MaterialRs getMaterialById(Long id) {

        Optional<Material> materialOptional = materialRepo.findById(id);
        List<MaterialSupplierPriceProjection> materialSupplierPriceProjectionList = supplierMaterialPriceRepo.getMaterialSupplierPrice(id);

        if (materialOptional.isEmpty()) return null;

        Material material = materialOptional.get();

        MaterialRs materialRs = MaterialRs.builder().id(id)
                .subCategory(material.getSubCategory())
                .name(material.getName())
                .color(material.getColor())
                .unitOfMeasure(material.getUnitOfMeasure())
                .build();

        materialRs.generateMaterialSupplierPrices(materialSupplierPriceProjectionList);

        return materialRs;

    }


    public List<MaterialRs> getMaterialsByCategory(String materialCategory) {

        List<Material> materialList = materialRepo.findBySubCategory(materialCategory);
        return materialList.stream()
                .map(material -> MaterialRs.builder()
                        .id(material.getId())
                        .name(material.getName())
                        .code(material.getCode())
                        .build())
                .toList();
    }


    public List<MaterialRs> getMaterials() {

        List<Material> materialList = materialRepo.findAll();
        return materialList.stream()
                .map(material -> MaterialRs.builder()
                        .id(material.getId())
                        .name(material.getName())
                        .code(material.getCode())
                        .color(material.getColor())
                        .subCategory(material.getSubCategory())
                        .unitOfMeasure(material.getUnitOfMeasure())
                        .build())
                .toList();
    }

    public List<MaterialCategoryDto> getMaterialCategories() {

        List<MaterialCategory> materialCategoryList = materialCategoryRepo.findAll();

        return materialCategoryList.stream()
                .map(materialCategory -> MaterialCategoryDto.builder()
                        .id(materialCategory.getId())
                        .name(materialCategory.getName())
                        .category(materialCategory.getCategory())
                        .build())
                .toList();
    }

    public MaterialCategoryDto saveMaterialCategory(MaterialCategoryDto materialCategoryDto) {

        MaterialCategory materialCategory = MaterialCategory.builder()
                .name(materialCategoryDto.getName())
                .category(materialCategoryDto.getCategory())
                .specification(materialCategoryDto.getSpecification())
                .build();

        materialCategory = materialCategoryRepo.save(materialCategory);

        return MaterialCategoryDto.builder()
                .id(materialCategory.getId())
                .name(materialCategory.getName())
                .build();
    }

    public MaterialRs saveMaterial(MaterialCreateRq materialCreateRq) {

        Material material = Material.builder()
                .code(materialCreateRq.code())
                .name(materialCreateRq.name())
                .subCategory(materialCreateRq.subCategory())
                .unitOfMeasure(UnitOfMeasure.valueOf(materialCreateRq.unitOfMeasure()))
                .color(materialCreateRq.color())
                .materialCategory(entityManager.getReference(MaterialCategory.class, materialCreateRq.materialCategoryId()))
                .build();

        material = materialRepo.save(material);
        return MaterialRs.builder()
                .name(material.getName())
                .id(material.getId())
                .build();
    }


    private MaterialCategoryRs generateMaterialCategoryRs(MaterialCategory materialCategory) {

        return MaterialCategoryRs.builder()
                .id(materialCategory.getId())
                .category(materialCategory.getCategory())
                .name(materialCategory.getName())
                .build();
    }
}
