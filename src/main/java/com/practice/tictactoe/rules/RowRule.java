package com.practice.tictactoe.rules;

import com.practice.tictactoe.model.Player;
import com.practice.tictactoe.model.Table;

import java.util.Map;
import java.util.stream.Collectors;

public class RowRule implements Rule {
    @Override
    public boolean isWinner(Player player, Table table) {
        Map<Integer, Long> countsInRow = table.getCells().stream().filter(cell -> cell.getPlayer() == player)
                .collect(Collectors.groupingBy(cell -> cell.getCoordinate().getY(), Collectors.counting()));
        return countsInRow.containsValue(3L);
    }
}
