# DP-7

## Problem1 Edit Distance (https://leetcode.com/problems/edit-distance/)

Explain approach in 3 lines: In this solution we are using dynammic programming with a 1D array to compute the minimum edit distance between two words. We will iteratively build up the solution by comparing characters of two strings and updating the array based on the minimum operations needed which will be insert, delete or replace. To calculate actions required in each case we are looking at elements like Edit --> Diagnoal Up Element, Add --> Just Above Element, Delete --> Left element and Skip when characters match --> Diagonal Up Element.

Time Complexity: O(n*m) where n is a length of word1 string and m is a length of word2 string

Space Complexity: O(n) as after optimization we are storing the dp values in a iD array

Approached Code:

class Solution {
    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // int[][] dp = new int[m+1][n+1];
        int[] dp = new int[n+1];

        for(int i = 0; i <= n; i++) {
            // dp[0][i] = i;
            dp[i] = i;
        }

        // for(int i = 0; i <= m; i++) {
        //     dp[i][0] = i;
        // }

        for(int i = 1; i < m+1; i++) {
            char c2 = word2.charAt(i-1);
            int diagUp = dp[0];
            dp[0] = i;

            for(int j = 1; j < n+1; j++) {
                char c1 = word1.charAt(j-1);
                int temp = dp[j];

                if(c1 == c2) {
                    // dp[i][j] = dp[i-1][j-1];
                    dp[j] = diagUp;
                } else {
                    // dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
                    dp[j] = 1 + Math.min(diagUp, Math.min(dp[j], dp[j-1]));
                }

                diagUp = temp;

            }
        }

        // return dp[m][n];
        return dp[n];
        
    }
}


## Problem2 Regular Expression Matching (https://leetcode.com/problems/regular-expression-matching/)

Explain approach in 3 lines: In this solution we are using Dynammic Programming to determine if string s matches the pattern p with . and *. We will use 1D DP array dp where dp[j] represents whether the first j characters of p can match the corresponding prefix of s. We will initialize dp[0] as true and will handle cases where * can match zero preceding characters. We will iterate through strings s and pattern p processing one character at a time. For * we will check if it matches zero occurences or matches at least one remains true if p[j-2] matches s[i-1]. For . or a direct character match we will update dp[j] based on dp[j-1] from the previous row. We will use a variable diagUp to track the diagonal value for handling single-character matches.

Time Complexity: O(m*n) where m is length of s and n is length of p as we are iterating through all the characters.

Space Complexity: O(n) as we are using single array to store dp matrix.

Approached Code:

class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        // boolean[][] dp = new boolean[m+1][n+1];
        boolean[] dp = new boolean[n+1];

        // dp[0][0] = true;
        dp[0] = true;

        for(int j = 1; j <= n; j++) {
            if(p.charAt(j-1) == '*') {
                // dp[0][j] = dp[0][j-2];
                dp[j] = dp[j-2];
            }
        }

        for(int i = 1; i <= m; i++) {
            boolean diagUp = dp[0];
            dp[0] = false;
            
            for(int j = 1; j <= n; j++) {
                boolean temp = dp[j];

                if(p.charAt(j-1) == '*') {
                    if(p.charAt(j-2) == s.charAt(i-1) || p.charAt(j-2) == '.') {
                        // dp[i][j] = dp[i][j-2] || dp[i-1][j];
                        dp[j] = dp[j-2] || dp[j];
                    } else {
                        // dp[i][j] = dp[i][j-2];
                        dp[j] = dp[j-2];
                    }
                } else {
                    if(p.charAt(j-1) == s.charAt(i-1) || p.charAt(j-1) == '.') {
                        // dp[i][j] = dp[i-1][j-1];
                        dp[j] = diagUp;
                    } else {
                        // dp[i][j] = false;
                        dp[j] = false;
                    }
                }

                diagUp = temp;
            }
        }
        // return dp[m][n];
        return dp[n];
    }
}


