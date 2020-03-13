package com.practice.poker.rules;

import com.practice.poker.model.Combinations;
import com.practice.poker.model.Hand;
import com.practice.poker.model.HandRank;

import java.util.Optional;

public interface Rule {
    Optional<HandRank> match(Hand hand);
    Combinations getCombination();
}
