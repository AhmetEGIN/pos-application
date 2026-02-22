package com.egin.common.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * branch çalışma saatlerinin geçerli olduğunu doğrulamak için özel bir doğrulama anotasyonu
 * class level
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidWorkingHoursValidator.class)
@Documented
public @interface ValidWorkingHours {

    String message() default "Open time must be before close time";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

