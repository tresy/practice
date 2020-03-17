package com.practice.poker2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Card {
    private Ranks rank;
    private Colors color;
}
