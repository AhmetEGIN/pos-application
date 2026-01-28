package com.egin.order.service.impl;

import com.egin.branch.model.Branch;
import com.egin.branch.service.BranchService;
import com.egin.common.model.CustomPage;
import com.egin.order.exception.OrderNotFoundException;
import com.egin.order.model.Order;
import com.egin.order.model.dto.request.OrderCreateRequest;
import com.egin.order.model.dto.request.OrderItemCreateRequest;
import com.egin.order.model.dto.request.OrderPagingRequest;
import com.egin.order.model.entity.OrderEntity;
import com.egin.order.model.entity.OrderItemEntity;
import com.egin.order.model.enums.PaymentType;
import com.egin.order.model.mapper.ListOrderEntityToListOrderMapper;
import com.egin.order.model.mapper.OrderCreateRequestToOrderEntityMapper;
import com.egin.order.model.mapper.OrderEntityToOrderMapper;
import com.egin.order.repository.OrderRepository;
import com.egin.order.service.OrderService;
import com.egin.product.model.Product;
import com.egin.product.model.entity.ProductEntity;
import com.egin.product.service.product.ProductReadService;
import com.egin.user.model.Customer;
import com.egin.user.model.User;
import com.egin.user.service.customer.CustomerService;
import com.egin.user.service.user.UserReadService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final BranchService branchService;
    private final UserReadService userReadService;
    private final CustomerService customerService;
    private final ProductReadService productReadService;


    public OrderServiceImpl(
            OrderRepository orderRepository,
            BranchService branchService,
            UserReadService userReadService,
            CustomerService customerService,
            ProductReadService productReadService
    ) {
        this.orderRepository = orderRepository;
        this.branchService = branchService;
        this.userReadService = userReadService;
        this.customerService = customerService;
        this.productReadService = productReadService;
    }


    @Override
    @Transactional
    public Order createOrder(final OrderCreateRequest request) {
        // Get branch
        final Branch branch = branchService.getBranchById(request.getBranchId());

        // Get current user (cashier) from context
        final User cashier = userReadService.getCurrentUser();

        // Get customer
        final Customer customer = customerService.getCustomerById(request.getCustomerId());

        // Create order entity
        OrderEntity orderEntity = OrderCreateRequestToOrderEntityMapper
                .toOrderEntity(request, branch, cashier, customer);

        // Calculate total and create order items
        double totalAmount = 0.0;
        List<OrderItemEntity> orderItems = new ArrayList<>();

        for (OrderItemCreateRequest itemRequest : request.getOrderItems()) {
            Product product = productReadService.getProductById(itemRequest.getProductId());

            OrderItemEntity orderItem = OrderItemEntity.builder()
                    .productEntity(ProductEntity.builder().id(product.getId()).build())
                    .quantity(itemRequest.getQuantity())
                    .price(itemRequest.getPrice())
                    .orderEntity(orderEntity)
                    .build();

            orderItems.add(orderItem);
            totalAmount += itemRequest.getPrice() * itemRequest.getQuantity();
        }

        orderEntity.setOrderItems(orderItems);
        orderEntity.setTotalAmount(totalAmount);

        // Save order
        OrderEntity savedOrder = orderRepository.save(orderEntity);

        return OrderEntityToOrderMapper.toOrder(savedOrder);
    }

    @Override
    public Order getOrderById(final String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        return OrderEntityToOrderMapper.toOrder(orderEntity);
    }

    @Override
    public CustomPage<Order> getOrdersByBranchId(final String branchId, final OrderPagingRequest pagingRequest) {

        Page<OrderEntity> orderEntitiesPage = orderRepository.findByBranchEntityId(branchId, pagingRequest.toPageable());

        List<Order> orders = ListOrderEntityToListOrderMapper.toOrderList(orderEntitiesPage.getContent());

        return CustomPage.of(orders, orderEntitiesPage);
    }

    @Override
    public CustomPage<Order> getOrdersByCashierId(final String cashierId, final OrderPagingRequest pagingRequest) {

        Page<OrderEntity> orderEntitiesPage = orderRepository.findByCashierId(cashierId, pagingRequest.toPageable());

        List<Order> orders = ListOrderEntityToListOrderMapper.toOrderList(orderEntitiesPage.getContent());

        return CustomPage.of(orders, orderEntitiesPage);
    }

    @Override
    @Transactional
    public void deleteOrderById(final String orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId));

        orderRepository.delete(orderEntity);
    }

    @Override
    public CustomPage<Order> getTodayOrdersByBranchId(final String branchId, final OrderPagingRequest pagingRequest) {
        LocalDateTime startOfDay = LocalDate.now().atStartOfDay();
        LocalDateTime endOfDay = LocalDate.now().atTime(LocalTime.MAX);


        Page<OrderEntity> orderEntitiesPage = orderRepository.findTodayOrdersByBranchId(
                branchId,
                startOfDay,
                endOfDay,
                pagingRequest.toPageable()
        );

        List<Order> orders = ListOrderEntityToListOrderMapper.toOrderList(orderEntitiesPage.getContent());

        return CustomPage.of(orders, orderEntitiesPage);
    }

    @Override
    public CustomPage<Order> getOrdersByCustomerId(final String customerId, final OrderPagingRequest pagingRequest) {

        Page<OrderEntity> orderEntitiesPage = orderRepository.findByCustomerEntityId(customerId, pagingRequest.toPageable());

        List<Order> orders = ListOrderEntityToListOrderMapper.toOrderList(orderEntitiesPage.getContent());

        return CustomPage.of(orders, orderEntitiesPage);
    }

    @Override
    public CustomPage<Order> getTop5RecentOrdersByBranchId(final String branchId) {
        Pageable pageable = PageRequest.of(0, 5);

        Page<OrderEntity> orderEntitiesPage = orderRepository.findTop5RecentOrdersByBranchId(branchId, pageable);

        List<Order> orders = ListOrderEntityToListOrderMapper.toOrderList(orderEntitiesPage.getContent());

        return CustomPage.of(orders, orderEntitiesPage);
    }

    @Override
    public Double calculateTotalSalesByCashierAndDateRange(final String cashierId, final LocalDateTime startDate, final LocalDateTime endDate) {
        return orderRepository.calculateTotalSalesByCashierAndDateRange(cashierId, startDate, endDate);
    }

    @Override
    public Long countOrdersByCashierAndDateRange(final String cashierId, final LocalDateTime startDate, final LocalDateTime endDate) {
        return orderRepository.countOrdersByCashierAndDateRange(cashierId, startDate, endDate);
    }

    @Override
    public List<Order> getRecentOrdersByCashierAndDateRange(final String cashierId, final LocalDateTime startDate, final LocalDateTime endDate, final int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "createdAt"));
        List<OrderEntity> orderEntities = orderRepository.findRecentOrdersByCashierAndDateRange(
                cashierId, startDate, endDate, pageable
        );
        return ListOrderEntityToListOrderMapper.toOrderList(orderEntities);
    }

    @Override
    public Map<PaymentType, Double> getPaymentSummaryByCashierAndDateRange(final String cashierId, final LocalDateTime startDate, final LocalDateTime endDate) {
        List<Object[]> results = orderRepository.getPaymentSummaryByCashierAndDateRange(cashierId, startDate, endDate);

        Map<PaymentType, Double> paymentSummary = new HashMap<>();
        for (Object[] result : results) {
            PaymentType paymentType = (PaymentType) result[0];
            Double totalAmount = (Double) result[1];
            paymentSummary.put(paymentType, totalAmount);
        }

        return paymentSummary;
    }

}

