package com.practice.poker2.engine;

import com.practice.poker2.model.Combinations;
import com.practice.poker2.model.Hand;
import com.practice.poker2.rules.RoyalFlushRule;
import com.practice.poker2.rules.Rule;
import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
public class Evaluator {

    private Map<Combinations, Rule> rules;

    public Combinations evaluate(Hand hand) {
        // handle royal flush explicitly
        Rule royalFlushRule = rules.remove(Combinations.ROYAL_FLUSH);
        if (royalFlushRule.match(hand)) {
            return Combinations.ROYAL_FLUSH;
        }

        // handle rules
        List<Combinations> combinations = new ArrayList<>();
        for (Rule rule : rules.values()) {
            if (rule.match(hand)) {
                combinations.add(rule.getCombination());
            }
        }

        // handle combined rules
        if (combinations.size() == 2) {
            if (combinations.contains(Combinations.DRILL) && combinations.contains(Combinations.PAIR)) {
                return Combinations.FULL_HOUSE;
            } else if (combinations.contains(Combinations.STRAIGHT) && combinations.contains(Combinations.FLUSH)) {
                return Combinations.STRAIGHT_FLUSH;
            }
        }

        // return combination with highest priority
        return combinations.stream().min(Comparator.comparing(Combinations::getPriority)).orElse(Combinations.HIGH_CARD);
    }

}
