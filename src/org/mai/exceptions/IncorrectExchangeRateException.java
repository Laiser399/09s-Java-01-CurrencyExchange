package org.mai.exceptions;

/**
 * Created by Asus on 9/17/2018.
 */
public class IncorrectExchangeRateException extends RuntimeException {
    public IncorrectExchangeRateException(String message) {
        super(message);
    }
}
