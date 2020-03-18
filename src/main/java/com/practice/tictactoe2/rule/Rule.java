package com.practice.tictactoe2.rule;

import com.practice.tictactoe2.model.Player;
import com.practice.tictactoe2.model.Table;

public interface Rule {
    boolean match(Player player, Table table);
}
