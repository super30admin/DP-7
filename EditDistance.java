// Time Complexity :O(m*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :No


// Your code here along with comments explaining your approach
/*
 * 1 - If the character is same, then take the diagonal value
 * 2 - If the character is not the same, we have 3 choices - add(above element); delete(left element); update(diagonal up)
 * Take the minimum and add 1 to it because of doing the operation.
 */
public class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2))
            return 0;
        
        int m = word2.length();
        int n = word1.length();
        int dp[][] = new int[m+1][n+1];
        
        for(int i=0; i<=m; i++)
        {
            dp[i][0] = i;
        }
        
        for(int j=0; j<=n; j++)
        {
            dp[0][j] = j;
        }
        
        for(int i=1; i<=m; i++)
        {
            for(int j=1; j<=n; j++)
            {
                if(word1.charAt(j-1) == word2.charAt(i-1))
                {
                    dp[i][j] = dp[i-1][j-1];
                }
                else
                {
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]));
                }
            }
        }
        return dp[m][n];
    }
}
