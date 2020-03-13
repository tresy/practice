package com.practice.poker.rules;

import com.practice.poker.model.Card;
import com.practice.poker.model.Combinations;
import com.practice.poker.model.Hand;
import com.practice.poker.model.Ranks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RoyalFlushRule implements Rule {
    @Override
    public boolean match(Hand hand) {
        List<Ranks> ranks = hand.getCards().stream().map(Card::getRank).collect(Collectors.toList());
        return ranks.equals(Arrays.asList(Ranks.TEN, Ranks.JACK, Ranks.QUEEN, Ranks.KING, Ranks.ACE))
                && hand.getCards().stream().map(Card::getColor).distinct().count() == 1;
    }

    @Override
    public Combinations getCombination() {
        return Combinations.ROYAL_FLUSH;
    }
}
