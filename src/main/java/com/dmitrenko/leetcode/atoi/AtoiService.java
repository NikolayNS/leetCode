package com.dmitrenko.leetcode.atoi;

public class AtoiService {

    public int myAtoi(String s) {
        if (s.isBlank()) return 0;

        StringBuilder buffer = new StringBuilder();
        var chars = s.toCharArray();

        boolean spaceState = false;
        if (chars[0] == ' ') spaceState = true;

        for (char aChar : chars) {
            if (spaceState) {
                if (checkSymbol(aChar) || aChar == '-' || aChar == '+') {
                    buffer.append(aChar);
                    spaceState = false;
                }
            } else {
                if (!checkSymbol(aChar)) break;
                buffer.append(aChar);
                spaceState = false;
            }
        }

        if (buffer.toString().isBlank()) return 0;

        long result;
        try {
            result = Long.parseLong(buffer.toString());
        } catch (Exception e) {
            if(buffer.toString().startsWith("-")) return Integer.MIN_VALUE;
            return Integer.MAX_VALUE;
        }

        if (result > Integer.MAX_VALUE) return Integer.MAX_VALUE;
        if (result < Integer.MIN_VALUE) return Integer.MIN_VALUE;

        return (int) result;
    }

    private boolean checkSymbol(char symbol) {
        return symbol == '0' ||
                symbol == '1' ||
                symbol == '2' ||
                symbol == '3' ||
                symbol == '4' ||
                symbol == '5' ||
                symbol == '6' ||
                symbol == '7' ||
                symbol == '8' ||
                symbol == '9' ||
                symbol == '-' ||
                symbol == '+';
    }
}
