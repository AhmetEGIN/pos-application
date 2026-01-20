package com.egin.branch.controller;

import com.egin.branch.model.Branch;
import com.egin.branch.model.mapper.BranchCustomPageToCustomPagingResponseMapper;
import com.egin.branch.model.request.BranchByStorePagingRequest;
import com.egin.branch.model.request.BranchCreateRequest;
import com.egin.branch.model.request.BranchUpdateRequest;
import com.egin.branch.service.BranchService;
import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/branches")
public class BranchController {

    private final BranchService branchService;


    public BranchController(
            BranchService branchService
    ) {
        this.branchService = branchService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Branch> createBranch(
            @RequestBody @Valid final BranchCreateRequest request
            ) {

        final Branch branch = branchService.createBranch(request);

        return CustomResponse.successOf(branch);
    }

    @PutMapping("/{branch-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Branch> updateBranch(
            @PathVariable("branch-id") final String branchId,
            @RequestBody @Valid final BranchUpdateRequest request
    ) {
        final Branch branch = branchService.updateBranch(branchId, request);

        return CustomResponse.successOf(branch);
    }


    @DeleteMapping("/{branch-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Void> deleteBranch(
            @PathVariable("branch-id") final String branchId
    ) {
        branchService.deleteBranch(branchId);
        return CustomResponse.SUCCESS;
    }

    @GetMapping("/{branch-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<Branch> getBranchById(
            @PathVariable("branch-id") final String branchId
    ) {
        final Branch branch = branchService.getBranchById(branchId);

        return CustomResponse.successOf(branch);
    }

    @GetMapping("/store/{store-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Branch>> getBranchesByStoreId(
            @PathVariable("store-id") final String storeId,
            @RequestBody @Valid final BranchByStorePagingRequest request
    ) {
        final CustomPagingResponse<Branch> branches = BranchCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        branchService.getBranchesByStoreId(storeId, request)
                );

        return CustomResponse.successOf(branches);
    }

}
