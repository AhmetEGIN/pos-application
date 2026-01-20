package com.egin.branch.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.entity.BranchEntity;
import com.egin.store.model.mapper.StoreEntityToStoreMapper;
import com.egin.user.model.mapper.UserEntityToUserMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BranchEntityToBranchMapper {

    public Branch toBranch(final BranchEntity branchEntity) {
        return Branch.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .address(branchEntity.getAddress())
                .phone(branchEntity.getPhone())
                .email(branchEntity.getEmail())
                .workingDays(branchEntity.getWorkingDays())
                .openTime(branchEntity.getOpenTime())
                .closeTime(branchEntity.getCloseTime())
                .store(StoreEntityToStoreMapper.toStore(branchEntity.getStoreEntity()))
                .manager(UserEntityToUserMapper.toUser(branchEntity.getManager()))
                .build();
    }


}
