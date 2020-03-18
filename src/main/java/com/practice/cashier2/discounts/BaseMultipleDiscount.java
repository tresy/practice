package com.practice.cashier2.discounts;

import com.practice.cashier2.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public abstract class BaseMultipleDiscount implements Discount {

    public abstract int getAmount();
    public abstract String getProductName();
    public abstract BigDecimal getDiscount();

    @Override
    public boolean match(Map.Entry<Product, Integer> item) {
        return item.getKey().getName().equalsIgnoreCase(getProductName()) && item.getValue() / getAmount() > 0;
    }

    @Override
    public BigDecimal calculatePrice(Map.Entry<Product, Integer> item) {
        int amountOnDiscount = item.getValue() / getAmount() * getAmount();
        BigDecimal discountPart = item.getKey().getPrice().multiply(getDiscount().multiply(BigDecimal.valueOf(amountOnDiscount)));
        BigDecimal fullPricePart = item.getKey().getPrice().multiply(BigDecimal.valueOf(item.getValue() - amountOnDiscount));
        return discountPart.add(fullPricePart);
    }

}
