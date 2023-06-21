package com.java.feature.currency;

import com.java.feature.currency.currency.Currency;

import java.io.IOException;

public interface CurrencyService {
    double getRate(Currency currency) throws IOException;
}
