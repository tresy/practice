package com.practice.poker.engine;

import com.practice.poker.model.Combinations;
import com.practice.poker.model.Hand;
import com.practice.poker.model.HandRank;
import com.practice.poker.rules.Rule;
import lombok.AllArgsConstructor;

import java.util.*;

@AllArgsConstructor
public class Evaluator {

    private Map<Combinations, Rule> rules;

    public HandRank evaluate(Hand hand) {
        // sort hand
        hand.getCards().sort(Comparator.comparing(card -> card.getRank().getScore()));

        // test royal flush explicitly
        Rule royalFlushRule = rules.remove(Combinations.ROYAL_FLUSH);
        Optional<HandRank> royalFlushOpt = royalFlushRule.match(hand);
        if (royalFlushOpt.isPresent()) {
            return royalFlushOpt.get();
        }

        // test other rules
        List<HandRank> handRanks = new ArrayList<>();
        for (Rule rule : rules.values()) {
            rule.match(hand).ifPresent(handRanks::add);
        }

        // test combined rules
        if (handRanks.size() == 2) {
            if (handRanks.stream().allMatch(handRank -> handRank.getCombination() == Combinations.PAIR || handRank.getCombination() == Combinations.DRILL)) {
                return HandRank.builder().combination(Combinations.FULL_HOUSE)
                        .highestRank(handRanks.stream().max(Comparator.comparing(HandRank::getHighestRank)).map(HandRank::getHighestRank).orElse(null))
                        .build();
            } else if (handRanks.stream().allMatch(handRank -> handRank.getCombination() == Combinations.STRAIGHT || handRank.getCombination() == Combinations.FLUSH)) {
                return HandRank.builder()
                        .combination(Combinations.STRAIGHT_FLUSH)
                        .highestRank(handRanks.stream().filter(handRank -> handRank.getCombination() == Combinations.STRAIGHT).findAny().map(HandRank::getHighestRank).orElse(null))
                        .build();
            }
        }

        // get the highest priority one and the highest card in the hand
        return handRanks.stream().min(Comparator.comparing(handRank -> handRank.getCombination().getPriority()))
                .orElse(HandRank.builder().combination(Combinations.HIGH_CARD).highestRank(hand.getHighestRank()).build());
    }

}
