package com.egin.order.service;

import com.egin.common.model.CustomPage;
import com.egin.order.model.Order;
import com.egin.order.model.dto.request.OrderCreateRequest;
import com.egin.order.model.dto.request.OrderPagingRequest;

public interface OrderService {

    Order createOrder(final OrderCreateRequest order);
    Order getOrderById(final String orderId);
    CustomPage<Order> getOrdersByBranchId(final String branchId, final OrderPagingRequest pagingRequest);
    CustomPage<Order> getOrdersByCashierId(final String cashierId, final OrderPagingRequest pagingRequest);
    void deleteOrderById(final String orderId);
    CustomPage<Order> getTodayOrdersByBranchId(final String branchId, final OrderPagingRequest pagingRequest);
    CustomPage<Order> getOrdersByCustomerId(final String customerId, final OrderPagingRequest pagingRequest);
    CustomPage<Order> getTop5RecentOrdersByBranchId(final String branchId);



}
