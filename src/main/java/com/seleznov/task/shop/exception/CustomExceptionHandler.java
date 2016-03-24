package com.seleznov.task.shop.exception;

import com.seleznov.task.shop.exception.view.ErrorView;
import com.seleznov.task.shop.exception.view.FieldErrorView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author illcko
 */
@ControllerAdvice
public class CustomExceptionHandler{

    private static final Logger log = LoggerFactory.getLogger(CustomExceptionHandler.class);

    @ExceptionHandler({IllegalOrderException.class, IllegalDecreaseAmountException.class, IllegalCustomerForCreateException.class})
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorView processMethodIllegalOrderException(RuntimeException exception) {
        log.error(exception.getMessage(), exception);
        return new ErrorView(ErrorConstants.ERR_OPERATION_DENIED, exception.getMessage());
    }


    @ExceptionHandler({MethodArgumentNotValidException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorView processValidationError(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        return processFieldErrors(fieldErrors);
    }

    private ErrorView processFieldErrors(List<FieldError> fieldErrors) {
        List<FieldErrorView> fieldErrorViews = fieldErrors.stream()
                .map(fieldError -> new FieldErrorView(fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        return  new ErrorView(ErrorConstants.ERR_VALIDATION, null, fieldErrorViews);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorView processMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        return new ErrorView(ErrorConstants.ERR_METHOD_NOT_SUPPORTED, exception.getMessage());
    }



}
