package com.egin.common.validation;

import com.egin.inventory.model.entity.InventoryEntity;
import com.egin.inventory.repository.InventoryRepository;
import com.egin.order.model.dto.request.OrderCreateRequest;
import com.egin.order.model.dto.request.OrderItemCreateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * stock kontrolü için kullanılan custom validator
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SufficientStockValidator implements ConstraintValidator<SufficientStock, OrderCreateRequest> {

    private final InventoryRepository inventoryRepository;

    @Override
    public void initialize(SufficientStock constraintAnnotation) {
    }

    @Override
    public boolean isValid(OrderCreateRequest request, ConstraintValidatorContext context) {
        if (request == null || request.getOrderItems() == null || request.getOrderItems().isEmpty()) {
            return true;
        }

        String branchId = request.getBranchId();
        List<String> insufficientStockItems = new ArrayList<>();

        for (OrderItemCreateRequest item : request.getOrderItems()) {
            String productId = item.getProductId();
            Integer requestedQuantity = item.getQuantity();

            if (productId == null || requestedQuantity == null) {
                continue;
            }

            // inventory' yi kontrol etmemiz lazım
            Optional<InventoryEntity> inventoryOpt = inventoryRepository
                    .findByProductEntityIdAndBranchEntityId(productId, branchId);

            if (inventoryOpt.isEmpty()) {
                insufficientStockItems.add("Product " + productId + " not found in branch inventory");
                continue;
            }

            InventoryEntity inventory = inventoryOpt.get();
            Integer availableStock = inventory.getQuantity();

            if (availableStock < requestedQuantity) {
                insufficientStockItems.add(
                        "Product " + productId + ": requested " + requestedQuantity +
                        ", available " + availableStock
                );
            }
        }

        if (!insufficientStockItems.isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Insufficient stock: " + String.join("; ", insufficientStockItems)
            ).addConstraintViolation();

            log.warn("Stock validation failed for branch {}: {}", branchId, insufficientStockItems);
            return false;
        }

        return true;
    }
}




