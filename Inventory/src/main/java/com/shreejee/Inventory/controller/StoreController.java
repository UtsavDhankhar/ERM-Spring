package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.StoreDto;
import com.shreejee.Inventory.service.StoreService;
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
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("store/{materialId}")
    public ResponseEntity<StoreDto> getStoreData(@PathVariable Long materialId) {
        StoreDto storeDto = storeService.findByMaterialId(materialId);
        return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }

    @GetMapping("store/all")
    public ResponseEntity<List<StoreDto>> getStoreList() {
        List<StoreDto> storeDtos = storeService.fetchAllStore();
        return new ResponseEntity<>(storeDtos, HttpStatus.OK);
    }

    @PostMapping("store")
    public ResponseEntity<StoreDto> saveStore (@RequestBody StoreDto storeDto) {
        storeDto = storeService.save(storeDto);
        return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }
}
