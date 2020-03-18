package com.practice.cashier2.discounts;

import com.practice.cashier2.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface Discount {
    boolean match(Map.Entry<Product, Integer> item);
    BigDecimal calculatePrice(Map.Entry<Product, Integer> item);
}
