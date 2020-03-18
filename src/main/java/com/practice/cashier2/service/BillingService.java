package com.practice.cashier2.service;

import com.practice.cashier2.model.Cart;

import java.math.BigDecimal;

public interface BillingService {
    BigDecimal getFinalPrice(Cart cart);
}
