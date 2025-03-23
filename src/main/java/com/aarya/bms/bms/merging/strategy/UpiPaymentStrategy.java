package com.aarya.bms.bms.merging.strategy;


public class UpiPaymentStrategy implements PaymentStrategy{
    @Override
    public  boolean pay(double amount) {
        System.out.println("Money paid: "+amount+" via upi");
        return true;
    }
}
