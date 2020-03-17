package com.practice.poker2.rules;

import com.practice.poker2.model.Card;
import com.practice.poker2.model.Combinations;
import com.practice.poker2.model.Hand;
import com.practice.poker2.model.Ranks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RoyalFlushRule implements Rule {
    @Override
    public boolean match(Hand hand) {
        List<Ranks> ranks = hand.getCards().stream()
                .map(Card::getRank)
                .sorted(Comparator.comparing(Ranks::getScore))
                .collect(Collectors.toList());

        // handle low ace explicitly
        return ranks.equals(Arrays.asList(Ranks.TEN, Ranks.JACK, Ranks.QUEEN, Ranks.KING, Ranks.ACE))
                && hand.getCards().stream().map(Card::getColor).distinct().count() == 1;
    }

    @Override
    public Combinations getCombination() {
        return Combinations.ROYAL_FLUSH;
    }
}
