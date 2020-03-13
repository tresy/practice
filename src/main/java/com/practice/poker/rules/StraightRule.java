package com.practice.poker.rules;

import com.practice.poker.model.Card;
import com.practice.poker.model.Combinations;
import com.practice.poker.model.Hand;
import com.practice.poker.model.Ranks;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StraightRule implements Rule {
    @Override
    public boolean match(Hand hand) {
        List<Ranks> ranks = hand.getCards().stream().map(Card::getRank).collect(Collectors.toList());
        if (ranks.equals(Arrays.asList(Ranks.TWO, Ranks.THREE, Ranks.FOUR, Ranks.FIVE, Ranks.ACE))) {
            return true;
        }

        for (int i = 1; i < hand.getCards().size(); i++) {
            Card current = hand.getCards().get(i);
            Card previous = hand.getCards().get(i - 1);
            if (current.getRank().getScore() - previous.getRank().getScore() != 1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public Combinations getCombination() {
        return Combinations.STRAIGHT;
    }
}
