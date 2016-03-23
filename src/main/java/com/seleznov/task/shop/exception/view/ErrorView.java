package com.seleznov.task.shop.exception.view;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author illcko
 */
public class ErrorView {

    private String code;
    private String message;
    private Collection<FieldErrorView> fieldErrors;

    public ErrorView() {
    }

    public ErrorView(String code) {
        this(code, null);
    }

    public ErrorView(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public ErrorView(String code, String message, Collection<FieldErrorView> fieldErrors) {
        this.code = code;
        this.message = message;
        this.fieldErrors = fieldErrors;
    }


    public void add(String objectName, String field, String message) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorView(objectName, field, message));
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Collection<FieldErrorView> getFieldErrors() {
        return fieldErrors;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFieldErrors(Collection<FieldErrorView> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}
