package com.dmitrenko.leetcode.matrixsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixSortService {

    public int[][] diagonalSort(int[][] matrix){
        List<List<Integer>> tempMatrix = new ArrayList<>();

        for (int j = 0; j < matrix[0].length; j++) {
            tempMatrix.add(getReverseSortListFromMatrix(matrix, 0, j));
        }

        for (int i = 1; i < matrix.length; i++) {
            tempMatrix.add(getReverseSortListFromMatrix(matrix, i, matrix[i].length - 1));
        }

        System.out.println(tempMatrix.toString());

        int[][] result = new int[matrix.length][matrix[0].length];

        int l = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                //result[i][j] = tempMatrix.get()
            }
            l++;
        }

        return result;
    }

    private List<Integer> getReverseSortListFromMatrix(int[][] matrix, int k, int l) {
        List<Integer> tempList = new ArrayList<>();
        while (l >= 0 && k < matrix.length) {
            tempList.add(matrix[k][l]);
            k++;
            l--;
        }
        Collections.sort(tempList);
        Collections.reverse(tempList);
        return tempList;
    }
}
