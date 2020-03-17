package com.practice.poker2.rules;

import com.practice.poker2.model.Card;
import com.practice.poker2.model.Combinations;
import com.practice.poker2.model.Hand;
import com.practice.poker2.model.Ranks;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StraightRule implements Rule {
    @Override
    public boolean match(Hand hand) {
        List<Ranks> ranks = hand.getCards().stream()
                .map(Card::getRank)
                .sorted(Comparator.comparing(Ranks::getScore))
                .collect(Collectors.toList());

        // handle low ace explicitly
        if (ranks.equals(Arrays.asList(Ranks.TWO, Ranks.THREE, Ranks.FOUR, Ranks.FIVE, Ranks.ACE))) {
            return true;
        }

        for (int i = 1; i < ranks.size(); i++) {
            int actual = ranks.get(i).getScore();
            int previous = ranks.get(i - 1).getScore();
            if (actual - previous != 1) {
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
