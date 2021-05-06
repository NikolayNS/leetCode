package com.dmitrenko.leetcode;

import com.dmitrenko.leetcode.merchants.service.GuildService;

public class Application {

    public static void main(String[] args) throws Exception{
        var guild = new GuildService();
        guild.createGuild();

        for (int i = 0; i < 100; i++) {
            var builder = new StringBuilder();
            builder.append("*******************************************************");
            builder.append("\n");

            guild.closeYear()
                    .forEach(o -> {
                        builder.append(o.getName());
                        builder.append("----------");
                        builder.append(o.getWallet());
                        builder.append("\n");
                    });
            builder.append("*******************************************************");
            builder.append("\n");

            guild.cleanUpLosers();

            System.out.println(builder.toString());
            guild.zeroProfit();
        }
    }
}
