package com.dmitrenko.leetcode;

import com.dmitrenko.leetcode.util.CustomFormat;

import java.time.Instant;
import java.util.Map;

public class Application {

    public static void main(String[] args) throws Exception{
        var startF = Instant.now().toEpochMilli();
        for (int i = 0; i < 1000000; i++) {
            String.format("Client not found %s, but found phone %s for client %s", "cl", "+7923", "cl");
        }
        var timeF = Instant.now().toEpochMilli() - startF;

        System.out.println("String.format() 1000000 operations: " + timeF);


        var startC = Instant.now().toEpochMilli();
        for (int i = 0; i < 1000000; i++) {
            CustomFormat.format(
                "Client not found %s, but found phone %d for client %s",
                Map.of("%s", "cl", "%d", "+7923"));
        }
        var timeC = Instant.now().toEpochMilli() - startC;

        System.out.println("CustomFormat.format() 1000000 operations: " + timeC);
    }
}
