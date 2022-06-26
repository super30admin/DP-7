// Time Complexity : O(n1*n2)
// Space Complexity : O(n1*n2)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach

public int MinDistance(string word1, string word2) {
    if(String.IsNullOrEmpty(word1) || String.IsNullOrEmpty(word1) || word1 == word2)
        return 0;
    
    int n = word1.Length;
    int m = word2.Length;
    
    if(m > n) return MinDistance(word2, word1);
    
    int[,] dp = new int[m + 1, n + 1];        
    
    //fill row
    for(int j = 0; j <= n; j++)
    {
        dp[0, j] = j;
    }
    
    //fill column
    
    for(int i = 0; i <= m; i++)
    {
        dp[i, 0] = i;
    }
    
    
    for(int i = 1; i <= m; i++)
    {
        for(int j = 1; j <= n; j++)
        {
            if(word1[j-1] == word2[i-1])
                dp[i,j] = dp[i-1, j-1];
            else
                dp[i,j] = Math.Min(dp[i-1,j-1], Math.Min(dp[i-1,j], dp[i, j-1])) + 1;
        }
    }
    
    return dp[m,n];
}