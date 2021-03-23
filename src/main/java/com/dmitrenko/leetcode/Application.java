package com.dmitrenko.leetcode;

import com.dmitrenko.leetcode.matrixsort.MatrixSortService;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        var service = new MatrixSortService();
        service
                .diagonalSort(new int[][]{
                        {1, 2, 4, 1},
                        {5, 6, 2, 1},
                        {3, 4, 2, 1},
                        {1, 4, 5, 2}
                });
        /*System.out.println(Arrays
                .deepToString(service
                        .diagonalSort(new int[][]{
                                {1, 2, 4, 1},
                                {5, 6, 2, 1},
                                {3, 4, 2, 1},
                                {1, 4, 5, 2}
                        })));

        System.out.println(Arrays
                .deepToString(service
                        .diagonalSort(new int[][]{
                                {1, 5, 6, 4},
                                {2, 4, 2, 4},
                                {3, 1, 2, 5},
                                {1, 1, 1, 2}
                        })));*/
    }
}
