package com.dmitrenko.leetcode.merchants.service;

import com.dmitrenko.leetcode.merchants.domain.Merchant;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.dmitrenko.leetcode.merchants.domain.TradeStrategy.*;

public class GuildService {

    private final List<Merchant> merchants = new ArrayList<>();
    private long count = 60;

    public void createGuild() {
        for (int i = 0; i < 10; i++) {
            merchants.add(new Merchant().setName(ALTRUIST.getValue() + i).setStrategy(ALTRUIST));
            merchants.add(new Merchant().setName(TRAITOR.getValue() + i).setStrategy(TRAITOR));
            merchants.add(new Merchant().setName(SLY.getValue() + i).setStrategy(SLY));
            merchants.add(new Merchant().setName(UNPREDICTABLE.getValue() + i).setStrategy(UNPREDICTABLE));
            merchants.add(new Merchant().setName(VINDICTIVE.getValue() + i).setStrategy(VINDICTIVE));
            merchants.add(new Merchant().setName(QUIRKY.getValue() + i).setStrategy(QUIRKY));
        }
    }

    public List<Merchant> closeYear() {
        var dealService = new DealService();
        for (int i = 0; i < merchants.size() - 1; i++) {
            for (int j = i + 1; j < merchants.size(); j++) {
                dealService.closeDeals(merchants.get(i), merchants.get(j));
            }
        }
        return merchants;
    }

    public void cleanUpLosers() {
        var sorted = merchants.stream()
                .map(Merchant::getWallet)
                .collect(Collectors.toList())
                .stream()
                .sorted()
                .collect(Collectors.toList());

        var badResults = sorted.subList(0, merchants.size() * 20 / 100);
        var topResults = sorted.subList(merchants.size() - merchants.size() * 20 / 100, merchants.size());

        var losers = merchants.stream()
                .filter(o -> badResults.contains(o.getWallet()))
                .collect(Collectors.toList());
        var top = merchants.stream()
                .filter(o -> topResults.contains(o.getWallet()))
                .collect(Collectors.toList());

        losers.forEach(merchants::remove);
        top.forEach(o -> merchants.add(new Merchant()
                .setName(o.getStrategy().getValue() + count++)
                .setStrategy(o.getStrategy())));
    }

    public void zeroProfit() {
        merchants.forEach(o -> o.setWallet(0));
    }
}
