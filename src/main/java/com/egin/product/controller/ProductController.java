package com.egin.product.controller;

import com.egin.common.model.dto.response.CustomPagingResponse;
import com.egin.common.model.dto.response.CustomResponse;
import com.egin.product.model.Product;
import com.egin.product.model.dto.request.product.ProductCreateRequest;
import com.egin.product.model.dto.request.product.ProductPagingByStoreRequest;
import com.egin.product.model.dto.request.product.ProductUpdateRequest;
import com.egin.product.model.dto.request.product.SearchProductPagingRequest;
import com.egin.product.model.mapper.product.ProductCustomPageToCustomPagingResponseMapper;
import com.egin.product.service.product.ProductCreateService;
import com.egin.product.service.product.ProductDeleteService;
import com.egin.product.service.product.ProductReadService;
import com.egin.product.service.product.ProductUpdateService;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductCreateService productCreateService;
    private final ProductReadService productReadService;
    private final ProductDeleteService productDeleteService;
    private final ProductUpdateService productUpdateService;

    public ProductController(
            ProductCreateService productCreateService,
            ProductReadService productReadService,
            ProductDeleteService productDeleteService,
            ProductUpdateService productUpdateService
    ) {
        this.productCreateService = productCreateService;
        this.productReadService = productReadService;
        this.productDeleteService = productDeleteService;
        this.productUpdateService = productUpdateService;
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Product> createProduct(
            @RequestBody @Valid final ProductCreateRequest request
    ) {
        final Product product = productCreateService.createProduct(request);

        return CustomResponse.successOf(product);
    }


    @GetMapping("/store/{store-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Product>> getProductsByStoreId(
            @PathVariable(value = "store-id") final String storeId,
            @RequestBody @Valid final ProductPagingByStoreRequest request
            ) {
        final CustomPagingResponse<Product> products = ProductCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        productReadService.getProductsByStoreId(storeId, request)
                );

        return CustomResponse.successOf(products);
    }


    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN', 'USER')")
    public CustomResponse<CustomPagingResponse<Product>> searchProducts(
            @RequestBody @Valid final SearchProductPagingRequest request
    ) {
        final CustomPagingResponse<Product> products = ProductCustomPageToCustomPagingResponseMapper
                .toCustomPagingResponse(
                        productReadService.searchByKeyword(request)
                );
        return CustomResponse.successOf(products);
    }

    @PutMapping("/{product-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Product> updateProduct(
            @PathVariable(value = "product-id") final String productId,
            @RequestBody @Valid final ProductUpdateRequest request
    ) {
        final Product updatedProduct = productUpdateService.updateProduct(productId, request);

        return CustomResponse.successOf(updatedProduct);
    }

    @DeleteMapping("/{product-id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'STORE_ADMIN')")
    public CustomResponse<Void> deleteProduct(@PathVariable(value = "product-id") final String productId) {
        productDeleteService.deleteProduct(productId);
        return CustomResponse.SUCCESS;
    }

}
