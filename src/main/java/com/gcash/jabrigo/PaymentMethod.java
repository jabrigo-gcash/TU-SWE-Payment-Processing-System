package com.gcash.jabrigo;

public abstract class PaymentMethod {

    protected double amount;
    protected String currency;

    public PaymentMethod(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public abstract void processPayment() throws PaymentException;
}
