package com.aarya.bms.bms.merging.strategy;

public class DebitCardPaymentStrategy implements PaymentStrategy{
    @Override
    public boolean pay(double amount) {
        System.out.println("Money paid: "+amount+" via debitCard");
        return false;
    }
}
