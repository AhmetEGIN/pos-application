package com.egin.refund.service.impl;

import com.egin.branch.model.Branch;
import com.egin.branch.service.BranchService;
import com.egin.common.model.CustomPage;
import com.egin.order.model.Order;
import com.egin.order.service.OrderService;
import com.egin.refund.exception.RefundNotFoundException;
import com.egin.refund.model.Refund;
import com.egin.refund.model.dto.request.RefundByCashierAndDateRangePagingRequest;
import com.egin.refund.model.dto.request.RefundCreateRequest;
import com.egin.refund.model.dto.request.RefundPagingRequest;
import com.egin.refund.model.entity.RefundEntity;
import com.egin.refund.model.mapper.ListRefundEntityToListRefundMapper;
import com.egin.refund.model.mapper.RefundCreateRequestToRefundEntityMapper;
import com.egin.refund.model.mapper.RefundEntityToRefundMapper;
import com.egin.refund.repository.RefundRepository;
import com.egin.refund.service.RefundService;
import com.egin.user.model.User;
import com.egin.user.service.user.UserReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefundServiceImpl implements RefundService {

    private final RefundRepository refundRepository;
    private final OrderService orderService;
    private final BranchService branchService;
    private final UserReadService userReadService;
    // private final ShiftReportService shiftReportService;

    @Override
    @Transactional
    public Refund createRefund(final RefundCreateRequest request) {
        // Get order
        final Order order = orderService.getOrderById(request.getOrderId());

        // Get branch
        final Branch branch = branchService.getBranchById(request.getBranchId());

        // Get current user (cashier) from context
        final User cashier = userReadService.getCurrentUser();

        // Get shift report (commented for now)
        // final ShiftReport shiftReport = shiftReportService.getShiftReportById(request.getShiftReportId());

        // Create refund entity
        RefundEntity refundEntity = RefundCreateRequestToRefundEntityMapper.toRefundEntity(request, order, branch, cashier);
        // Save refund
        RefundEntity savedRefund = refundRepository.save(refundEntity);

        return RefundEntityToRefundMapper.toRefund(savedRefund);
    }

    @Override
    public CustomPage<Refund> getAllRefunds(final RefundPagingRequest pagingRequest) {
        Page<RefundEntity> refundEntitiesPage = refundRepository.findAll(pagingRequest.toPageable());

        List<Refund> refunds = ListRefundEntityToListRefundMapper.toRefundList(refundEntitiesPage.getContent());

        return CustomPage.of(refunds, refundEntitiesPage);
    }

    @Override
    public Refund getRefundByCashierId(final String cashierId) {
        RefundEntity refundEntity = refundRepository.findFirstByCashierId(cashierId);

        if (refundEntity == null) {
            throw new RefundNotFoundException("Refund not found for cashier id: " + cashierId);
        }

        return RefundEntityToRefundMapper.toRefund(refundEntity);
    }

    @Override
    public Refund getRefundById(final String refundId) {
        RefundEntity refundEntity = refundRepository.findById(refundId)
                .orElseThrow(() -> new RefundNotFoundException("Refund not found with id: " + refundId));

        return RefundEntityToRefundMapper.toRefund(refundEntity);
    }

    @Override
    public CustomPage<Refund> getRefundByBranchId(final String branchId, final RefundPagingRequest pagingRequest) {
        Page<RefundEntity> refundEntitiesPage = refundRepository.findByBranchEntityId(branchId, pagingRequest.toPageable());

        List<Refund> refunds = ListRefundEntityToListRefundMapper.toRefundList(refundEntitiesPage.getContent());

        return CustomPage.of(refunds, refundEntitiesPage);
    }

    @Override
    public CustomPage<Refund> getRefundByCashierAndDateRange(final RefundByCashierAndDateRangePagingRequest pagingRequest) {
        Page<RefundEntity> refundEntitiesPage = refundRepository.findByCashierIdAndDateRange(
                pagingRequest.getCashierId(),
                pagingRequest.getStartDate(),
                pagingRequest.getEndDate(),
                pagingRequest.toPageable()
        );

        List<Refund> refunds = ListRefundEntityToListRefundMapper.toRefundList(refundEntitiesPage.getContent());

        return CustomPage.of(refunds, refundEntitiesPage);
    }

    @Override
    @Transactional
    public void deleteRefund(final String refundId) {
        RefundEntity refundEntity = refundRepository.findById(refundId)
                .orElseThrow(() -> new RefundNotFoundException("Refund not found with id: " + refundId));

        refundRepository.delete(refundEntity);
    }

}

