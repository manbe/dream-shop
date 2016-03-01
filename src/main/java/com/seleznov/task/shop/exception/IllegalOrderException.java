package com.seleznov.task.shop.exception;

/**
 * @author illcko
 */
public class IllegalOrderException extends RuntimeException{
    public IllegalOrderException(String message) {
        super(message);
    }
}
