package com.egin.branch.service;

import com.egin.branch.model.Branch;
import com.egin.branch.model.request.BranchByStorePagingRequest;
import com.egin.branch.model.request.BranchCreateRequest;
import com.egin.branch.model.request.BranchUpdateRequest;
import com.egin.common.model.CustomPage;

public interface BranchService {

    Branch createBranch(final BranchCreateRequest branchCreateRequest);
    Branch updateBranch(final String branchId, final BranchUpdateRequest branchUpdateRequest);
    void deleteBranch(final String branchId);
    CustomPage<Branch> getBranchesByStoreId(final String storeId, final BranchByStorePagingRequest branchPagingRequest);
    Branch getBranchById(final String branchId);




}
