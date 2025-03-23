package com.aarya.bms.bms.merging.factory;

import com.aarya.bms.bms.merging.strategy.DebitCardPaymentStrategy;
import com.aarya.bms.bms.merging.strategy.PaymentStrategy;
import com.aarya.bms.bms.merging.strategy.UpiPaymentStrategy;

import java.util.HashMap;

public class PaymentStrategyFactory {

    private static final HashMap<String,PaymentStrategy> paymentStrategyHashMap = new HashMap<>();

    static {
        addPaymentStrategy("UPI", new UpiPaymentStrategy());
        addPaymentStrategy("DEBITCARD", new DebitCardPaymentStrategy());
    }

    public static void addPaymentStrategy(String type, PaymentStrategy strategy) {
        paymentStrategyHashMap.put(type.toUpperCase(), strategy);
    }

    public static PaymentStrategy generatePaymentStrategy(String type) {
        String strategyType = type.toUpperCase();
        if(paymentStrategyHashMap.containsKey(strategyType)){
            return paymentStrategyHashMap.get(strategyType);
        }else{
            throw new RuntimeException("Payment method not supported");
        }
    }
}
