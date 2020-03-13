package com.practice.poker.rules;

import com.practice.poker.model.Card;
import com.practice.poker.model.Combinations;
import com.practice.poker.model.Hand;
import com.practice.poker.model.Ranks;

import java.util.Map;
import java.util.stream.Collectors;


public class PairRule implements Rule {
    public boolean match(Hand hand) {
         Map<Ranks, Long> counts = hand.getCards().stream()
                 .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
         return counts.containsValue(2L);
    }

    public Combinations getCombination() {
        return Combinations.PAIR;
    }
}
