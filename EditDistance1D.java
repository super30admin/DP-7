class Solution {
    //tc-o(mn) sc-o(n)
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[]dp = new int [n+1];

        for(int j=0;j<dp.length;j++)
        {
            dp[j] = j;
        }

        for(int i=1;i<=m;i++)
        {
            int diagonal = dp[0];
            for(int j=0;j<=n;j++)
            {
                if(j==0) dp[j] = i;
                else
                {
                    int temp = dp[j];
                    if(word1.charAt(i-1) == word2.charAt(j-1))
                    {
                        dp[j] = diagonal;
                    }
                    else{
                        dp[j] = 1+ Math.min(dp[j], Math.min(diagonal, dp[j-1]));
                    }
                    diagonal = temp;
                }
            }

        }
        return dp[n];
    }
        
}