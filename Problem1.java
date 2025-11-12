// Time Complexity : O(mn) where m and n are lengths of word1 and word2
// Space Complexity : O(min(m,n))
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Using a dp matrix/array to keep track of the minimum distance at every point in the iteration.
 * if current char matches, then we have already calculated that problem. "-hor" -> "-r". Since 'r' matches, we have to match '-ho' -> '-'
 * If current char doesn't match
 *  then get the minimum of update, delete, add cases and add 1 to it
 *  update case is diagonal up left. "-h" -> "-r". if we update h to r, then "-" -> "-" needs to be matched
 *  add case is up. "-h" -> "-r" will require adding r to do "-hr"->"r" and then "-h"->"-" needs to be matched
 *  delete case is left. "-h"->"-r" will require deleting h to do "-"->"-r" and then "-"->"-r" needs to be matched
 */
class DpSolution {
    int[] dp;

    public int minDistance(String word1, String word2) {
        if (word2.length() > word1.length()) {
            return minDistance(word2, word1);
        }

        int m = word1.length();
        int n = word2.length();
        this.dp = new int[n + 1];

        // first row will be value of jth index since "" -> "abc" requires 3 modifications
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= m; i++) {
            int diagUp = dp[0];
            // same reasoning as above where "abc" -> "" requires 3 modifications
            dp[0] = i;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                // if current char matches, then rest of the calculation is diagonal up in matrix
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = diagUp;
                } else {
                    // if the char doesn't match, then we have to get the minimum number of steps from 3 operations
                    // dp[j] is add case
                    // diagUp is update case
                    // dp[j-1] is delete case
                    dp[j] = 1 + Math.min(dp[j], Math.min(diagUp, dp[j - 1]));
                }
                diagUp = temp;
            }
        }

        return dp[n];
    }
}

// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

/**
 * Using memoization to calculate the number of modifications required in bottom up manner. Same logic as dp.
 */
class MemoSolution {
    int m;
    int n;
    int[][] memo;

    public int minDistance(String word1, String word2) {
        m = word1.length();
        n = word2.length();
        if (n > m) {
            return minDistance(word2, word1);
        }
        this.memo = new int[m][n];
        return helper(word1, word2, 0, 0);
    }

    private int helper(String word1, String word2, int i, int j) {
        // if i or j finishes, we still require rest of the modification steps from the other string
        if (i == m)
            return n - j;
        if (j == n)
            return m - i;

        if (memo[i][j] != 0)
            return memo[i][j];

        int res = 0;
        // char matches, then move to next char for both words
        if (word1.charAt(i) == word2.charAt(j)) {
            res = helper(word1, word2, i + 1, j + 1);
        } else {
            // delete
            int case1 = helper(word1, word2, i + 1, j);
            // add
            int case2 = helper(word1, word2, i, j + 1);
            // update
            int case3 = helper(word1, word2, i + 1, j + 1);

            res = 1 + Math.min(case1, Math.min(case2, case3));
        }

        memo[i][j] = res;
        return res;
    }
}