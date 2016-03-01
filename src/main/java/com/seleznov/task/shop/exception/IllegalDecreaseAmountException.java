package com.seleznov.task.shop.exception;

/**
 * @author illcko
 */
public class IllegalDecreaseAmountException extends RuntimeException {
    public IllegalDecreaseAmountException(String message) {
        super(message);
    }
}
