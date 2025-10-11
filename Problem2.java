

//Time Complexity: O(n*m)
//Space Complexity: O(n*m)

//Did this code successfully run on Leetcode : Yes

//We can solve this problem using dynamic programming.And further optimise the space complexity to O(min(n,m)).
//We initialize the first row and column to represent the cost of converting an empty string to the other string.
//Then we iterate through each character of both strings, updating the DP table based on whether the characters match or not,

//if we have a character or dot we check if it matches the current character in s, if it does we take the diagonal value
//if it doesn't we mark it false

//if we have a * we have 2 options, we can either ignore the character before * and the * itself and take the value 2 columns back
//or if the character before * matches the current character in s or is a dot we can take the value from the previous row same column
//this is because we can have multiple occurrences of the character before * matching the current character in s


class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();

        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;

        for (int j = 1; j <= n; j++) {
            char pChar = p.charAt(j - 1);
            if (pChar == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char pChar = p.charAt(j - 1);
                if (pChar == '*') {
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
                    } else {
                        dp[i][j] = dp[i][j - 2];
                    }
                } else {
                    if (pChar == s.charAt(i - 1) || pChar == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }
            }
        }

        return dp[m][n];
    }
}