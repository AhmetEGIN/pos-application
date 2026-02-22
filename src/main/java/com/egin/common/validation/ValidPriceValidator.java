package com.egin.common.validation;

import com.egin.product.model.dto.request.product.ProductCreateRequest;
import com.egin.product.model.dto.request.product.ProductUpdateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * sellingprice değerinin mrp değerinden büyük olmamasını sağlayan validator
 */
public class ValidPriceValidator implements ConstraintValidator<ValidPrice, Object> {

    @Override
    public void initialize(ValidPrice constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // @NotNull handle null validation
        }

        Double mrp = null;
        Double sellingPrice = null;

        // ProductCreateRequest
        if (value instanceof ProductCreateRequest request) {
            mrp = request.getMrp();
            sellingPrice = request.getSellingPrice();
        }
        // ProductUpdateRequest
        else if (value instanceof ProductUpdateRequest request) {
            mrp = request.getMrp();
            sellingPrice = request.getSellingPrice();
        }

        // ikisi de null ise validation'u geç
        if (mrp == null || sellingPrice == null) {
            return true;
        }

        if (sellingPrice > mrp) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Selling price (" + sellingPrice + ") cannot be greater than MRP (" + mrp + ")"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}

