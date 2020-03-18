package com.practice.tictactoe2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Cell {
    private Player player;
    private Coordinate coordinate;
}
