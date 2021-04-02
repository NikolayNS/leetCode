package com.dmitrenko.leetcode.imagereader;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    private final TrieNode root = new TrieNode();

    public void add(String name, String symbol) {
        TrieNode terminalPoint = addToTrie(symbol);
        terminalPoint.terminating = name;
    }

    private TrieNode addToTrie(String symbol) {
        TrieNode current = root;
        char[] wordChars = symbol.toCharArray();
        for (char wordChar : wordChars) {
            if (!current.children.containsKey(wordChar)) {
                TrieNode child = new TrieNode();
                child.parent = current;
                child.parentChar = wordChar;
                current.children.put(wordChar, child);
            }
            current = current.children.get(wordChar);
        }
        return current;
    }

    public String findNames(String cards) {
        StringBuilder res = new StringBuilder();
        TrieNode current = root;
        char[] lineChars = cards.toCharArray();
        for (char lineChar : lineChars) {
            if (current.children.containsKey(lineChar)) {
                current = current.children.get(lineChar);
                if (current.terminating != null) {
                    res.append(current.terminating);
                }
            } else {
                current = followTheLink(current, lineChar);
            }
        }
        return res.toString();
    }

    private TrieNode followTheLink(TrieNode node, Character wordChar) {
        if (node.suffixJump.get(wordChar) == null) {
            if (node.children.get(wordChar) != null) {
                node.suffixJump.put(wordChar, node.children.get(wordChar));
            } else if (node == root) {
                node.suffixJump.put(wordChar, root);
            } else {
                node.suffixJump.put(wordChar, followTheLink(buildSuffixLink(node), wordChar));
            }
        }
        return node.suffixJump.get(wordChar);
    }

    private TrieNode buildSuffixLink(TrieNode node) {
        if (node.suffixLink == null) {
            if (node != root && node.parent != root) {
                node.suffixLink = followTheLink(buildSuffixLink(node.parent), node.parentChar);
            } else {
                node.suffixLink = root;
            }
        }
        return node.suffixLink;
    }

    private static class TrieNode {
        private char parentChar;
        private Map<Character, TrieNode> children = new HashMap<>();
        private Map<Character, TrieNode> suffixJump = new HashMap<>();
        private String terminating;
        private TrieNode parent;
        private TrieNode suffixLink;

        private TrieNode() {
        }
    }
}
