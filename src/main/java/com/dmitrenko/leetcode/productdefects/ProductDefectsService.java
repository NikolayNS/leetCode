package com.dmitrenko.leetcode.productdefects;

import java.util.List;

public class ProductDefectsService {
    public static int largestArea(List<List<Integer>> samples) {
        int maxSize = 0;
        int k;

        for (int i = 0; i < samples.size(); i++) {
            for (int j = 0; j < samples.get(i).size(); j++) {
                if (samples.get(i).get(j) == 1) {
                    for (k = j; k < samples.get(i).size(); k++) {
                        if (samples.get(i).get(k) != 1) break;
                    }
                    k = k - j;
                    if (i + k >= samples.size()) k = samples.size() - i;
                    if (check(i, j, k, samples) != 0){
                        if (k > maxSize) maxSize = k;
                    }
                }
            }
        }
        return maxSize;
    }

    private static int check(int x, int y, int size, List<List<Integer>> samples) {
        int i;
        int j;
        int s = 0;
        for (i = x; i < x + size; i++)
        {
            for (j = y; j < y + size; j++)
            {
                s += samples.get(i).get(j);
            }
        }
        return s;
    }
}
