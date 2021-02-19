package com.dmitrenko.leetcode;

import com.dmitrenko.leetcode.atoi.AtoiService;

public class Application {

    public static void main(String[] args) {
        var service = new AtoiService();
        System.out.println(service.myAtoi("+1"));
    }
}
