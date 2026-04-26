// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

// Your code here along with comments explaining your approach
/*
We try all possible operations by converting one word to the other. If the characters match, we recursively
proceed, if not, we take minimum of the 3 operations.We leverage memo array to refer to the already computed
subproblems.
 */
class Solution {
    Integer[][] memo;
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        this.memo = new Integer[m][n];
        return helper(word1, word2, 0 , 0);
    }

    private int helper(String word1, String word2, int i , int j) {
        if(i == word1.length() && j == word2.length())
            return 0;
        if(i == word1.length())
            return word2.length() - j;

        if(j == word2.length())
            return word1.length() - i;

        if(memo[i][j] != null)
            return memo[i][j];

        int result = 0;
        if(word1.charAt(i) == word2.charAt(j)) {
            result = helper(word1, word2, i + 1, j + 1);
        }
        else {
            int add = 1 + helper(word1, word2, i + 1, j);
            int edit = 1 + helper(word1, word2, i + 1, j + 1);
            int delete = 1 + helper(word1, word2, i, j + 1);
            result = Math.min(add, Math.min(edit, delete));
        }
        memo[i][j] = result;
        return result;
    }
}