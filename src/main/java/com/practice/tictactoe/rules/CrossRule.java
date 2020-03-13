package com.practice.tictactoe.rules;

import com.practice.tictactoe.model.Player;
import com.practice.tictactoe.model.Table;

public class CrossRule implements Rule {
    @Override
    public boolean isWinner(Player player, Table table) {
        return table.getCells().stream().filter(cell -> {
            int abs = Math.abs(cell.getCoordinate().getX() - cell.getCoordinate().getY());
            return cell.getPlayer() == player && ( abs == 0 || abs == 2 );
        }).count() >= 3;
    }
}
