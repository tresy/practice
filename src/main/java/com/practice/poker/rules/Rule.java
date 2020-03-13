package com.practice.poker.rules;

import com.practice.poker.model.Combinations;
import com.practice.poker.model.Hand;

public interface Rule {
    boolean match(Hand hand);
    Combinations getCombination();
}
