/*
Memoization 

TC - O(m*n)
SC - O(m*n)
 */

class Solution {

    public int minDistance(String word1, String word2) {

        int m = word1.length();

        int n = word2.length();

        int[][] memo = new int[m][n];

        return helper(word1, word2, 0, 0, memo);

    }

    private int helper(String word1, String word2, int i, int j, int[][] memo) {

        // base

        if (i == word1.length()) {

            return word2.length() - j;

        }

        if (j == word2.length()) {

            return word1.length() - i;

        }

        if (memo[i][j] != 0)
            return memo[i][j];

        // logic

        int result = 0;

        if (word1.charAt(i) == word2.charAt(j)) {

            result = helper(word1, word2, i + 1, j + 1, memo);

        } else {

            //add

            int case1 = 1 + helper(word1, word2, i, j + 1, memo);

            //delete 

            int case2 = 1 + helper(word1, word2, i + 1, j, memo);

            //update

            int case3 = 1 + helper(word1, word2, i + 1, j + 1, memo);

            result = Math.min(case1, Math.min(case2, case3));

        }

        memo[i][j] = result;

        return result;

    }

}