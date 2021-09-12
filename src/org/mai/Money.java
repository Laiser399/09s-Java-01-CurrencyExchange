package org.mai;

import org.mai.exceptions.DifferentCurrenciesException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money {
    private final Currency currency;
    private final BigDecimal amount;

    public Money(Currency currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount.setScale(
                this.currency.getDefaultFractionDigits(),
                RoundingMode.HALF_UP);
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Money add(Money money) {
        checkCurrency(money.currency);

        var amount = this.amount.add(money.amount);

        return new Money(currency, amount);
    }

    public Money subtract(Money money) {
        checkCurrency(money.currency);

        var amount = this.amount.subtract(money.amount);

        return new Money(currency, amount);
    }

    public Money multiply(BigDecimal ratio) {
        var amount = this.amount.multiply(ratio);

        return new Money(currency, amount);
    }

    public Money divide(BigDecimal ratio) {
        var amount = this.amount.divide(ratio, RoundingMode.HALF_UP);

        return new Money(currency, amount);
    }

    private void checkCurrency(Currency currency) {
        if (!this.currency.equals(currency)) {
            throw new DifferentCurrenciesException();
        }
    }
}
