package com.dmitrenko.leetcode.luckynum;

import java.util.ArrayList;

public class LuckyService {

	public boolean isLucky(int n) {
		var nums = new ArrayList<>();
		while (true) {
			nums.add(n % 10);
			if (n == 0) break;
		}

		var right = 0;
		var left = 0;
		for (int i = 1; i < nums.size() / 2; i++) {

		}

		return false;
	}
}
