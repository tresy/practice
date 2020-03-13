package com.practice.poker.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Card {
    private Ranks rank;
    private Colors color;
}
