package Trie;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

class SolutionTest {

    @Test
    void minimumLengthEncoding() {
        Solution solution = new Solution();
        int res = solution.minimumLengthEncoding(new String[]{"time", "me", "bell"});
        assertEquals(10,res);
    }
}