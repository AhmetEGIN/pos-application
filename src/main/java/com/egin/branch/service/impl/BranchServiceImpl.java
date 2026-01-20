package com.egin.branch.service.impl;

import com.egin.branch.exception.BranchEntityNotFoundException;
import com.egin.branch.model.Branch;
import com.egin.branch.model.entity.BranchEntity;
import com.egin.branch.model.mapper.BranchCreateRequestToBranchEntityMapper;
import com.egin.branch.model.mapper.BranchEntityToBranchMapper;
import com.egin.branch.model.mapper.BranchUpdateRequestToBranchEntityMapper;
import com.egin.branch.model.mapper.ListBranchEntityToListBranchMapper;
import com.egin.branch.model.request.BranchByStorePagingRequest;
import com.egin.branch.model.request.BranchCreateRequest;
import com.egin.branch.model.request.BranchUpdateRequest;
import com.egin.branch.repository.BranchRepository;
import com.egin.branch.service.BranchService;
import com.egin.common.model.CustomPage;
import com.egin.store.model.Store;
import com.egin.store.service.StoreService;
import com.egin.user.model.User;
import com.egin.user.service.UserReadService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreService storeService;
    private final UserReadService userReadService;

    public BranchServiceImpl(
            BranchRepository branchRepository,
            StoreService storeService,
            UserReadService userReadService
    ) {
        this.branchRepository = branchRepository;
        this.storeService = storeService;
        this.userReadService = userReadService;
    }

    @Override
    public Branch createBranch(final BranchCreateRequest branchCreateRequest) {

        final Store store = this.storeService.
                getStoreById(branchCreateRequest.getStoreEntityId());

        final User user = this.userReadService
                .getUserById(branchCreateRequest.getUserEntityId());

        final BranchEntity branchEntity = BranchCreateRequestToBranchEntityMapper
                .toBranchEntity(
                        branchCreateRequest,
                        store,
                        user
                );

        final BranchEntity savedBranchEntity = branchRepository.save(branchEntity);

        return BranchEntityToBranchMapper.toBranch(savedBranchEntity);
    }

    @Override
    public Branch updateBranch(final String branchId, final BranchUpdateRequest branchUpdateRequest) {

        final BranchEntity branchEntityToBeUpdate = this.branchRepository
                .findById(branchId)
                .orElseThrow(BranchEntityNotFoundException::new);

        BranchUpdateRequestToBranchEntityMapper.toBranchEntity(
                branchUpdateRequest,
                branchEntityToBeUpdate
        );

        final BranchEntity updatedBranchEntity = this.branchRepository.save(branchEntityToBeUpdate);

        return BranchEntityToBranchMapper.toBranch(updatedBranchEntity);
    }

    @Override
    public void deleteBranch(final String branchId) {

        final BranchEntity branchEntityToBeDelete = this.branchRepository
                .findById(branchId)
                .orElseThrow(BranchEntityNotFoundException::new);

        this.branchRepository.delete(branchEntityToBeDelete);

    }

    @Override
    public CustomPage<Branch> getBranchesByStoreId(final String storeId, final BranchByStorePagingRequest branchPagingRequest) {


        final Page<BranchEntity> branchEntityPage = this.branchRepository
                .findAllByStoreEntityId(
                        storeId,
                        branchPagingRequest.toPageable()
                );
        if (branchEntityPage.getContent().isEmpty()) {
            throw new BranchEntityNotFoundException();
        }
        final List<Branch> branches = ListBranchEntityToListBranchMapper
                .toListBranch(branchEntityPage.getContent());

        return CustomPage.of(branches, branchEntityPage);
    }

    @Override
    public Branch getBranchById(final String branchId) {

        final BranchEntity branchEntity = this.branchRepository
                .findById(branchId)
                .orElseThrow(BranchEntityNotFoundException::new);

        return BranchEntityToBranchMapper.toBranch(branchEntity);

    }
}
