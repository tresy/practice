package com.practice.tictactoe2.engine;

import com.practice.tictactoe2.model.Player;
import com.practice.tictactoe2.model.Table;
import com.practice.tictactoe2.rule.Rule;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class Evaluator {

    private List<Rule> rules;

    public boolean evaluate(Player player, Table table) {
        for (Rule rule : rules) {
            if (rule.match(player, table)) {
                return true;
            }
        }
        return false;
    }

}
