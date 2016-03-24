package com.seleznov.task.shop.exception.view;

/**
 * @author illcko
 */
public class FieldErrorView {

    private String objectName;

    private String field;

    private String message;

    public FieldErrorView() {
    }

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

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public void setField(String field) {
        this.field = field;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
