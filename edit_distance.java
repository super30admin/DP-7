// Time Complexity : O(m * n) because each pair of indices is computed once
// Space Complexity : O(m * n) for memoization and recursion stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use DFS with memoization where helper(i, j) gives the minimum operations to convert word1 from i into word2 from j.
// If characters match, we move both pointers without adding any operation.
// Otherwise, we try insert, replace, and delete operations and take the minimum result.

class Solution {
    Integer[][] memo;

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        this.memo = new Integer[m][n];

        return helper(word1, word2, 0, 0);
    }

    private int helper(String word1, String word2, int i, int j) {

        if(i == word1.length()) return word2.length() - j;
        if(j == word2.length()) return word1.length() - i;

        if(memo[i][j] != null) return memo[i][j];

        int result = 0;

        if(word1.charAt(i) == word2.charAt(j)) {
            result = helper(word1, word2, i + 1, j + 1);
        } else {
            // try insert, replace, delete
            int add = 1 + helper(word1, word2, i + 1, j);
            int replace = 1 + helper(word1, word2, i + 1, j + 1);
            int delete = 1 + helper(word1, word2, i, j + 1);

            result = Math.min(add, Math.min(replace, delete));
        }

        memo[i][j] = result;
        return result;
    }
}