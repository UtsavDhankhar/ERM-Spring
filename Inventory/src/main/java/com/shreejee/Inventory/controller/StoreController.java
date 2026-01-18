package com.shreejee.Inventory.controller;

import com.shreejee.Inventory.dto.request.StoreRq;
import com.shreejee.Inventory.dto.response.StoreRs;
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
@RequestMapping("/api/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @GetMapping("/{materialId}")
    public ResponseEntity<StoreRs> getStoreData(@PathVariable Long materialId) {
        StoreRs storeDto = storeService.findByMaterialId(materialId);
        return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<StoreRs>> getStoreList() {
        List<StoreRs> storeDtos = storeService.fetchAllStore();
        return new ResponseEntity<>(storeDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<StoreRs> saveStore (@RequestBody StoreRq storeCreateRq) {
        StoreRs storeRs = storeService.save(storeCreateRq);
        return new ResponseEntity<>(storeRs, HttpStatus.OK);
    }
}
