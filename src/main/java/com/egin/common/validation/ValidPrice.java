package com.egin.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Selling price değerinin MRP değerinden büyük olmamasını sağlayan custom validation annotationı
 * class level
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidPriceValidator.class)
@Documented
public @interface ValidPrice {

    String message() default "Selling price cannot be greater than MRP";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

