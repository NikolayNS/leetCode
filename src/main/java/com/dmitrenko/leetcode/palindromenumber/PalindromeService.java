package com.dmitrenko.leetcode.palindromenumber;

import java.util.ArrayList;
import java.util.List;

public class PalindromeService {

    public boolean isPalindrome(int x) {
        if(x >= 0 && x < 10) return true;
        if (x < 0 || x == 10) return false;

        List<Integer> xList = new ArrayList<>();

        while (x != 0) {
            xList.add(x % 10);
            x = x / 10;
        }

        for (int i = 0; i < xList.size() / 2 ; i++) {
            if (!xList.get(i).equals(xList.get(xList.size() - i - 1))) {
                return false;
            }
        }

        return true;
    }
}
