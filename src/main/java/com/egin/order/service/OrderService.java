package com.egin.order.service;

import com.egin.common.model.CustomPage;
import com.egin.order.model.Order;
import com.egin.order.model.dto.request.OrderCreateRequest;
import com.egin.order.model.dto.request.OrderPagingRequest;
import com.egin.order.model.enums.PaymentType;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface OrderService {

    Order createOrder(final OrderCreateRequest order);
    Order getOrderById(final String orderId);
    CustomPage<Order> getOrdersByBranchId(final String branchId, final OrderPagingRequest pagingRequest);
    CustomPage<Order> getOrdersByCashierId(final String cashierId, final OrderPagingRequest pagingRequest);
    void deleteOrderById(final String orderId);
    CustomPage<Order> getTodayOrdersByBranchId(final String branchId, final OrderPagingRequest pagingRequest);
    CustomPage<Order> getOrdersByCustomerId(final String customerId, final OrderPagingRequest pagingRequest);
    CustomPage<Order> getTop5RecentOrdersByBranchId(final String branchId);

    // Shift report calculations
    Double calculateTotalSalesByCashierAndDateRange(String cashierId, LocalDateTime startDate, LocalDateTime endDate);
    Long countOrdersByCashierAndDateRange(String cashierId, LocalDateTime startDate, LocalDateTime endDate);

    // New methods for shift report details
    List<Order> getRecentOrdersByCashierAndDateRange(String cashierId, LocalDateTime startDate, LocalDateTime endDate, int limit);
    Map<PaymentType, Double> getPaymentSummaryByCashierAndDateRange(String cashierId, LocalDateTime startDate, LocalDateTime endDate);

}
