package com.practice.poker.rules;

import com.practice.poker.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RoyalFlushRule implements Rule {
    @Override
    public Optional<HandRank> match(Hand hand) {
        List<Ranks> ranks = hand.getCards().stream().map(Card::getRank).collect(Collectors.toList());
        if (ranks.equals(Arrays.asList(Ranks.TEN, Ranks.JACK, Ranks.QUEEN, Ranks.KING, Ranks.ACE))
                && hand.getCards().stream().map(Card::getColor).distinct().count() == 1) {
            return Optional.of(HandRank.builder().combination(getCombination()).highestRank(Ranks.ACE).build());
        }
        return Optional.empty();
    }

    @Override
    public Combinations getCombination() {
        return Combinations.ROYAL_FLUSH;
    }
}
