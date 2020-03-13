package com.practice.tictactoe.engine;

import com.practice.tictactoe.model.Player;
import com.practice.tictactoe.model.Table;
import com.practice.tictactoe.rules.Rule;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Evaluator {

    private List<Rule> rules;

    public boolean evaluate(Player player, Table table) {
        return rules.stream().anyMatch(rule -> rule.isWinner(player, table));
    }

}
