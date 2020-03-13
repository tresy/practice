package com.practice.poker.rules;

import com.practice.poker.model.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class StraightRule implements Rule {
    @Override
    public Optional<HandRank> match(Hand hand) {
        List<Ranks> ranks = hand.getCards().stream().map(Card::getRank).collect(Collectors.toList());
        if (ranks.equals(Arrays.asList(Ranks.TWO, Ranks.THREE, Ranks.FOUR, Ranks.FIVE, Ranks.ACE))) {
            return Optional.of(HandRank.builder().combination(getCombination()).highestRank(Ranks.FIVE).build());
        }

        for (int i = 1; i < ranks.size(); i++) {
            Ranks current = ranks.get(i);
            Ranks previous = ranks.get(i - 1);
            if (current.getScore() - previous.getScore() != 1) {
                return Optional.empty();
            }
        }

        return Optional.of(HandRank.builder()
                .combination(getCombination())
                .highestRank(ranks.get(ranks.size() - 1))
                .build()
        );
    }

    @Override
    public Combinations getCombination() {
        return Combinations.STRAIGHT;
    }
}
