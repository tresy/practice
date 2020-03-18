package com.practice.cashier2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class Cart {
    Map<Product, Integer> items;
}
