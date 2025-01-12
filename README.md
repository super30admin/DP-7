# DP-7

## Problem1 Edit Distance (https://leetcode.com/problems/edit-distance/)
## Time and Space:O(MXN)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];

        // Initialize the first row and first column
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i; // Deleting all characters from word1
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j; // Inserting all characters into word1
        }

        // Fill the DP table
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    // If characters match, no operation needed
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    // Take the minimum of the three operations
                    dp[i][j] = 1 + Math.min(
                        dp[i-1][j-1], // Replace
                        Math.min(dp[i][j-1], // Insert
                                 dp[i-1][j]) // Delete
                    );
                }
            }
        }

        // The result is in dp[m][n]
        return dp[m][n];
    }
}


## Problem2 Regular Expression Matching (https://leetcode.com/problems/regular-expression-matching/)
## Time Complexity and Space:O(mxn) 
class Solution {
    public boolean isMatch(String s, String p) {
       int m=s.length();
       int n=p.length();


       boolean[][] dp=new boolean[m+1][n+1];
       dp[0][0]=true;

       for(int j=1;j<=n;j++)
       {
        char pChar=p.charAt(j-1);
        if(pChar=='*'){
           dp[0][j]=dp[0][j-2];
        }
       }


       for(int i=1;i<=m;i++)
       {
        for(int j=1;j<=n;j++)
        {
            char pChar=p.charAt(j-1);
            if(pChar=='*'){
                if(s.charAt(i-1)==p.charAt(j-2) || p.charAt(j-2)=='.')
                {
                    dp[i][j]=dp[i][j-2] || dp[i-1][j];
                }
                else{
                    dp[i][j]=dp[i][j-2];
                }
            }
            else{
               if(pChar==s.charAt(i-1) || pChar=='.'){
                dp[i][j]=dp[i-1][j-1];
               }
               else{dp[i][j]=false;}
            }
        }
       }

       return dp[m][n];

    }
}