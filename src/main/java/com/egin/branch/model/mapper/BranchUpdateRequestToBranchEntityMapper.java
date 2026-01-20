package com.egin.branch.model.mapper;

import com.egin.branch.model.entity.BranchEntity;
import com.egin.branch.model.request.BranchUpdateRequest;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class BranchUpdateRequestToBranchEntityMapper {


    public void toBranchEntity(
            final BranchUpdateRequest branchUpdateRequest,
            final BranchEntity branchEntityToBeUpdate) {

        Optional.ofNullable(branchUpdateRequest.getName())
                .ifPresent(branchEntityToBeUpdate::setName);

        Optional.ofNullable(branchUpdateRequest.getAddress())
                .ifPresent(branchEntityToBeUpdate::setAddress);

        Optional.ofNullable(branchUpdateRequest.getPhone())
                .ifPresent(branchEntityToBeUpdate::setPhone);

        Optional.ofNullable(branchUpdateRequest.getEmail())
                .ifPresent(branchEntityToBeUpdate::setEmail);

        Optional.ofNullable(branchUpdateRequest.getWorkingDays())
                .ifPresent(branchEntityToBeUpdate::setWorkingDays);

        Optional.ofNullable(branchUpdateRequest.getOpenTime())
                .ifPresent(branchEntityToBeUpdate::setOpenTime);

        Optional.ofNullable(branchUpdateRequest.getCloseTime())
                .ifPresent(branchEntityToBeUpdate::setCloseTime);

    }

}
