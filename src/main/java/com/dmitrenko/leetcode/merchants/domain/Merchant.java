package com.dmitrenko.leetcode.merchants.domain;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class Merchant {
    private String name;
    private long wallet;
    private TradeStrategy strategy;
    private RelationshipType relationship;
    private boolean deceived;
}
