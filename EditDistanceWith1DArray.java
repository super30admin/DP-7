// Time Complexity : O(m*n)
// Space Complexity : O(n)
// Did this code successfully run on Leetcode : Yes

public class EditDistanceWith1DArray {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        int[] dp = new int[n+1];

        for(int j=0; j<=n; j++)
        {
            dp[j] = j;
        }

        for(int i=1; i<=m; i++){
            int diagUp = dp[0];
            for(int j=0; j<=n; j++){
                int temp = dp[j];
                if(j == 0){
                    dp[j] = i;
                }else{
                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[j] = diagUp;
                    }else{
                        dp[j] = 1 + Math.min(diagUp,Math.min(dp[j-1],dp[j]));
                    }
                }
                diagUp = temp;
            }
        }

        return dp[n];
    }
}
