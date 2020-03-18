package com.practice.cashier2.service;

import com.practice.cashier2.discounts.Discount;
import com.practice.cashier2.model.Cart;
import com.practice.cashier2.model.Product;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class BillingServiceImpl implements BillingService {

    private List<Discount> discounts;

    @Override
    public BigDecimal getFinalPrice(Cart cart) {
        BigDecimal finalPrice = BigDecimal.ZERO;

        for (Map.Entry<Product, Integer> entry : cart.getItems().entrySet()) {
            Product product = entry.getKey();
            Integer amount = entry.getValue();
            BigDecimal price = product.getPrice().multiply(BigDecimal.valueOf(amount));
            for (Discount discount : discounts) {
                if (discount.match(entry)) {
                    price = discount.calculatePrice(entry);
                }
            }
            finalPrice = finalPrice.add(price);
        }

        return finalPrice;
    }

}
