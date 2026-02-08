package com.egin.branch.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.entity.BranchEntity;
import com.egin.store.model.mapper.StoreEntityToStoreMapper;
import com.egin.user.model.mapper.UserEntityToUserMapper;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class BranchEntityToBranchMapper {

    public Branch toBranch(final BranchEntity branchEntity) {
        // Lazy proxy'den kurtulmak için koleksiyonu somut bir listeye kopyala
        List<String> workingDays = branchEntity.getWorkingDays() != null
            ? new ArrayList<>(branchEntity.getWorkingDays())
            : new ArrayList<>();
        System.out.println("BranchEntityToBranchMapper: workingDays = " + workingDays);
        return Branch.builder()
                .id(branchEntity.getId())
                .name(branchEntity.getName())
                .address(branchEntity.getAddress())
                .phone(branchEntity.getPhone())
                .email(branchEntity.getEmail())
                .workingDays(workingDays)
                .openTime(branchEntity.getOpenTime())
                .closeTime(branchEntity.getCloseTime())
//                .store(branchEntity.getStoreEntity() != null
//                    ? StoreEntityToStoreMapper.toStore(branchEntity.getStoreEntity())
//                    : null)
                .manager(branchEntity.getManager() != null
                    ? UserEntityToUserMapper.toUser(branchEntity.getManager())
                    : null)
                .build();
    }


}
