package com.dmitrenko.leetcode.merchants.domain;

public enum TradeStrategy {
    ALTRUIST("ALTRUIST"),
    TRAITOR("TRAITOR"),
    SLY("SLY"),
    UNPREDICTABLE("UNPREDICTABLE"),
    VINDICTIVE("VINDICTIVE"),
    QUIRKY("QUIRKY");

    private final String value;

    TradeStrategy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
