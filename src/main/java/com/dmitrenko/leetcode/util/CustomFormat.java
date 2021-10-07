package com.dmitrenko.leetcode.util;

import java.util.HashMap;
import java.util.Map;

public class CustomFormat {

	private CustomFormat() {
	}

	public static String format(String source, Map<String, Object> vars) {
		var root = getRoot();

		vars.forEach((k, v) -> addWord(root, k, v.toString()));

		return replaceWords(root, source);
	}

	private static TrieNode getRoot() {
		var root = new TrieNode();
		var space = new TrieNode();

		space.parent = root;
		root.children.put(' ', space);
		return root;
	}

	private static void addWord(TrieNode root, String replaceableWord, String replacementWord) {
		var terminalPoint = addToTrie(root, replaceableWord);
		terminalPoint.replacementWord = replacementWord;
	}

	private static TrieNode addToTrie(TrieNode root, String word) {
		var current = root.children.get(' ');
		char[] chars = word.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			if (notLetter(chars[i])) chars[i] = ' ';
			if (!(current.children.containsKey(chars[i]))) {
				var child = new TrieNode();
				child.depth = i;
				child.parent = current;
				child.parentChar = chars[i];
				current.children.put(chars[i], child);
			}
			current = current.children.get(chars[i]);
		}
		return current;
	}

	private static String replaceWords(TrieNode root, String line) {
		var result = new StringBuilder();
		char[] lineChars = line.toCharArray();
		var current = root.children.get(' ');

		int left = 0;
		for (int right = 0; right <line.length() ; right++) {
			char tmp = lineChars[right];
			if (notLetter(lineChars[right])) {
				tmp = lineChars[right];
				lineChars[right] = ' ';
			}
			if (current.children.containsKey(lineChars[right])) {
				current = current.children.get(lineChars[right]);
				lineChars[right] = tmp;
				if (right == lineChars.length - 1) {
					if (current.replacementWord != null) {
						result.append(current.replacementWord);
						break;
					}
					while (left <= right) {
						result.append(lineChars[left]);
						left++;
					}
				}
				int depth = current.depth;
				while (left < (right - depth)) {
					result.append(lineChars[left]);
					left++;
				}
			} else {
				if (notLetter(lineChars[right]) && current.replacementWord != null) {
					result.append(current.replacementWord);
					left = right;
				}
				current = followTheLink(root, current, lineChars[right]);
				lineChars[right] = tmp;
				while (left <= right) {
					result.append(lineChars[left]);
					left++;
				}
			}
		}
		return result.toString();
	}

	private static TrieNode followTheLink(TrieNode root, TrieNode node, char wordChar) {
		if (node.suffixJump.get(wordChar) == null) {
			if (node.children.get(wordChar) != null) {
				node.suffixJump.put(wordChar, node.children.get(wordChar));
			} else if (node == root) {
				node.suffixJump.put(wordChar, root);
			} else {
				node.suffixJump.put(wordChar, followTheLink(root, buildSuffixLink(root, node), wordChar));
			}
		}
		return node.suffixJump.get(wordChar);
	}

	private static TrieNode buildSuffixLink(TrieNode root, TrieNode node) {
		if (node.suffixLink == null) {
			node.suffixLink = (node != root && node.parent != root)
				? followTheLink(root, buildSuffixLink(root, node.parent), node.parentChar)
				: root;
		}
		return node.suffixLink;
	}

	private static boolean notLetter(char symbol) {
		return symbol == ' ' || symbol == ',' || symbol == '.' || symbol == '_' || symbol == ':'
			|| symbol == ';' || symbol == '\'' || symbol == '\"' || symbol == '(' || symbol == ')'
			|| symbol == '[' || symbol == ']' || symbol == '{' || symbol == '}' || symbol == '/'
			|| symbol == '\\' || symbol == '?' || symbol == '!' || symbol == '^' || symbol == '$'
			|| symbol == '#' || symbol == '*' || symbol == '+' || symbol == '-' || symbol == '~'
			|| symbol == '<' || symbol == '=' || symbol == '>';
	}

	private static class TrieNode {
		private int depth;
		private char parentChar;

		private String replacementWord;
		private TrieNode parent;
		private TrieNode suffixLink;
		private final Map<Character, TrieNode> children = new HashMap<>();
		private final Map<Character, TrieNode> suffixJump = new HashMap<>();
	}
}
