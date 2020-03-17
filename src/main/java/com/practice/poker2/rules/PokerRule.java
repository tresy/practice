package com.practice.poker2.rules;

public class PokerRule extends CountRule implements Rule {
    @Override
    public Long getCount() {
        return 4L;
    }
}
