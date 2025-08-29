package com.gcash.jabrigo;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PaymentProcessor {

    private static void printMenu() {
        System.out.println("\n--- Payment Processor Menu ---");
        System.out.println("1. Add a Credit Card Payment");
        System.out.println("2. Add a PayPal Payment");
        System.out.println("3. Process All Payments");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void printAmountPrompt() {
        System.out.print("Enter credit card payment amount: ");
    }

    private static void printCurrencyPrompt() {
        System.out.print("Enter currency (e.g. USD): ");
    }

    private static void addCreditCardPayment(Scanner s, List<PaymentMethod> paymentMethods) {
        try {
            printAmountPrompt();
            double amount = s.nextDouble();
            printCurrencyPrompt();
            String currency = s.next();
            PaymentMethod creditCardPayment = new CreditCardPayment(amount, currency);

            paymentMethods.add(creditCardPayment);
            System.out.println("Credit Card payment added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Input amount is invalid");
        }
    }

    private static void addPayPalPayment(Scanner s, List<PaymentMethod> paymentMethods) {
        try {
            printAmountPrompt();
            double amount = s.nextDouble();
            printCurrencyPrompt();
            String currency = s.next();
            PaymentMethod payPalPayment = new PayPalPayment(amount, currency);

            paymentMethods.add(payPalPayment);
            System.out.println("PayPal payment added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Input amount is invalid");
        }
    }

    private static void processAllPayments(List<PaymentMethod> paymentMethods) {
        if (paymentMethods.isEmpty()) {
            System.out.println("No payments to process.");
            return;
        }

        System.out.println("\n--- Processing All Payments ---");
        paymentMethods.forEach(paymentMethod -> {
           if (paymentMethod instanceof RequiresAuthentication) {
               if(!((RequiresAuthentication) paymentMethod).authenticate()) {
                   System.out.println("Authentication failed.");
                   return;
               }
           }
           try {
               paymentMethod.processPayment();
           } catch (PaymentException e) {
               System.out.println(e.getMessage());
           }
        });
        System.out.println("-----------------------------");
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        List<PaymentMethod> paymentMethods = new ArrayList<>();
        boolean canExit = false;

        while(!canExit) {
            printMenu();
            int option = 0;
            try{
                option = s.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input");
            }
            switch (option) {
                case 1 -> addCreditCardPayment(s, paymentMethods);
                case 2 -> addPayPalPayment(s, paymentMethods);
                case 3 -> processAllPayments(paymentMethods);
                case 4 -> canExit = true;
            }
        }
        System.out.println("\nExiting the application. Goodbye!");
        s.close();
    }
}