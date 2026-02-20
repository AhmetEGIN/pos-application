package com.egin.common.exception;

import com.egin.auth.exception.PasswordNotValidException;
import com.egin.auth.exception.TokenAlreadyInvalidatedException;
import com.egin.auth.exception.UserStatusNotValidException;
import com.egin.branch.exception.BranchEntityNotFoundException;
import com.egin.common.model.CustomError;
import com.egin.inventory.exception.InventoryNotFoundException;
import com.egin.order.exception.OrderNotFoundException;
import com.egin.payment.exception.IllegalPaymentStateTransitionException;
import com.egin.payment.exception.PaymentAlreadyProcessedException;
import com.egin.payment.exception.PaymentNotFoundException;
import com.egin.payment.exception.PaymentProcessingException;
import com.egin.product.exception.category.CategoryNotFoundException;
import com.egin.product.exception.product.ProductNotFoundException;
import com.egin.refund.exception.RefundNotFoundException;
import com.egin.shiftReport.exception.ShiftAlreadyActiveException;
import com.egin.shiftReport.exception.ShiftReportNotFoundException;
import com.egin.store.exception.StoreNotFoundException;
import com.egin.user.exception.*;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handeMethodArgumentNotValidException(
            final MethodArgumentNotValidException exception
    ) {

        List<CustomError.CustomSubError> subErrors = new ArrayList<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            CustomError.CustomSubError subError = CustomError.CustomSubError.builder()
                    .field(fieldName)
                    .message(message)
                    .value(error.getRejectedValue())
                    .type(error.getCode())
                    .build();
            subErrors.add(subError);
        });

        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message("Validation failed for one or more fields.")
                .isSuccess(false)
                .subErrors(subErrors)
                .build();


        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Object> handlePathVariableException(
            final ConstraintViolationException exception
            ) {
        List<CustomError.CustomSubError> subErrors = new ArrayList<>();
        exception.getConstraintViolations().forEach(violation -> {
            String fieldName = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            CustomError.CustomSubError subError = CustomError.CustomSubError.builder()
                    .field(fieldName)
                    .message(message)
                    .value(violation.getInvalidValue())
                    .type(violation.getInvalidValue().getClass().getSimpleName())
                    .build();
            subErrors.add(subError);
        });

        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message("Validation failed for one or more path variables.")
                .isSuccess(false)
                .subErrors(subErrors)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(
            final RuntimeException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.API_ERROR.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(PasswordNotValidException.class)
    public ResponseEntity<CustomError> handlePasswordNotValidException(
            final PasswordNotValidException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.AUTHENTICATION_ERROR.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<?> handleAccessDeniedException(
            final AccessDeniedException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.FORBIDDEN)
                .header(CustomError.Header.AUTHENTICATION_ERROR.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.FORBIDDEN);
    }


    @ExceptionHandler(TokenAlreadyInvalidatedException.class)
    public ResponseEntity<CustomError> handleTokenAlreadyInvalidatedException(
            final TokenAlreadyInvalidatedException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.AUTHENTICATION_ERROR.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<CustomError> handleUserNotFoundException(
            final UserNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<CustomError> handleUserAlreadyExistsException(
            final UserAlreadyExistsException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .header(CustomError.Header.ALREADY_EXISTS.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(UserStatusNotValidException.class)
    public ResponseEntity<CustomError> handleUserStatusNotValidException(
            final UserStatusNotValidException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BranchEntityNotFoundException.class)
    public ResponseEntity<CustomError> handleBranchEntityNotFoundException(
            final BranchEntityNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InventoryNotFoundException.class)
    public ResponseEntity<CustomError> handleInventoryNotFoundException(
            final InventoryNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<CustomError> handleOrderNotFoundException(
            final OrderNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<CustomError> handleProductNotFoundException(
            final ProductNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<CustomError> handleCategoryNotFoundException(
            final CategoryNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(RefundNotFoundException.class)
    public ResponseEntity<CustomError> handleRefundNotFoundException(
            final RefundNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShiftReportNotFoundException.class)
    public ResponseEntity<CustomError> handleShiftReportNotFoundException(
            final ShiftReportNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ShiftAlreadyActiveException.class)
    public ResponseEntity<CustomError> handleShiftAlreadyActiveException(
            final ShiftAlreadyActiveException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();

        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(StoreNotFoundException.class)
    public ResponseEntity<CustomError> handleStoreNotFoundException(
            final StoreNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<CustomError> handleCustomerNotFoundException(
            final CustomerNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseEntity<CustomError> handleEmployeeAlreadyExistsException(
            final EmployeeAlreadyExistsException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .header(CustomError.Header.ALREADY_EXISTS.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<CustomError> handleEmployeeNotFoundException(
            final EmployeeNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);

    }

    // ==================== PAYMENT EXCEPTIONS ====================

    @ExceptionHandler(PaymentNotFoundException.class)
    public ResponseEntity<CustomError> handlePaymentNotFoundException(
            final PaymentNotFoundException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.NOT_FOUND)
                .header(CustomError.Header.NOT_FOUND.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PaymentAlreadyProcessedException.class)
    public ResponseEntity<CustomError> handlePaymentAlreadyProcessedException(
            final PaymentAlreadyProcessedException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.CONFLICT)
                .header(CustomError.Header.ALREADY_EXISTS.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalPaymentStateTransitionException.class)
    public ResponseEntity<CustomError> handleIllegalPaymentStateTransitionException(
            final IllegalPaymentStateTransitionException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST)
                .header(CustomError.Header.VALIDATION_ERROR.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<CustomError> handlePaymentProcessingException(
            final PaymentProcessingException exception
    ) {
        CustomError customError = CustomError.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .header(CustomError.Header.API_ERROR.getName())
                .message(exception.getMessage())
                .isSuccess(false)
                .build();
        return new ResponseEntity<>(customError, HttpStatus.INTERNAL_SERVER_ERROR);
    }



}
