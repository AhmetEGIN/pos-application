package com.egin.branch.model.mapper;

import com.egin.branch.model.Branch;
import com.egin.branch.model.entity.BranchEntity;
import lombok.experimental.UtilityClass;

import java.util.List;

@UtilityClass
public class ListBranchEntityToListBranchMapper {


    public List<Branch> toListBranch(List<BranchEntity> content) {
        return content.stream()
                .map(BranchEntityToBranchMapper::toBranch)
                .toList();
    }
}
