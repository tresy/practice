package com.practice.tictactoe2.rule;

import com.practice.tictactoe2.model.Player;
import com.practice.tictactoe2.model.Table;

import java.util.Map;
import java.util.stream.Collectors;

public class CrossRule implements Rule {
    @Override
    public boolean match(Player player, Table table) {
        Map<Integer, Long> colCounts = table.getCells().stream()
                .filter(cell -> {
                    int abs = Math.abs(cell.getCoordinate().getX() - cell.getCoordinate().getY());
                    return cell.getPlayer() == player && (abs == 0 || abs == 2);
                }).collect(Collectors.groupingBy(cell -> cell.getCoordinate().getX(), Collectors.counting()));

        Map<Integer, Long> rowCounts = table.getCells().stream()
                .filter(cell -> {
                    int abs = Math.abs(cell.getCoordinate().getX() - cell.getCoordinate().getY());
                    return cell.getPlayer() == player && (abs == 0 || abs == 2);
                }).collect(Collectors.groupingBy(cell -> cell.getCoordinate().getY(), Collectors.counting()));

        return colCounts.size() == 3 && rowCounts.size() == 3;
    }
}
