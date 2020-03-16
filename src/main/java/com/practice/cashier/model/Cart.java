package com.practice.cashier.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Cart {
    private Map<Product, Integer> items;
}
