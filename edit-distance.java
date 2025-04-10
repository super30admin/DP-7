// TC: O(n*m)
// SC: O(n*m)   
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] memo = new int[m][n];
        return helper(word1, word2, 0, 0, memo);
    }
    
    private int helper(String word1, String word2, int i, int j, int[][] memo) {
        // Base cases: if one word is exhausted, return the remaining length of the other.
        if (i == word1.length()) {
            return word2.length() - j;
        }
        if (j == word2.length()) {
            return word1.length() - i;
        }
        
        // If the value has been computed before, return it.
        if (memo[i][j] != 0) {
            return memo[i][j];
        }
        
        int result;
        // If characters match, move to the next pair.
        if (word1.charAt(i) == word2.charAt(j)) {
            result = helper(word1, word2, i + 1, j + 1, memo);
        } else {
            // Compute the three possible operations:
            // 1. Insertion: add 1 and try matching word1[i] with word2[j+1].
            int case1 = 1 + helper(word1, word2, i, j + 1, memo);
            // 2. Deletion: add 1 and try matching word1[i+1] with word2[j].
            int case2 = 1 + helper(word1, word2, i + 1, j, memo);
            // 3. Replacement: add 1 and try matching word1[i+1] with word2[j+1].
            int case3 = 1 + helper(word1, word2, i + 1, j + 1, memo);
            result = Math.min(case1, Math.min(case2, case3));
        }
        
        memo[i][j] = result;
        return result;
    }
}
