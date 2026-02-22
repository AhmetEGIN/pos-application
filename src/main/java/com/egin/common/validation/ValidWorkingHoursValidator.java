package com.egin.common.validation;

import com.egin.branch.model.request.BranchCreateRequest;
import com.egin.branch.model.request.BranchUpdateRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;

/**
 * branch open time değerinin close time değerinden önce olduğunu doğrulayan @ValidWorkingHours anotasyonu
 */
public class ValidWorkingHoursValidator implements ConstraintValidator<ValidWorkingHours, Object> {

    @Override
    public void initialize(ValidWorkingHours constraintAnnotation) {
        // Initialization if needed
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        LocalDateTime openTime = null;
        LocalDateTime closeTime = null;

        // BranchCreateRequest
        if (value instanceof BranchCreateRequest request) {
            openTime = request.getOpenTime();
            closeTime = request.getCloseTime();
        }
        // BranchUpdateRequest
        else if (value instanceof BranchUpdateRequest request) {
            openTime = request.getOpenTime();
            closeTime = request.getCloseTime();
        }

        // ikisi de null ise geç
        if (openTime == null || closeTime == null) {
            return true;
        }

        if (!openTime.isBefore(closeTime)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                    "Open time (" + openTime + ") must be before close time (" + closeTime + ")"
            ).addConstraintViolation();
            return false;
        }

        return true;
    }
}

