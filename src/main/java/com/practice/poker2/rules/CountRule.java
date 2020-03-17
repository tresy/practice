package com.practice.poker2.rules;

import com.practice.poker2.model.Card;
import com.practice.poker2.model.Combinations;
import com.practice.poker2.model.Hand;
import com.practice.poker2.model.Ranks;

import java.util.Map;
import java.util.stream.Collectors;

public abstract class CountRule implements Rule {

    public abstract Long getCount();

    @Override
    public boolean match(Hand hand) {
        Map<Ranks, Long> counts = hand.getCards().stream().collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));
        return counts.containsValue(getCount());
    }

    @Override
    public Combinations getCombination() {
        if (getCount() == 2L) {
            return Combinations.PAIR;
        } else if (getCount() == 3L) {
            return  Combinations.DRILL;
        } else if (getCount() == 4L) {
            return  Combinations.POKER;
        } else {
            throw new UnsupportedOperationException(String.format("Can't get a combination for count %d!", getCount()));
        }
    }

}
