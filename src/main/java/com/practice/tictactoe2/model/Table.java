package com.practice.tictactoe2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Table {
    private List<Cell> cells;
}
