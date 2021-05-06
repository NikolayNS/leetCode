package com.dmitrenko.leetcode.merchants.service;

import com.dmitrenko.leetcode.merchants.domain.Merchant;
import com.dmitrenko.leetcode.merchants.domain.RelationshipType;

import java.util.Random;

import static com.dmitrenko.leetcode.merchants.domain.RelationshipType.COOPERATION;
import static com.dmitrenko.leetcode.merchants.domain.RelationshipType.SCAM;
import static com.dmitrenko.leetcode.merchants.domain.TradeStrategy.SLY;
import static com.dmitrenko.leetcode.merchants.domain.TradeStrategy.TRAITOR;

public class DealService {

    public void closeDeals(Merchant first, Merchant second) {
        int deals = 5 + new Random().nextInt(5);
        for (int i = 0; i < deals; i++) {
            setCurrentRelationship(first, second, i);
            setCurrentRelationship(second, first, i);
            closeDeal(first, second);
        }
        first.setDeceived(false);
        second.setDeceived(false);
    }

    private void setCurrentRelationship(Merchant merchant, Merchant competitor, int deal) {
        switch (merchant.getStrategy()) {
            case ALTRUIST -> merchant.setRelationship(COOPERATION);
            case TRAITOR -> merchant.setRelationship(SCAM);
            case SLY -> merchant.setRelationship(deal == 0 ? COOPERATION : competitor.getRelationship());
            case UNPREDICTABLE -> merchant.setRelationship(RelationshipType.getRandom());
            case VINDICTIVE -> merchant.setRelationship(merchant.isDeceived() ? SCAM : COOPERATION);
            case QUIRKY -> quirkyStrategy(merchant, competitor, deal);
            default -> throw new IllegalStateException("Unexpected value: " + merchant.getStrategy());
        }
    }

    private void quirkyStrategy(Merchant merchant, Merchant competitor, int deal) {
        if (deal > 3) {
            merchant.setStrategy(merchant.isDeceived() ? TRAITOR : SLY);
            setCurrentRelationship(merchant, competitor, deal);
        } else if (deal == 1) {
            merchant.setRelationship(SCAM);
        } else {
            merchant.setRelationship(COOPERATION);
        }
    }

    private void closeDeal(Merchant first, Merchant second) {
        wrong(first);
        wrong(second);

        if (first.getRelationship() == COOPERATION && second.getRelationship() == COOPERATION) {
            first.setWallet(first.getWallet() + 4);
            second.setWallet(second.getWallet() + 4);
        }
        if (first.getRelationship() == SCAM && second.getRelationship() == SCAM) {
            first.setWallet(first.getWallet() + 2);
            first.setDeceived(true);
            second.setWallet(second.getWallet() + 2);
            second.setDeceived(true);
        }
        if (first.getRelationship() == COOPERATION && second.getRelationship() == SCAM) {
            first.setWallet(first.getWallet() + 1);
            first.setDeceived(true);
            second.setWallet(second.getWallet() + 5);
        }
        if (first.getRelationship() == SCAM && second.getRelationship() == COOPERATION) {
            first.setWallet(first.getWallet() + 5);
            second.setWallet(second.getWallet() + 1);
            second.setDeceived(true);
        }
    }

    private void wrong(Merchant merchant) {
        boolean probability = new Random().nextInt(100) < 5;
        if (probability) {
            merchant.setRelationship(merchant.getRelationship() == SCAM ? COOPERATION : SCAM);
        }
    }
}
