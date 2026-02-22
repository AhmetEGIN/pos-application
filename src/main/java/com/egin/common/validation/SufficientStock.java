package com.egin.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * stock yeterli mi diye kontrol eder
 * class level
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SufficientStockValidator.class)
@Documented
public @interface SufficientStock {

    String message() default "Insufficient stock for one or more products";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

