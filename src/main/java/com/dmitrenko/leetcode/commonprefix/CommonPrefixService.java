package com.dmitrenko.leetcode.commonprefix;

public class CommonPrefixService {

	public String longestCommonPrefix(String[] strs) {
		var result = "";

		char[] main = strs[0].toLowerCase().toCharArray();
		for (var i = 1; i < strs.length - 1; i++) {
			char[] s = strs[i].toLowerCase().toCharArray();

			for (var j = 0; j < s.length; j++) {
				if (main[j] != s[j]) {
					result = strs[i].substring(0, j - 1);
				}
			}
		}

		return result;
	}
}
