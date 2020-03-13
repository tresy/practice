package com.practice.tictactoe.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Cell {
    private Player player;
    private Coordinate coordinate;
}
