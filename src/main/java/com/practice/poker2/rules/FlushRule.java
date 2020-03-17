package com.practice.poker2.rules;

import com.practice.poker2.model.Card;
import com.practice.poker2.model.Combinations;
import com.practice.poker2.model.Hand;

public class FlushRule implements Rule {
    @Override
    public boolean match(Hand hand) {
        return hand.getCards().stream().map(Card::getColor).distinct().count() == 1;
    }

    @Override
    public Combinations getCombination() {
        return Combinations.FLUSH;
    }
}
