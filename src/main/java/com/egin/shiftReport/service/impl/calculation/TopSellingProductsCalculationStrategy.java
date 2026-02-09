package com.egin.shiftReport.service.impl.calculation;

import com.egin.product.model.Product;
import com.egin.product.model.entity.ProductEntity;
import com.egin.product.model.mapper.product.ProductToProductEntityMapper;
import com.egin.product.service.product.ProductReadService;
import com.egin.shiftReport.model.entity.ShiftReportEntity;
import com.egin.shiftReport.service.ShiftCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Order(5)
@RequiredArgsConstructor
public class TopSellingProductsCalculationStrategy implements ShiftCalculationStrategy {

    private static final int MAX_TOP_PRODUCTS = 5;

    private final ProductReadService productReadService;

    @Override
    public void calculate(ShiftReportEntity shiftReportEntity, String cashierId, LocalDateTime shiftStart, LocalDateTime shiftEnd) {
        List<Product> topProducts = productReadService.getTopSellingProductsByCashierAndDateRange(
                cashierId, shiftStart, shiftEnd, MAX_TOP_PRODUCTS
        );

        if (topProducts != null && !topProducts.isEmpty()) {
            List<ProductEntity> productEntities = topProducts.stream()
                    .map(ProductToProductEntityMapper::toProductEntity)
                    .collect(Collectors.toList());
            shiftReportEntity.setTopSellingProducts(productEntities);
        } else {
            shiftReportEntity.setTopSellingProducts(Collections.emptyList());
        }
    }

}

