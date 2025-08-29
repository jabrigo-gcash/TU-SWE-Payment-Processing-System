package com.gcash.jabrigo;

public class PayPalPayment extends PaymentMethod {

    public PayPalPayment(double amount, String currency) {
        super(amount, currency);
    }

    @Override
    public void processPayment() throws PaymentException {
        System.out.println("Processing PayPal payment of " + amount + " " + currency + ".");
    }
}
