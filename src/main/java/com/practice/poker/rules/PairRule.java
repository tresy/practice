package com.practice.poker.rules;

public class PairRule extends CountRule {
    @Override
    Long getCount() {
        return 2L;
    }
}
