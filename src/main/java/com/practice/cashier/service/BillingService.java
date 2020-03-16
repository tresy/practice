package com.practice.cashier.service;

import com.practice.cashier.model.Cart;

import java.math.BigDecimal;

public interface BillingService {
    BigDecimal getFinalPrice(Cart cart);
}
