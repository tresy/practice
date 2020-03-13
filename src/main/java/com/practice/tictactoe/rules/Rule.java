package com.practice.tictactoe.rules;

import com.practice.tictactoe.model.Player;
import com.practice.tictactoe.model.Table;

public interface Rule {
    boolean isWinner(Player player, Table table);
}
