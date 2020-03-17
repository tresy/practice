package com.practice.poker2.rules;

public class DrillRule extends CountRule implements Rule {
    @Override
    public Long getCount() {
        return 3L;
    }
}
