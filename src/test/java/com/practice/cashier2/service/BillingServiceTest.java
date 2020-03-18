package com.practice.cashier2.service;

import com.practice.cashier2.discounts.MultipleChocolateDiscount;
import com.practice.cashier2.discounts.MultipleCokeDiscount;
import com.practice.cashier2.model.Cart;
import com.practice.cashier2.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@DisplayName("Testing the billing service")
public class BillingServiceTest {

    @ParameterizedTest(name = "#{index} {arguments}")
    @MethodSource("initParams")
    public void testFinalPrice(TestCase testCase) {
        // setup
        BillingService billingService = new BillingServiceImpl(Arrays.asList(new MultipleChocolateDiscount(), new MultipleCokeDiscount()));

        // when
        BigDecimal finalPrice = billingService.getFinalPrice(testCase.getCart());

        // then
        Assert.assertEquals(testCase.getFinalPrice(), finalPrice);
    }

    private static TestCase[] initParams() {
        Product chocolate = new Product("Chocolate", BigDecimal.valueOf(1.25));
        Product coke = new Product("Coke", BigDecimal.valueOf(2.99));
        Product banana = new Product("Banana", BigDecimal.valueOf(0.25));

        Map<Product, Integer> items1 = new HashMap<>();
        items1.put(chocolate, 2);
        items1.put(coke, 2);
        items1.put(banana, 5);

        Map<Product, Integer> items2 = new HashMap<>();
        items2.put(chocolate, 4);
        items2.put(coke, 6);
        items2.put(banana, 5);

        return new TestCase[] {
                new TestCase(new Cart(items1), BigDecimal.valueOf(9.73), "Testing final price for cart"),
                new TestCase(new Cart(items2), BigDecimal.valueOf(13.345), "Testing final price with 50% discount if buying 3 coke/chocolate")
        };
    }

    @Getter
    @AllArgsConstructor
    private static class TestCase {
        private Cart cart;
        private BigDecimal finalPrice;
        private String name;

        @Override
        public String toString() {
            return name;
        }
    }

}
