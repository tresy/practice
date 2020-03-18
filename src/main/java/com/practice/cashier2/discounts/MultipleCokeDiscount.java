package com.practice.cashier2.discounts;

import java.math.BigDecimal;

public class MultipleCokeDiscount extends BaseMultipleDiscount implements Discount {
    @Override
    public int getAmount() {
        return 3;
    }

    @Override
    public String getProductName() {
        return "Coke";
    }

    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0.5);
    }
}
