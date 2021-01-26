package Trie;

import org.junit.Test;

import java.util.ArrayList;

public class Solution {
    @Test
    public static int minimumLengthEncoding(String[] words) {
        if (words.length == 0) return 0;
        TrieNode root = new TrieNode();
        for (String word : words) {
            root.insert(word);
        }
        int shortestLength = 0;
        shortestLength += dfs(root, 1);
        return shortestLength;
    }

    private static int dfs(TrieNode node, int depth) {
        if (node.childIndex.size() == 0) return depth;
        int ans = 0;
        for (Integer index : node.childIndex) {
            ans += dfs(node.children[index], depth + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        minimumLengthEncoding(new String[]{"time", "me", "bell"});
    }

    private static class TrieNode {
        ArrayList<Integer> childIndex;
        TrieNode[] children = new TrieNode[26];

        public TrieNode() {
            childIndex = new ArrayList<>();
        }

        public void insert(String s) {
            if (s.length() == 0) return;
            int idx = s.charAt(s.length() - 1) - 'a';
            if (!childIndex.contains(idx)) this.childIndex.add(idx);
            if (children[idx] == null) {
                children[idx] = new TrieNode();
            }
            children[idx].insert(s.substring(0, s.length() - 1));
        }
    }
}


