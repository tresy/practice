package com.practice.poker.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class HandRank {
    private Ranks highestRank;
    private Combinations combination;
}
