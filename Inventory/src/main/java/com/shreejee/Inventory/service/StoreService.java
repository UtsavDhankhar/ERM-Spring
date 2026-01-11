package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.StoreDto;
import com.shreejee.Inventory.entity.Store;
import com.shreejee.Inventory.repo.StoreRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.shreejee.Inventory.utils.DtoConvertors.generateMaterialDto;
import static com.shreejee.Inventory.utils.DtoConvertors.generateMaterialFromDto;

@Service
public class StoreService {

    private final StoreRepo storeRepo;

    public StoreService(StoreRepo storeRepo) {
        this.storeRepo = storeRepo;
    }

    public StoreDto findByMaterialId(Long materialId) {

        Optional<Store> storeOptional = storeRepo.findByMaterialId(materialId);
        return  storeOptional.map(store -> StoreDto.builder()
                .id(store.getId())
                .materialDto(generateMaterialDto(store.getMaterial()))
                .quantity(store.getQuantity())
                .build()).orElse(null);

    }

    public List<StoreDto> fetchAllStore () {

        List<Store> storeList = storeRepo.findAll();
        return storeList.stream()
                .map(store -> StoreDto.builder()
                        .id(store.getId())
                        .materialDto(generateMaterialDto(store.getMaterial()))
                        .quantity(store.getQuantity())
                        .build())
                .toList();
    }

    public StoreDto save(StoreDto storeDto) {

        Store store = Store.builder()
                .quantity(storeDto.getQuantity())
                .material(generateMaterialFromDto(storeDto.getMaterialDto()))
                .build();

        store = storeRepo.save(store);
        return StoreDto.builder()
                .id(store.getId())
                .quantity(store.getQuantity())
                .build();
    }

    public StoreDto updateStore(StoreDto storeDto) {

        Store store = null;

        synchronized (this) {
            store = storeRepo.findById(storeDto.getId()).orElse(null);
            if (store == null ) return this.save(storeDto);
            store.setQuantity(store.getQuantity().add(storeDto.getQuantity()));
            store = storeRepo.save(store);
        }

        return StoreDto.builder()
                .id(store.getId())
                .quantity(store.getQuantity())
                .build();
    }
}
