package com.practice.cashier2.discounts;

import java.math.BigDecimal;

public class MultipleChocolateDiscount extends BaseMultipleDiscount implements Discount {
    @Override
    public int getAmount() {
        return 3;
    }

    @Override
    public String getProductName() {
        return "Chocolate";
    }

    @Override
    public BigDecimal getDiscount() {
        return BigDecimal.valueOf(0.5);
    }
}
