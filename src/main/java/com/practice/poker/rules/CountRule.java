package com.practice.poker.rules;

import com.practice.poker.model.*;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class CountRule implements Rule {
    abstract Long getCount();

    @Override
    public Optional<HandRank> match(Hand hand) {
        Map<Ranks, Long> counts = hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        return counts.entrySet().stream()
                .filter(entry -> entry.getValue().equals(getCount())).findAny()
                .map(entry -> HandRank.builder().highestRank(entry.getKey()).combination(getCombination()).build());
    }

    @Override
    public Combinations getCombination() {
        Long count = getCount();
        if (count == 2L) {
            return Combinations.PAIR;
        } else if (count == 3L) {
            return Combinations.DRILL;
        } else if (count == 4L) {
            return Combinations.POKER;
        }
        throw new UnsupportedOperationException("Count is not supported in poker games!");
    }
}
