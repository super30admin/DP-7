// Time Complexity : O(m*n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

public class EditDistance {
    class Solution {
        public int minDistance(String word1, String word2) {
            if(word1.equals(word2))
                return 0;

            int m = word1.length();
            int n = word2.length();

            //get longest word as first word
            if(n > m)
                minDistance(word2, word1);

            int[] dp = new int[n + 1];

            for(int j = 0; j <= n; j++){
                dp[j] = j;
            }

            for(int i = 1; i <= m; i++){
                int topLeft = dp[0];
                dp[0] = i;
                for(int j = 1; j <= n; j++){
                    int temp = dp[j];
                    if(word1.charAt(i-1) == word2.charAt(j - 1)){
                        dp[j] = topLeft;
                    }
                    else{
                        dp[j] = Math.min(dp[j-1], Math.min(dp[j], topLeft)) + 1;
                    }
                    topLeft = temp;
                }
            }
            return dp[n];
        }
    }
}
