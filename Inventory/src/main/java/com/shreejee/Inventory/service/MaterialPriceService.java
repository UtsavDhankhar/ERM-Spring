package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.MaterialPriceDto;
import com.shreejee.Inventory.entity.Material;
import com.shreejee.Inventory.entity.MaterialPrice;
import com.shreejee.Inventory.repo.MaterialPriceRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.shreejee.Inventory.utils.DtoConvertors.generateMaterialDto;
import static com.shreejee.Inventory.utils.DtoConvertors.generateMaterialFromDto;

@Service
public class MaterialPriceService {

    private final MaterialPriceRepo materialPriceRepo;

    public MaterialPriceService(MaterialPriceRepo materialPriceRepo) {
        this.materialPriceRepo = materialPriceRepo;
    }

    public MaterialPriceDto getMaterialPrice(Long materialPriceId) {

        Optional<MaterialPrice> materialPriceOptional = materialPriceRepo.findById(materialPriceId);
        return materialPriceOptional.map(materialPrice -> MaterialPriceDto.builder()
                .material(generateMaterialDto(materialPrice.getMaterial()))
                .pricePerUnit(materialPrice.getPricePerUnit())
                .currency(materialPrice.getCurrency())
                .validFrom(materialPrice.getValidFrom())
                .validTo(materialPrice.getValidTo())
                .build()).orElse(null);
    }

    public List<MaterialPriceDto> getMaterialPriceList(List<Long> materialIds) {

        List<MaterialPrice> materialPriceList = materialPriceRepo.findAllById(materialIds);

        return materialPriceList.stream()
                .map(materialPrice -> MaterialPriceDto.builder()
                        .material(generateMaterialDto(materialPrice.getMaterial()))
                        .pricePerUnit(materialPrice.getPricePerUnit())
                        .currency(materialPrice.getCurrency())
                        .build())
                .toList();
    }

    public List<MaterialPriceDto> getMaterialPriceList(Long materialId) {

        List<MaterialPrice> materialPriceList = materialPriceRepo.findByMaterialId(materialId);

        return materialPriceList.stream()
                .map(materialPrice -> MaterialPriceDto.builder()
                        .material(generateMaterialDto(materialPrice.getMaterial()))
                        .pricePerUnit(materialPrice.getPricePerUnit())
                        .currency(materialPrice.getCurrency())
                        .build())
                .toList();
    }

    public MaterialPriceDto saveMaterialPrice(MaterialPriceDto materialPriceDto) {

        MaterialPrice materialPrice = MaterialPrice.builder()
                .material(generateMaterialFromDto(materialPriceDto.getMaterial()))
                .pricePerUnit(materialPriceDto.getPricePerUnit())
                .currency(materialPriceDto.getCurrency())
                .validFrom(materialPriceDto.getValidFrom())
                .build();

        materialPrice = materialPriceRepo.save(materialPrice);
        return MaterialPriceDto.builder()
                .id(materialPrice.getId())
                .pricePerUnit(materialPrice.getPricePerUnit())
                .build();
    }

}
