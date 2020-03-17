package com.practice.poker2.rules;

import com.practice.poker2.model.Combinations;
import com.practice.poker2.model.Hand;

public interface Rule {
    boolean match(Hand hand);
    Combinations getCombination();
}
