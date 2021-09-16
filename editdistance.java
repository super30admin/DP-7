// Time Complexity : o(m*n)
// Space Complexity : o(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach
class Solution {
    public int minDistance(String word1, String word2) {
        
        if(word1 == word2)
        {
            return 0;
        }

        int m = word2.length();
        int n = word1.length();

        int[][] dp = new int[m + 1][n + 1];
        
        for(int i=1;i<=m;i++)
        {
            dp[i][0]=i;
        }
        
        for(int j=1;j<=n;j++)
        {
            dp[0][j]=j;
        }
        
        for(int i=1;i<=m;i++)
        {
            for(int j=1;j<=n;j++)
            {
                //not equal
                if(word1.charAt(j-1)!=word2.charAt(i-1))
                {
                    dp[i][j]=1+ Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1]);
                }
                else
                {
                    //equal
                    
                    dp[i][j]=dp[i-1][j-1];
                }
            }
        }
        
         return dp[m][n];
    }
}