package com.practice.poker.rules;

import com.practice.poker.model.*;

import java.util.Comparator;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TwoPairRule implements Rule {
    @Override
    public Optional<HandRank> match(Hand hand) {
        Map<Ranks, Long> counts = hand.getCards().stream()
                .collect(Collectors.groupingBy(Card::getRank, Collectors.counting()));

        Map<Ranks, Long> pairs = counts.entrySet().stream()
                .filter(entry -> entry.getValue() == 2L)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        if (pairs.size() != 2) {
            return Optional.empty();
        }

        return pairs.entrySet().stream()
                .max(Comparator.comparing(entry -> entry.getKey().getScore()))
                .map(entry -> HandRank.builder().highestRank(entry.getKey()).combination(getCombination()).build());
    }

    @Override
    public Combinations getCombination() {
        return Combinations.TWO_PAIR;
    }
}
