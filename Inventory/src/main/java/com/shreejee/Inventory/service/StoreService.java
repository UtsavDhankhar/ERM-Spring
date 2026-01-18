package com.shreejee.Inventory.service;

import com.shreejee.Inventory.dto.request.StoreRq;
import com.shreejee.Inventory.dto.response.StoreRs;
import com.shreejee.Inventory.entity.Material;
import com.shreejee.Inventory.entity.Store;
import com.shreejee.Inventory.projection.StoreProjection;
import com.shreejee.Inventory.repo.StoreRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class StoreService {

    private final StoreRepo storeRepo;

    @PersistenceContext
    private EntityManager entityManager;

    public StoreService(StoreRepo storeRepo) {
        this.storeRepo = storeRepo;
    }

    public StoreRs findByMaterialId(Long materialId) {

        Optional<Store> storeOptional = storeRepo.findByMaterialId(materialId);
        return  storeOptional.map(store -> StoreRs.builder()
                .id(store.getId())
                .quantity(store.getQuantity())
                .build()).orElse(null);
    }

    public List<StoreRs> fetchAllStore () {
        List<StoreProjection> storeProjectionList = storeRepo.findAllMaterials();
        return storeProjectionList.stream()
                .map(storeProjection -> StoreRs.builder()
                        .id(storeProjection.getId())
                        .quantity(storeProjection.getQuantity())
                        .materialName(storeProjection.getMaterialName())
                        .materialColor(storeProjection.getMaterialColor())
                        .unitOfMeasure(storeProjection.getUnitOfMeasure())
                        .subCategory(storeProjection.getSubCategory())
                        .build())
                .toList();
    }

    public StoreRs save(StoreRq storeRq) {

        Store store = Store.builder()
                .quantity(storeRq.quantity())
                .material(entityManager.getReference(Material.class, storeRq.materialId()))
                .build();

        store = storeRepo.save(store);
        return StoreRs.builder()
                .id(store.getId())
                .build();
    }

    public StoreRs updateStore(StoreRq storeRq) {

        Store store = null;
        synchronized (this) {
            store = storeRepo.findById(storeRq.id()).orElse(null);
            if (store == null ) return this.save(storeRq);
            store.setQuantity(store.getQuantity().add(storeRq.quantity()));
            store = storeRepo.save(store);
        }
        return StoreRs.builder()
                .id(store.getId())
                .quantity(store.getQuantity())
                .build();
    }
}
