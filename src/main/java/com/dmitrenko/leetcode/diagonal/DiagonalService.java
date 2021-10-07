package com.dmitrenko.leetcode.diagonal;

import java.util.List;
import java.util.stream.Collectors;

public class DiagonalService {

	public int diagonalDifference(List<List<Integer>> arr) {
		var left = 0;
		var right = 0;

		for (var i = 0; i < arr.size(); i++) {
			left = left + arr.get(i).get(i);
			right = right + arr.get(i).get(arr.size() - i -1);
		}

		var result = left - right;
		return result < 0 ? result * -1 : result;
	}

	public void plusMinus(List<Integer> arr) {
		int positive = 0;
		int negative = 0;
		int zero = 0;

		for (Integer a : arr) {
			if (a > 0) positive = positive + 1;
			if (a < 0) negative = negative + 1;
			if (a == 0) zero = zero + 1;
		}

		System.out.println(positive / arr.size());
		System.out.println(negative / arr.size());
		System.out.println(zero / arr.size());
	}

	public void staircase(int n) {
		for (int i = 1; i <= n; i++) {
			StringBuilder r = new StringBuilder();
			for (int j = 0; j < n - i; j++) {
				r.append(" ");
			}

			for (int j = 0; j < i; j++) {
				r.append("#");
			}

			System.out.println(r);
		}
	}

	public void miniMaxSum(List<Integer> arr) {
		List<Integer> sorted = arr
			.stream()
			.sorted()
			.collect(Collectors.toList());

		long result = sorted.stream().mapToLong(o -> o).sum();

		System.out.println(result - sorted.get(sorted.size() - 1) + " " + (result - sorted.get(0)));
	}

	public int birthdayCakeCandles(List<Integer> candles) {
		int max = candles
			.stream()
			.mapToInt(o -> o)
			.max()
			.getAsInt();

		return (int) candles
			.stream()
			.filter(o -> o == max)
			.count();
	}

	public String timeConversion(String s) {
		String result = s.charAt(8) == 'A'
			? s.substring(0, 8)
			: Integer.parseInt(s.substring(0, 2)) + 12 + s.substring(2, 8);

		result = s.startsWith("12") && s.charAt(8) == 'A'
			? 24 + s.substring(2, 8)
			: s.startsWith("12")
			? 12 + s.substring(2, 8)
			: result;

		return result.startsWith("24")
			? "00" + result.substring(2, 8)
			: result;
	}

	public int chocolateInBox(List<Integer> arr) {
		long r = arr.get(0);
		for (int i = 1; i < arr.size(); i++) {
			r = r ^ arr.get(i);
		}

		int s = 0;

		for (Integer i : arr) {
			if ((r ^ i) < i) s = s + 1;
		}

		return s;
	}

	public List<Integer> gradingStudents(List<Integer> grades) {
		return grades
			.stream()
			.map(o -> {
				if (o < 38) return o;
				int s = o % 5;
				if (s >= 3) {
					o = o + (5 - s);
				}
				return o;
			})
			.collect(Collectors.toList());
	}

	public void countApplesAndOranges(int s, int t, int a, int b, List<Integer> apples, List<Integer> oranges) {
		System.out.println(apples.stream().filter(o -> s <= o + a && o + a <= t).count());
		System.out.println(oranges.stream().filter(o -> s <= o + b && o + b <= t).count());
	}

	public String kangaroo(int x1, int v1, int x2, int v2) {
		String response = "NO";
		boolean canCatchUp = (v2 < v1);
		if(canCatchUp) {
			boolean willIntersectOnLand = (x1 - x2) % (v2 - v1) == 0;
			if(willIntersectOnLand) {
				response = "YES";
			}
		}
		return response;
	}

	public List<Integer> breakingRecords(List<Integer> scores) {
		var max = scores.get(0);
		var maxCount = 0;
		var min = scores.get(0);
		var minCount = 0;
		for (var i = 1; i < scores.size(); i++) {
			if (max < scores.get(i)) {
				max = scores.get(i);
				maxCount++;
			}
			if (min > scores.get(i)) {
				min = scores.get(i);
				minCount++;
			}
		}

		return List.of(maxCount, minCount);
	}

	public int birthday(List<Integer> s, int d, int m) {
		var result = 0;
		for (int i = 0; i < s.size(); i++) {
			var sum = 0;
			for (int j = 0; j < m; j++) {
				if (s.size() <= (i + j)) break;
				sum = sum + s.get(i + j);
			}
			if (d == sum) result++;
		}
		return result;
	}


}
