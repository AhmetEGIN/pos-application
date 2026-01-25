package com.egin.branch.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.entity.BranchEntity;
import com.egin.store.model.mapper.StoreToStoreEntityMapper;
import com.egin.user.model.mapper.UserToUserEntityMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BranchToBranchEntityMapper {
    public BranchEntity toBranchEntity(Branch branch) {
        return BranchEntity.builder()
                .id(branch.getId())
                .name(branch.getName())
                .address(branch.getAddress())
                .phone(branch.getPhone())
                .email(branch.getEmail())
                .workingDays(branch.getWorkingDays())
                .openTime(branch.getOpenTime())
                .closeTime(branch.getCloseTime())
                .storeEntity(StoreToStoreEntityMapper.toStoreEntity(branch.getStore()))
                .manager(UserToUserEntityMapper.toUserEntity(branch.getManager()))
                .build();

    }
}
