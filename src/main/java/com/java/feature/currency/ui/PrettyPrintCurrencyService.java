package com.java.feature.currency.ui;

import com.java.feature.currency.currency.Currency;

public class PrettyPrintCurrencyService {
    public String convert(double rate, Currency currency){


        return "Course "+currency.name()+" =>"+" UAH ="+rate;
    }
}
