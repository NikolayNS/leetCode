package com.dmitrenko.leetcode.minx;

import java.util.List;

public class MinXService {

    public static int minX(List<Integer> arr) {
        for (int x = 0; x <= Integer.MAX_VALUE; x++) {
            int sum = x;
            for (int n : arr) {
                sum += n;
                if (sum < 1) {
                    break;
                }
            }
            if (sum >= 1) {
                return x;
            }
        }
        return 0;
    }
}
