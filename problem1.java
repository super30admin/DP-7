// Time Complexity :O(mn)
// Space Complexity :O(mn)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no

/*
Approach
We use DP tabulation to solve this one
first we make sure the word1 len is high 
we create a dp matrix of m+1,n+1 size (+1 to accomodate for empty string)
we fill it first col with increase amount, as first col is empty we will have to add chars to the array on each size increase

then we start our loops outer loop till len of word1 and inner loop of len word2
over logis is the if both chars are same we go diagonal up and place the same number there, becuase we dont have to do any additional step
if both chars are same

if they are not the same the we take min of (replace(i-1,j-1),delete(i-1,j),insert(i,j-1))
*/
public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        if (m < n) {
            minDistance(word2, word1);
        }
        int[][] dp = new int[m + 1][n + 1];
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[m][n];
    }
}