package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.MaterialCategoryDto;
import com.shreejee.Inventory.dto.MaterialDto;
import com.shreejee.Inventory.dto.MaterialPriceDto;
import com.shreejee.Inventory.service.MaterialPriceService;
import com.shreejee.Inventory.service.MaterialService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/material")
public class MaterialController {

    private final MaterialService materialService;
    private final MaterialPriceService materialPriceService;

    public MaterialController(MaterialService materialService, MaterialPriceService materialPriceService) {
        this.materialService = materialService;
        this.materialPriceService = materialPriceService;
    }

    @GetMapping("{materialId}")
    public ResponseEntity<MaterialDto> getMaterialById(@PathVariable Long materialId) {
        MaterialDto materialDto = materialService.getMaterialById(materialId);
        return new ResponseEntity<>(materialDto, HttpStatus.OK);
    }

    @PostMapping("materialCategory")
    public ResponseEntity<MaterialCategoryDto> saveMaterialCategory(@RequestBody MaterialCategoryDto materialCategoryDto) {
        materialCategoryDto = materialService.saveMaterialCategory(materialCategoryDto);
        return new ResponseEntity<>(materialCategoryDto, HttpStatus.OK);
    }

    @GetMapping("materialCategory")
    public ResponseEntity<List<MaterialCategoryDto>> fetchAllMaterialCategories() {
            List<MaterialCategoryDto> materialCategoryDtos = materialService.getMaterialCategories();
        return new ResponseEntity<>(materialCategoryDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<MaterialDto> saveMaterial(@RequestBody MaterialDto materialDto) {
        MaterialDto savedMaterial = materialService.saveMaterial(materialDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMaterial);
    }

    @GetMapping("materialCategory/{materialCategory}")
    public ResponseEntity<List<MaterialDto>> getMaterialByCategory(@PathVariable String materialCategory) {
        List<MaterialDto> materialDtoList = materialService.getMaterialsByCategory(materialCategory);
        return new ResponseEntity<>(materialDtoList, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<MaterialDto>> getMaterialList() {
        List<MaterialDto> materials = materialService.getMaterials();
        return new ResponseEntity<>(materials, HttpStatus.OK);
    }

    @GetMapping("materialPrice/{materialPriceId}")
    public ResponseEntity<MaterialPriceDto> getMaterialPrice(@PathVariable Long materialPriceId) {
        MaterialPriceDto materialPriceDto = materialPriceService.getMaterialPrice(materialPriceId);
        return new ResponseEntity<>(materialPriceDto, HttpStatus.OK);
    }

    @GetMapping("materialPrice/{materialId}")
    public ResponseEntity<List<MaterialPriceDto>> getMaterialPrices(@PathVariable Long materialId) {
        List<MaterialPriceDto> materialPriceDtos = materialPriceService.getMaterialPriceList(materialId);
        return new ResponseEntity<>(materialPriceDtos, HttpStatus.OK);
    }

    @PostMapping("materialPrice")
    public ResponseEntity<MaterialPriceDto> saveMaterialPrice(@RequestBody MaterialPriceDto materialPriceDto) {
        materialPriceDto = materialPriceService.saveMaterialPrice(materialPriceDto);
        return new ResponseEntity<>(materialPriceDto, HttpStatus.OK);
    }

}
