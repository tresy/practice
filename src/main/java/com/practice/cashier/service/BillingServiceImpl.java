package com.practice.cashier.service;

import com.practice.cashier.discounts.Discount;
import com.practice.cashier.model.Cart;
import com.practice.cashier.model.Product;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class BillingServiceImpl implements BillingService {

    private List<Discount> discounts;

    @Override
    public BigDecimal getFinalPrice(Cart cart) {
        Map<Product, BigDecimal> discountMap = new HashMap<>();
        for (Discount discount : discounts) {
            cart.getItems().entrySet().forEach(entry -> {
                BigDecimal actual = discount.match(entry) ? discount.getDiscount(entry) : BigDecimal.ONE;
                discountMap.merge(entry.getKey(), actual, BigDecimal::multiply);
            });
        }

        BigDecimal sum = BigDecimal.ZERO;
        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            sum = sum.add(entry.getKey().getPrice().multiply(discountMap.computeIfAbsent(entry.getKey(), e -> BigDecimal.ONE)).multiply(BigDecimal.valueOf(entry.getValue())));
        }
        return sum.setScale(2, RoundingMode.HALF_UP);
    }
}
