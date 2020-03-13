package com.practice.poker.rules;

import com.practice.poker.model.Card;
import com.practice.poker.model.Combinations;
import com.practice.poker.model.Hand;
import com.practice.poker.model.HandRank;

import java.util.Optional;

public class FlushRule implements Rule {
    @Override
    public Optional<HandRank> match(Hand hand) {
        if (hand.getCards().stream().map(Card::getColor).distinct().count() == 1) {
           return Optional.of(HandRank.builder()
                   .combination(getCombination())
                   .highestRank(hand.getHighestRank())
                   .build());
        }
        return Optional.empty();
    }

    @Override
    public Combinations getCombination() {
        return Combinations.FLUSH;
    }
}
