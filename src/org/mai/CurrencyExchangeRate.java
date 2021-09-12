package org.mai;

import org.mai.exceptions.DifferentCurrenciesException;
import org.mai.exceptions.IncorrectExchangeRateException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

/**
 * Created by VPerov on 17.09.2018.
 */
public class CurrencyExchangeRate {
    private final BigDecimal rate;
    private final Currency baseCurrency;
    private final Currency destCurrency;

    public CurrencyExchangeRate(BigDecimal rate, Currency baseCurrency, Currency destCurrency) {
        if (rate.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IncorrectExchangeRateException(String.format("Expected rate > 0, but actual is %s", rate));
        }

        this.rate = rate;
        this.baseCurrency = baseCurrency;
        this.destCurrency = destCurrency;
    }

    public Money convert(Money money) {
        if (!money.getCurrency().equals(baseCurrency)) {
            throw new DifferentCurrenciesException("Base currency and money's currency are different.");
        }

        var result = money.getAmount()
                .multiply(rate)
                .setScale(destCurrency.getDefaultFractionDigits(), RoundingMode.HALF_UP);

        return new Money(destCurrency, result);
    }
}
