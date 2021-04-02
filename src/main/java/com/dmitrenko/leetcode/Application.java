package com.dmitrenko.leetcode;

import com.dmitrenko.leetcode.imagereader.Constant;
import com.dmitrenko.leetcode.imagereader.ImageReaderImpl;
import com.dmitrenko.leetcode.imagereader.Trie;

public class Application {

    public static void main(String[] args) throws Exception{
        var reader = new ImageReaderImpl();
        var trie = new Trie();

        Constant.CARDS_MAP.forEach(trie::add);

        var cards = reader.readImage("C:/Users/dmitrenko/Downloads/Ks10sJs.png");

        System.out.println(trie.findNames(cards));
    }
}
