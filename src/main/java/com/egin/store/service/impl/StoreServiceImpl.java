package com.egin.store.service.impl;

import com.egin.store.exception.StoreNotFoundException;
import com.egin.store.model.Store;
import com.egin.store.model.dto.request.StoreCreateRequest;
import com.egin.store.model.dto.request.StoreUpdateRequest;
import com.egin.store.model.entity.StoreEntity;
import com.egin.store.model.enums.StoreStatus;
import com.egin.store.model.mapper.ListStoreEntityToListStoreMapper;
import com.egin.store.model.mapper.StoreCreateRequestToStoreEntityMapper;
import com.egin.store.model.mapper.StoreEntityToStoreMapper;
import com.egin.store.model.mapper.StoreUpdateRequestToStoreEntityMapper;
import com.egin.store.repository.StoreRepository;
import com.egin.store.service.StoreService;
import com.egin.user.model.entity.UserEntity;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import com.egin.user.service.user.UserReadService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserReadService userReadService;

    public StoreServiceImpl(
            StoreRepository storeRepository,
            UserReadService userReadService
    ) {
        this.storeRepository = storeRepository;
        this.userReadService = userReadService;
    }

    @Override
    public Store createStore(StoreCreateRequest request) {

        final UserEntity userEntity = UserToUserEntityMapper.toUserEntity(userReadService.getCurrentUser());
        final StoreEntity storeEntity = StoreCreateRequestToStoreEntityMapper.toStoreEntity(request, userEntity);
        final StoreEntity savedStoreEntity = storeRepository.save(storeEntity);

        return StoreEntityToStoreMapper.toStore(savedStoreEntity);
    }

    @Override
    public Store getStoreById(String storeId) {
        final StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + storeId));
        return StoreEntityToStoreMapper.toStore(storeEntity);
    }

    @Override
    public List<Store> getAllStores() {

        final List<StoreEntity> storeEntities = storeRepository.findAll();
        final List<Store> stores = ListStoreEntityToListStoreMapper.toStoreList(storeEntities);

        return stores;
    }

    @Override
    public Store getStoreByAdmin() {

        final StoreEntity storeEntity = storeRepository.findByStoreAdminId(
                userReadService.getCurrentUser().getId()
        );

        return StoreEntityToStoreMapper.toStore(storeEntity);

    }

    @Override
    public Store updateStore(String storeId, StoreUpdateRequest request) {

        final StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + storeId));

        StoreUpdateRequestToStoreEntityMapper.toStoreEntity(storeEntity, request);
        final StoreEntity updatedStoreEntity = storeRepository.save(storeEntity);

        return StoreEntityToStoreMapper.toStore(updatedStoreEntity);
    }

    @Override
    public void deleteStore(String storeId) {

        final StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + storeId));

        storeRepository.delete(storeEntity);

    }

    @Override
    public Store getStoreByEmployee() {
        return null;
    }

    @Override
    public Store changeStatus(String storeId, String status) {
        final StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + storeId));
        storeEntity.setStatus(Enum.valueOf(StoreStatus.class, status));
        final StoreEntity updatedStoreEntity = storeRepository.save(storeEntity);
        return StoreEntityToStoreMapper.toStore(updatedStoreEntity);
    }
}
