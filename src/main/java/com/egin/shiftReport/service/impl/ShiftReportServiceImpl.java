package com.egin.shiftReport.service.impl;

import com.egin.branch.model.Branch;
import com.egin.branch.service.BranchService;
import com.egin.common.model.CustomPage;
import com.egin.shiftReport.exception.ShiftAlreadyActiveException;
import com.egin.shiftReport.exception.ShiftReportNotFoundException;
import com.egin.shiftReport.model.ShiftReport;
import com.egin.shiftReport.model.dto.request.ShiftReportByCashierAndDateRequest;
import com.egin.shiftReport.model.dto.request.ShiftReportPagingRequest;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.model.mapper.ListShiftReportEntityToListShiftReportMapper;
import com.egin.shiftReport.model.mapper.ShiftReportEntityToShiftReportMapper;
import com.egin.shiftReport.repository.ShiftReportRepository;
import com.egin.shiftReport.service.ShiftReportService;
import com.egin.shiftReport.service.impl.calculation.ShiftReportCalculator;
import com.egin.user.model.User;
import com.egin.user.service.user.UserReadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class ShiftReportServiceImpl implements ShiftReportService {

    private final ShiftReportRepository shiftReportRepository;
    private final ShiftReportCalculator shiftReportCalculator;
    private final BranchService branchService;
    private final UserReadService userReadService;

    public ShiftReportServiceImpl(
            ShiftReportRepository shiftReportRepository,
            ShiftReportCalculator shiftReportCalculator,
            BranchService branchService,
            UserReadService userReadService
    ) {
        this.shiftReportRepository = shiftReportRepository;
        this.shiftReportCalculator = shiftReportCalculator;
        this.branchService = branchService;
        this.userReadService = userReadService;
    }

    @Override
    @Transactional
    public ShiftReport startShift(final String branchId, final String cashierId, final LocalDateTime shiftStartTime) {
        log.info("Starting shift for cashier: {} at branch: {}", cashierId, branchId);

        validateNoActiveShift(cashierId);

        Branch branch = branchService.getBranchById(branchId);
        User cashier = userReadService.getUserById(cashierId);

        ShiftReportEntity shiftReportEntity = ShiftReportEntityFactory.createForNewShift(branch, cashier, shiftStartTime);
        ShiftReportEntity savedEntity = shiftReportRepository.save(shiftReportEntity);

        log.info("Shift started successfully. ShiftReportId: {}", savedEntity.getId());
        return ShiftReportEntityToShiftReportMapper.toShiftReport(savedEntity);
    }

    @Override
    @Transactional
    public ShiftReport endShift(final String shiftReportId, final LocalDateTime shiftEndTime) {
        log.info("Ending shift: {}", shiftReportId);

        ShiftReportEntity shiftReportEntity = findShiftReportEntityById(shiftReportId);
        LocalDateTime endTime = resolveEndTime(shiftEndTime);

        shiftReportEntity.setShiftEnd(endTime);

        calculateShiftMetrics(shiftReportEntity, endTime);

        ShiftReportEntity updatedEntity = shiftReportRepository.save(shiftReportEntity);

        log.info("Shift ended successfully. ShiftReportId: {}, NetSales: {}",
                updatedEntity.getId(), updatedEntity.getNetSales());
        return ShiftReportEntityToShiftReportMapper.toShiftReport(updatedEntity);
    }

    @Override
    public ShiftReport getShiftReportById(final String shiftId) {
        log.debug("Fetching shift report by id: {}", shiftId);
        ShiftReportEntity shiftReportEntity = findShiftReportEntityById(shiftId);
        return ShiftReportEntityToShiftReportMapper.toShiftReport(shiftReportEntity);
    }

    @Override
    public CustomPage<ShiftReport> getAllShiftReports(final ShiftReportPagingRequest pagingRequest) {
        log.debug("Fetching all shift reports with pagination");
        Page<ShiftReportEntity> page = shiftReportRepository.findAll(pagingRequest.toPageable());
        return toCustomPage(page);
    }

    @Override
    public CustomPage<ShiftReport> getShiftReportsByBranchId(final String branchId, final ShiftReportPagingRequest pagingRequest) {
        log.debug("Fetching shift reports for branch: {}", branchId);
        Page<ShiftReportEntity> page = shiftReportRepository.findByBranchEntityId(branchId, pagingRequest.toPageable());
        return toCustomPage(page);
    }

    @Override
    public CustomPage<ShiftReport> getShiftReportsByCashierId(final String cashierId, final ShiftReportPagingRequest pagingRequest) {
        log.debug("Fetching shift reports for cashier: {}", cashierId);
        Page<ShiftReportEntity> page = shiftReportRepository.findByCashierId(cashierId, pagingRequest.toPageable());
        return toCustomPage(page);
    }

    @Override
    public ShiftReport getCurrentShiftProgress(final String cashierId) {
        log.debug("Fetching current shift progress for cashier: {}", cashierId);
        ShiftReportEntity shiftReportEntity = shiftReportRepository.findCurrentShiftByCashierId(cashierId)
                .orElseThrow(() -> new ShiftReportNotFoundException(
                        String.format("No active shift found for cashier id: %s", cashierId)));
        return ShiftReportEntityToShiftReportMapper.toShiftReport(shiftReportEntity);
    }

    @Override
    public ShiftReport getShiftByCashierAndDate(final String cashierId, final ShiftReportByCashierAndDateRequest request) {
        log.debug("Fetching shift report for cashier: {} between {} and {}",
                cashierId, request.getStartDate(), request.getEndDate());

        ShiftReportEntity shiftReportEntity = shiftReportRepository.findByCashierIdAndDateRange(
                        cashierId, request.getStartDate(), request.getEndDate())
                .orElseThrow(() -> new ShiftReportNotFoundException(
                        String.format("Shift report not found for cashier id: %s in the specified date range", cashierId)));

        return ShiftReportEntityToShiftReportMapper.toShiftReport(shiftReportEntity);
    }

    // ==================== Private Helper Methods ====================

    private void validateNoActiveShift(String cashierId) {
        shiftReportRepository.findCurrentShiftByCashierId(cashierId)
                .ifPresent(shift -> {
                    throw new ShiftAlreadyActiveException(
                            String.format("Cashier %s already has an active shift", cashierId));
                });
    }

    private ShiftReportEntity findShiftReportEntityById(String shiftReportId) {
        return shiftReportRepository.findById(shiftReportId)
                .orElseThrow(() -> new ShiftReportNotFoundException(
                        String.format("Shift report not found with id: %s", shiftReportId)));
    }

    private LocalDateTime resolveEndTime(LocalDateTime shiftEndTime) {
        return shiftEndTime != null ? shiftEndTime : LocalDateTime.now();
    }

    private void calculateShiftMetrics(ShiftReportEntity shiftReportEntity, LocalDateTime endTime) {
        String cashierId = shiftReportEntity.getCashier().getId();
        LocalDateTime shiftStart = shiftReportEntity.getShiftStart();

        shiftReportCalculator.calculateAll(shiftReportEntity, cashierId, shiftStart, endTime);
    }

    private CustomPage<ShiftReport> toCustomPage(Page<ShiftReportEntity> page) {
        List<ShiftReport> shiftReports = ListShiftReportEntityToListShiftReportMapper.toShiftReportList(page.getContent());
        return CustomPage.of(shiftReports, page);
    }

}
