package com.practice.poker2.rules;

public class PairRule extends CountRule implements Rule {
    @Override
    public Long getCount() {
        return 2L;
    }
}
