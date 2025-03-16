// Time Complexity :O(M*N);
// Space Complexity : O(M*N);
// Did this code successfully run on Leetcode :
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        
        for(int i = 1; i <=m; i++){           
                dp[i][0] = i;
        }
        
        for(int j = 1; j <=n; j++){           
                dp[0][j] = j;
        }

        for(int i = 1; i <=m; i++){
            for(int j = 1; j <= n; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
                }
            }
        }

        return dp[m][n];
        /*    "" r    o   s
        ""  0   1   2   3 
        h   1   
        o   2
        r   3
        s   4
        e   5

        h = r , a - >hr = r -> 1 + 1  behind
                d - > "" = r - > 1 + 1 top
                u - > r= r - > 1 + 0 diagonal

        ho = r   */     

    }
}