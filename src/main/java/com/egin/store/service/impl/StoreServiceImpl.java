package com.egin.store.service.impl;

import com.egin.common.model.CustomPage;
import com.egin.store.exception.StoreNotFoundException;
import com.egin.store.model.Store;
import com.egin.store.model.dto.request.StoreByAdminPagingRequest;
import com.egin.store.model.dto.request.StoreCreateRequest;
import com.egin.store.model.dto.request.StorePagingRequest;
import com.egin.store.model.dto.request.StoreUpdateRequest;
import com.egin.store.model.entity.StoreEntity;
import com.egin.store.model.enums.StoreStatus;
import com.egin.store.model.mapper.ListStoreEntityToListStoreMapper;
import com.egin.store.model.mapper.StoreCreateRequestToStoreEntityMapper;
import com.egin.store.model.mapper.StoreEntityToStoreMapper;
import com.egin.store.model.mapper.StoreUpdateRequestToStoreEntityMapper;
import com.egin.store.repository.StoreRepository;
import com.egin.store.service.StoreService;
import com.egin.user.model.User;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import com.egin.user.service.user.UserReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
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
    @CachePut(value = "storeById", key = "#result.id")
    public Store createStore(StoreCreateRequest request) {

//        final UserEntity userEntity = UserToUserEntityMapper.toUserEntity(userReadService.getCurrentUser());
        final User user = userReadService.getCurrentUser();
        final StoreEntity storeEntity = StoreCreateRequestToStoreEntityMapper.toStoreEntity(
                request,
                UserToUserEntityMapper.toUserEntity(user)
        );
        final StoreEntity savedStoreEntity = storeRepository.save(storeEntity);

        return StoreEntityToStoreMapper.toStore(savedStoreEntity);
    }

    @Override
    @Cacheable(value = "storeById", key = "#storeId")
    public Store getStoreById(String storeId) {
        final StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + storeId));
        return StoreEntityToStoreMapper.toStore(storeEntity);
    }

    @Override
    @Cacheable(value = "stores", key = "#request.paging.pageNumber + '-' + #request.paging.pageSize")
    public CustomPage<Store> getAllStores(final StorePagingRequest request) {

        final Page<StoreEntity> storeEntityPage = storeRepository
                .findAll(request.toPageable());

        if (storeEntityPage.getContent().isEmpty()) {
            throw new StoreNotFoundException("No stores found.");
        }

        final List<Store> stores = ListStoreEntityToListStoreMapper
                .toStoreList(storeEntityPage.getContent());

        return CustomPage.of(stores, storeEntityPage);
    }

    @Override
    public CustomPage<Store> getStoreByAdmin(final StoreByAdminPagingRequest request) {
        final Page<StoreEntity> storeEntityPage = this.storeRepository
                .findAllByStoreAdminId(
                        userReadService.getCurrentUser().getId(),
                        request.toPageable()
                );
        if (storeEntityPage.getContent().isEmpty()) {
            throw new StoreNotFoundException("No stores found for the current admin.");
        }
        final List<Store> stores = ListStoreEntityToListStoreMapper
                .toStoreList(storeEntityPage.getContent());


        return CustomPage.of(stores, storeEntityPage);

    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "stores", allEntries = true)
    }, put = {
            @CachePut(value = "storeById", key = "#storeId")
    })
    public Store updateStore(String storeId, StoreUpdateRequest request) {

        final StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + storeId));

        StoreUpdateRequestToStoreEntityMapper.toStoreEntity(storeEntity, request);
        final StoreEntity updatedStoreEntity = storeRepository.save(storeEntity);

        return StoreEntityToStoreMapper.toStore(updatedStoreEntity);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "stores", allEntries = true),
            @CacheEvict(value = "storeById", key = "#storeId")
    })
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
    @Caching(evict = {
            @CacheEvict(value = "stores", allEntries = true)
    }, put = {
            @CachePut(value = "storeById", key = "#storeId")
    })
    public Store changeStatus(String storeId, String status) {
        final StoreEntity storeEntity = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreNotFoundException("Store not found with id: " + storeId));
        storeEntity.setStatus(Enum.valueOf(StoreStatus.class, status));
        final StoreEntity updatedStoreEntity = storeRepository.save(storeEntity);
        return StoreEntityToStoreMapper.toStore(updatedStoreEntity);
    }
}
