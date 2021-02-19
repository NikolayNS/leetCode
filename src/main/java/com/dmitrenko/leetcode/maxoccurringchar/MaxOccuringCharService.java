package com.dmitrenko.leetcode.maxoccurringchar;

import java.util.*;

public class MaxOccuringCharService {

    public char maximumOccurringCharacter(String text) {
        char[] chars = text.toCharArray();
        Map<Character, Integer> map = new HashMap<>();

        int i = 1;
        for (char c : chars) {
            Integer count = map.get(c);
            if (count == null) {
                map.put(c, i);
            } else {
                map.put(c, count + 1);
            }
        }

        int max = 0;
        char c = ' ';

        for (Map.Entry<Character, Integer> m : map.entrySet()) {
            if (max < m.getValue()) {
                max = m.getValue();
                c = m.getKey();
            }
        }

        return c;
    }
}
