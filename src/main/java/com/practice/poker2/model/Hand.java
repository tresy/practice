package com.practice.poker2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Hand {
    private List<Card> cards;
}
