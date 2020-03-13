package com.practice.tictactoe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Table {
    List<Cell> cells;
}
