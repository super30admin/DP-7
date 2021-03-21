import java.util.Arrays;
// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : getting started

// Your code here along with comments explaining your approach
public class EditDistance {

    class Solution {
        public int minDistance(String word1, String word2) {

            //create 2d dp array
            int m = word1.length(), n = word2.length();
            int[][] dp = new int[m + 1][n + 1];

            //Initialize dp array with base values
            for (int i = 0; i <= m; i++) {
                Arrays.fill(dp[i], Integer.MAX_VALUE);
                dp[i][0] = i;
            }

            for (int j = 0; j <= n ; j++) {
                dp[0][j] = j;
            }

            //perform dp calculation
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    char c1 = word1.charAt(i);
                    char c2 = word2.charAt(j);

                    if(c1 == c2){
                        dp[i+1][j+1] = dp[i][j];
                    }else{
                        dp[i+1][j+1] = Math.min(dp[i+1][j], Math.min(dp[i][j+1], dp[i][j])) + 1;
                    }

                }
                
            }
            return dp[m][n];
        }
    }

}
