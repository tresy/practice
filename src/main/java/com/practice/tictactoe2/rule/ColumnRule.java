package com.practice.tictactoe2.rule;

import com.practice.tictactoe2.model.Player;
import com.practice.tictactoe2.model.Table;

import java.util.Map;
import java.util.stream.Collectors;

public class ColumnRule implements Rule {
    @Override
    public boolean match(Player player, Table table) {
        Map<Integer, Long> counts = table.getCells().stream()
                .filter(cell -> cell.getPlayer() == player)
                .collect(Collectors.groupingBy(cell -> cell.getCoordinate().getX(), Collectors.counting()));
        return counts.containsValue(3L);
    }
}
