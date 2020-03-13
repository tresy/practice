package com.practice.poker.engine;

import com.practice.poker.model.Combinations;
import com.practice.poker.model.Hand;
import com.practice.poker.rules.Rule;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class Evaluator {

    private Map<Combinations, Rule> rules;

    public Combinations evaluate(Hand hand) {
        // sort hand
        hand.getCards().sort(Comparator.comparing(card -> card.getRank().getScore()));

        // test royal flush explicitly
        Rule royalFlushRule = rules.remove(Combinations.ROYAL_FLUSH);
        if (royalFlushRule.match(hand)) {
            return royalFlushRule.getCombination();
        }

        // test other rules
        List<Combinations> combinations = new ArrayList<>();
        for (Rule rule : rules.values()) {
            if (rule.match(hand)) {
                combinations.add(rule.getCombination());
            }
        }

        // test combined rules
        if (combinations.size() == 2) {
            if (combinations.contains(Combinations.PAIR) && combinations.contains(Combinations.DRILL)) {
                return Combinations.FULL_HOUSE;
            } else if (combinations.contains(Combinations.STRAIGHT) && combinations.contains(Combinations.FLUSH)) {
                return Combinations.STRAIGHT_FLUSH;
            }
        }

        // get the highest priority one
        return combinations.stream().min(Comparator.comparingInt(Combinations::getPriority)).orElse(Combinations.HIGH_CARD);
    }

}
