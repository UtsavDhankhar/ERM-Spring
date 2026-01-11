package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.BrandDto;
import com.shreejee.Inventory.dto.request.BrandCreateRq;
import com.shreejee.Inventory.entity.Brand;
import com.shreejee.Inventory.enums.BrandStatus;
import com.shreejee.Inventory.exception.ValidationException;
import com.shreejee.Inventory.repo.BrandRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandService {

    private final BrandRepo brandRepo;

    public BrandService(BrandRepo brandRepo) {
        this.brandRepo = brandRepo;
    }

    public List<BrandDto> fetchAllBrand() {
        List<Brand> brands = brandRepo.findAll();
        return brands.stream()
                .map(brand -> BrandDto.builder()
                        .id(brand.getId())
                        .name(brand.getName())
                        .build())
                .toList();
    }

    public BrandDto findByBrandId(Long brandId) {

        Optional<Brand> brandOptional = brandRepo.findById(brandId);

        return brandOptional.map(brand -> BrandDto.builder()
                .id(brandId)
                .name(brand.getName())
                .code(brand.getCode())
                .phone(brand.getPhone())
                .email(brand.getEmail())
                .status(brand.getStatus())
                .build())
                .orElse(null);
    }

    public BrandDto saveBrand(BrandCreateRq brandCreateRq) {

        validateBrand(brandCreateRq);
        Brand brand = Brand.builder()
                .name(brandCreateRq.name())
                .code(brandCreateRq.code())
                .phone(brandCreateRq.phone())
                .contactPerson(brandCreateRq.contactPerson())
                .email(brandCreateRq.email())
                .status(BrandStatus.ACTIVE)
                .build();

        brand = brandRepo.save(brand);
        return BrandDto.builder()
                .name(brand.getName())
                .id(brand.getId())
                .build();
    }

    private void validateBrand(BrandCreateRq brandCreateRq) {
        if (brandCreateRq.code() == null) {
            throw new ValidationException("SYS07","Missing Brand");
        }
    }
}
