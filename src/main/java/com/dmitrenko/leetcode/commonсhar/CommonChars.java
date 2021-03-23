package com.dmitrenko.leetcode.commonсhar;

import java.util.HashMap;
import java.util.Map;

public class CommonChars {

    int commonCharacterCount(String s1, String s2) {
        int result = 0;
        Map<Character, Integer> firstMap = getMap(s1);
        Map<Character, Integer> secondMap = getMap(s2);

        for(Map.Entry<Character, Integer> entry : firstMap.entrySet()){
            if (secondMap.get(entry.getKey()) != null){
                int min = Math.min(entry.getValue(), secondMap.get(entry.getKey()));
                result = result + min;
            }
        }

        return result;
    }

    private Map<Character, Integer> getMap(String s){
        Map<Character, Integer> tempMap = new HashMap<>();

        for(char c : s.toCharArray()){
            tempMap.putIfAbsent(c, 0);
            tempMap.put(c, tempMap.get(c) + 1);
        }

        return tempMap;
    }
}
