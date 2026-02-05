package com.egin.store.service;

import com.egin.common.model.CustomPage;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.store.model.Store;
import com.egin.store.model.dto.request.StoreByAdminPagingRequest;
import com.egin.store.model.dto.request.StoreCreateRequest;
import com.egin.store.model.dto.request.StorePagingRequest;
import com.egin.store.model.dto.request.StoreUpdateRequest;
import com.egin.store.model.enums.StoreStatus;
import com.egin.user.model.User;

import java.util.List;

public interface StoreService {

    Store createStore(StoreCreateRequest request);
    Store getStoreById(String storeId);
    CustomPage<Store> getAllStores(final StorePagingRequest request);
    CustomPage<Store> getStoreByAdmin(final StoreByAdminPagingRequest request);
    Store updateStore(String storeId, StoreUpdateRequest request);
    void deleteStore(String storeId);
    Store getStoreByEmployee();
    Store changeStatus(String storeId, String status);

}
