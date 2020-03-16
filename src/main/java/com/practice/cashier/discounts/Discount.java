package com.practice.cashier.discounts;

import com.practice.cashier.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface Discount {
    boolean match(Map.Entry<Product, Integer> item);
    BigDecimal getDiscount(Map.Entry<Product, Integer> item);
}
