package com.dmitrenko.leetcode.merchants.domain;

import java.util.Random;

public enum RelationshipType {
    COOPERATION,
    SCAM;

    public static RelationshipType getRandom() {
        return RelationshipType.values()[new Random().nextInt(2)];
    }
}
