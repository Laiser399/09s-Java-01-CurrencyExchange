package org.mai.exceptions;

/**
 * Created by VPerov on 17.09.2018.
 */
public class DifferentCurrenciesException extends RuntimeException {
    public DifferentCurrenciesException() {}

    public DifferentCurrenciesException(String message) {
        super(message);
    }
}
