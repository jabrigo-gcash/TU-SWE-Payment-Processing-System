package com.gcash.jabrigo;

public class CreditCardPayment extends PaymentMethod implements RequiresAuthentication{

    private boolean isAuthenticated = false;

    public CreditCardPayment(double amount, String currency) {
        super(amount, currency);
    }

    @Override
    public void processPayment() throws PaymentException {
        if (!isAuthenticated) {
            throw new PaymentException("Payment not authenticated");
        }
        System.out.println("Processing credit card payment of " + amount + " " + currency + ".");
    }

    @Override
    public boolean authenticate() {
        System.out.println("Authenticating credit card...");
        isAuthenticated = true;
        return true;
    }
}
