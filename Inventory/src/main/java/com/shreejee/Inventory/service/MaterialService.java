package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.MaterialCategoryDto;
import com.shreejee.Inventory.dto.MaterialDto;
import com.shreejee.Inventory.entity.Material;
import com.shreejee.Inventory.entity.MaterialCategory;
import com.shreejee.Inventory.repo.MaterialCategoryRepo;
import com.shreejee.Inventory.repo.MaterialRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaterialService {

    private final MaterialRepo materialRepo;
    private final MaterialCategoryRepo materialCategoryRepo;

    public MaterialService(MaterialRepo materialRepo, MaterialCategoryRepo materialCategoryRepo) {
        this.materialRepo = materialRepo;
        this.materialCategoryRepo = materialCategoryRepo;
    }

    public MaterialDto getMaterialById(Long id) {

        Optional<Material> materialOptional = materialRepo.findById(id);
        return materialOptional.map(material -> MaterialDto.builder()
                .id(id)
                .name(material.getName())
                .code(material.getCode())
                .color(material.getColor())
                .category(material.getCategory())
                .supplierId(material.getSupplierId())
                .unitOfMeasure(material.getUnitOfMeasure())
                .build()).orElse(null);
    }

    public List<MaterialDto> getMaterialsByCategory(String materialCategory) {

        List<Material> materialList = materialRepo.findByCategory(materialCategory);

        return materialList.stream()
                .map(material -> MaterialDto.builder()
                        .id(material.getId())
                        .name(material.getName())
                        .code(material.getCode())
                        .build())
                .toList();
    }


    public List<MaterialDto> getMaterials() {

        List<Material> materialList = materialRepo.findAll();
        return materialList.stream()
                .map(material -> MaterialDto.builder()
                        .id(material.getId())
                        .name(material.getName())
                        .code(material.getCode())
                        .color(material.getColor())
                        .category(material.getCategory())
                        .supplierId(material.getSupplierId())
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

    public MaterialDto saveMaterial(MaterialDto materialDto) {

        Material material = Material.builder()
                .name(materialDto.getName())
                .category(materialDto.getCategory())
                .code(materialDto.getCode())
                .color(materialDto.getColor())
                .unitOfMeasure(materialDto.getUnitOfMeasure())
                .supplierId(materialDto.getSupplierId())
                .build();

        material = materialRepo.save(material);

        return MaterialDto.builder()
                .name(material.getName())
                .id(material.getId())
                .build();
    }
}
