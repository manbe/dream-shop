package com.seleznov.task.shop.exception.view;

/**
 * @author illcko
 */
public class FieldErrorView {

    private final String objectName;

    private final String field;

    private final String message;

    public FieldErrorView(String objectName, String field, String message) {
        this.objectName = objectName;
        this.field = field;
        this.message = message;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
