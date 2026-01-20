package com.egin.branch.model.mapper;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.branch.model.request.BranchCreateRequest;
import com.egin.store.model.Store;
import com.egin.store.model.mapper.StoreToStoreEntityMapper;
import com.egin.user.model.User;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BranchCreateRequestToBranchEntityMapper {

    public BranchEntity toBranchEntity(
            final BranchCreateRequest branchCreateRequest,
            final Store store,
            final User user
            ) {

        return BranchEntity.builder()
                .name(branchCreateRequest.getName())
                .address(branchCreateRequest.getAddress())
                .email(branchCreateRequest.getEmail())
                .workingDays(branchCreateRequest.getWorkingDays())
                .openTime(branchCreateRequest.getOpenTime())
                .closeTime(branchCreateRequest.getCloseTime())
                .storeEntity(StoreToStoreEntityMapper.toStoreEntity(store))
                .manager(UserToUserEntityMapper.toUserEntity(user))
                .build();
    }


}
