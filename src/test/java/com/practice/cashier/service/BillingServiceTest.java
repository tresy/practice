package com.practice.cashier.service;

import com.practice.cashier.discounts.MultiProductDiscount;
import com.practice.cashier.model.Cart;
import com.practice.cashier.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@DisplayName(value = "Testing the billing service")
public class BillingServiceTest {

    private BillingService billingService = new BillingServiceImpl(
            Collections.singletonList(new MultiProductDiscount(Arrays.asList("Banana", "Chocolate"))));

    @ParameterizedTest(name = "#{index} {arguments}")
    @MethodSource(value = "initParams")
    public void testBilling(TestCase testCase) {
        // when
        BigDecimal finalPrice = billingService.getFinalPrice(testCase.getCart());

        // then
        Assert.assertEquals(testCase.getFinalPrice(), finalPrice);
    }

    private static TestCase[] initParams() {
        Product yoghurt = new Product(BigDecimal.valueOf(1.23), "Yoghurt");
        Product milk = new Product(BigDecimal.valueOf(2.50), "Milk");
        Product coke = new Product(BigDecimal.valueOf(4.02), "Coke");
        Product chocolate = new Product(BigDecimal.valueOf(0.77), "Chocolate");
        Product banana = new Product(BigDecimal.valueOf(0.12), "Banana");

        Map<Product, Integer> items1 = new HashMap<>();
        items1.put(yoghurt, 2);
        items1.put(milk, 1);
        items1.put(coke, 5);

        Map<Product, Integer> items2 = new HashMap<>();
        items2.put(coke, 2);
        items2.put(chocolate, 2);
        items2.put(banana, 2);


        return new TestCase[] {
                new TestCase(
                        new Cart(items1),
                        BigDecimal.valueOf(25.06),
                        "Testing cart with multiple products"
                ),
                new TestCase(
                        new Cart(items2),
                        BigDecimal.valueOf(8.93),
                        "Testing discount when buying two from certain products"
                )
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
