# DP-7

## Problem1 Edit Distance (https://leetcode.com/problems/edit-distance/)

## Problem2 Regular Expression Matching (https://leetcode.com/problems/regular-expression-matching/)

// Time Complexity = O(M*N)
// Space Complexity = O(M*N)

class Solution {
public int minDistance(String word1, String word2) {
if(word1 == word2) {
return 0;
}

        int m = word2.length();
        int n = word1.length();

        int[][] dp = new int[m + 1][n + 1];

        for(int i = 1 ; i <= m; i++) {
            dp[i][0] = i;
        }

        for(int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }


        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                //not equal
                if(word1.charAt(j-1) != word2.charAt(i-1)) {
                    dp[i][j]=Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]))+1;
                } else {
                //equal
                    dp[i][j] = dp[i-1][j-1];
                }
            }
        }
        return dp[m][n];
    }

}

// Time Complexity = O(M*N)
// Space Complexity = O(M*N)

class Solution {
public boolean isMatch(String s, String p) {
if(s.equals(p)) {
return true;
}

        int slength = s.length();
        int plength = p.length();

        boolean[][] dp = new boolean[slength+1][plength+1];
        dp[0][0] = true;
        for(int i = 1; i < dp[0].length; i++) {
            if(p.charAt(i-1) == '*') {
                dp[0][i] = dp[0][i-2];
            }
        }

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                //not star
                if(p.charAt(j-1) != '*') {
                    if(p.charAt(j-1) == '.' || p.charAt(j-1) == s.charAt(i-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    }
                } else {
                //star
                    dp[i][j] = dp[i][j-2];
                    if(p.charAt(j-2) == '.' || p.charAt(j-2) == s.charAt(i-1)) {
                        dp[i][j] = dp[i][j] || dp[i-1][j];
                    }
                }
            }
        }
        return dp[slength][plength];
    }

}
