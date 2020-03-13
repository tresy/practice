package com.practice.poker.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Hand {
    private List<Card> cards;

    public Ranks getHighestRank() {
        return cards.stream()
                .max(Comparator.comparing(card -> card.getRank().getScore()))
                .map(Card::getRank).orElse(null);
    }
}
