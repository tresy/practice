package com.practice.poker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Combinations {
    HIGH_CARD(10), PAIR(9), TWO_PAIR(8), DRILL(7), STRAIGHT(6),
    FLUSH(5), FULL_HOUSE(4), POKER(3), STRAIGHT_FLUSH(2), ROYAL_FLUSH(1);

    private int priority;
}
