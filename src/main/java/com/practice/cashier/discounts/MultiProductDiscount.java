package com.practice.cashier.discounts;

import com.practice.cashier.model.Product;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class MultiProductDiscount implements Discount {

    private List<String> products;

    @Override
    public boolean match(Map.Entry<Product, Integer> item) {
        return products.contains(item.getKey().getName()) && item.getValue() / 2 > 0;
    }

    @Override
    public BigDecimal getDiscount(Map.Entry<Product, Integer> item) {
        return BigDecimal.valueOf(0.5);
    }

}
