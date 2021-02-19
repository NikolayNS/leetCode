package com.dmitrenko.leetcode.romantointeger;

import java.util.HashMap;
import java.util.Map;

public class RomanToIntegerService {
    public int romanToInt(String s) {
        Map<String, Integer> romanSymbols = getRomanSymbols();
        String[] symbols = s.split("");

        int result = 0;
        String symbol;
        for (int i = 0; i < symbols.length; i++) {
            symbol = symbols[i];
            if (i == symbols.length - 1) {
                result = romanSymbols.containsKey(symbol)
                        ? result + romanSymbols.get(symbol)
                        : 0;
                return result;
            }

            if (romanSymbols.containsKey(symbol + symbols[i + 1])) {
                result = result + romanSymbols.get(symbol + symbols[i + 1]);
                i++;
            } else if (romanSymbols.containsKey(symbol)) {
                result = result + romanSymbols.get(symbol);
            } else {
                return 0;
            }
        }

        return result;
    }

    private Map<String, Integer> getRomanSymbols() {
        Map<String, Integer> symbolsMap = new HashMap<>();
        symbolsMap.put("I", 1);
        symbolsMap.put("IV", 4);
        symbolsMap.put("V", 5);
        symbolsMap.put("IX", 9);
        symbolsMap.put("X", 10);
        symbolsMap.put("XL", 40);
        symbolsMap.put("L", 50);
        symbolsMap.put("XC", 90);
        symbolsMap.put("C", 100);
        symbolsMap.put("CD", 400);
        symbolsMap.put("D", 500);
        symbolsMap.put("CM", 900);
        symbolsMap.put("M", 1000);
        return symbolsMap;
    }
}
