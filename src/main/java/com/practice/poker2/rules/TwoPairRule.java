package com.practice.poker2.rules;

import com.practice.poker2.model.Card;
import com.practice.poker2.model.Combinations;
import com.practice.poker2.model.Hand;
import com.practice.poker2.model.Ranks;

import java.util.Map;
import java.util.stream.Collectors;

public class TwoPairRule implements Rule {
    @Override
    public boolean match(Hand hand) {
        Map<Ranks, Long> counts = hand.getCards().stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return counts.values().stream().filter(value -> value == 2L).count() == 2;
    }

    @Override
    public Combinations getCombination() {
        return Combinations.TWO_PAIR;
    }
}
