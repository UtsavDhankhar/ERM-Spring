package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.BrandDto;
import com.shreejee.Inventory.dto.request.BrandCreateRq;
import com.shreejee.Inventory.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController()
@RequestMapping("api/brands")
public class BrandController {

    private final BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping()
    public ResponseEntity<List<BrandDto>> getAllBrands () {
        List<BrandDto> brandDtos = brandService.fetchAllBrand();
        return new ResponseEntity<>(brandDtos, HttpStatus.OK);
    }

    @GetMapping(value = "{brandId}")
    public ResponseEntity<BrandDto> getBrandById(@PathVariable Long brandId) {
        BrandDto brandDto = brandService.findByBrandId(brandId);
        return new ResponseEntity<>(brandDto, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BrandDto> saveBrand(@RequestBody BrandCreateRq brandCreateRq) {
        BrandDto brandDto  = brandService.saveBrand(brandCreateRq);
        return new ResponseEntity<>(brandDto, HttpStatus.OK);
    }
}
