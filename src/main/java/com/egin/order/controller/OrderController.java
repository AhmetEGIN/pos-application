package com.egin.order.controller;

import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.order.model.Order;
import com.egin.order.model.dto.request.OrderCreateRequest;
import com.egin.order.model.dto.request.OrderPagingRequest;
import com.egin.order.model.mapper.OrderCustomPageToCustomPagingResponseMapper;
import com.egin.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<Order> createOrder(
            @RequestBody @Valid final OrderCreateRequest request
    ) {
        final Order order = orderService.createOrder(request);
        return CustomResponse.successOf(order);
    }

    @GetMapping("/{order-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<Order> getOrderById(
            @PathVariable(value = "order-id") final String orderId
    ) {
        final Order order = orderService.getOrderById(orderId);
        return CustomResponse.successOf(order);
    }

    @GetMapping("/branch/{branch-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Order>> getOrdersByBranchId(
            @PathVariable(value = "branch-id") final String branchId,
            @RequestBody @Valid final OrderPagingRequest request
    ) {
        final CustomPagingResponse<Order> orders = OrderCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        orderService.getOrdersByBranchId(branchId, request)
                );
        return CustomResponse.successOf(orders);
    }

    @GetMapping("/branch/{branch-id}/today")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Order>> getTodayOrdersByBranchId(
            @PathVariable(value = "branch-id") final String branchId,
            @RequestBody @Valid final OrderPagingRequest request
    ) {
        final CustomPagingResponse<Order> orders = OrderCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        orderService.getTodayOrdersByBranchId(branchId, request)
                );
        return CustomResponse.successOf(orders);
    }

    @GetMapping("/branch/{branch-id}/recent")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Order>> getTop5RecentOrdersByBranchId(
            @PathVariable(value = "branch-id") final String branchId
    ) {
        final CustomPagingResponse<Order> orders = OrderCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        orderService.getTop5RecentOrdersByBranchId(branchId)
                );
        return CustomResponse.successOf(orders);
    }

    @GetMapping("/cashier/{cashier-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Order>> getOrdersByCashierId(
            @PathVariable(value = "cashier-id") final String cashierId,
            @RequestBody @Valid final OrderPagingRequest request
    ) {
        final CustomPagingResponse<Order> orders = OrderCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        orderService.getOrdersByCashierId(cashierId, request)
                );
        return CustomResponse.successOf(orders);
    }

    @GetMapping("/customer/{customer-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Order>> getOrdersByCustomerId(
            @PathVariable(value = "customer-id") final String customerId,
            @RequestBody @Valid final OrderPagingRequest request
    ) {
        final CustomPagingResponse<Order> orders = OrderCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        orderService.getOrdersByCustomerId(customerId, request)
                );
        return CustomResponse.successOf(orders);
    }

    @DeleteMapping("/{order-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Void> deleteOrder(
            @PathVariable(value = "order-id") final String orderId
    ) {
        orderService.deleteOrderById(orderId);
        return CustomResponse.SUCCESS;
    }

}

