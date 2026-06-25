// Time Complexity : O(m*n) where m is the length of the string and n is the length of the pattern
// Space Complexity : O(m*n) where m is the length of the string and n is the length of the pattern
// Did this code successfully run on Leetcode : YES
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach
// Approach: We will use a 2D boolean array to store the results of subproblems.
//  The first row is initialized based on the pattern.
// Then we iterate through the string and pattern, filling the memoization table based on the matching conditions for characters and the '*' wildcard.
// If the characters match or if the pattern character is '.', we take the value from the diagonal cell.
// If the pattern character is '*', we check if the preceding character matches the current string character or if it is '.', and update the memoization table accordingly. (0 AND 1 CASES)
// the final result will be stored in the bottom-right cell of the memoization table, which indicates whether the entire string matches the entire pattern.         

class Solution {

    boolean[][] memo;

    public boolean isMatch(String s, String p) {

        int m = s.length();
        int n = p.length();

        memo = new boolean[m + 1][n + 1];

        memo[0][0] = true;

        // Initialize first row
        for (int j = 1; j <= n; j++) {

            char pChar = p.charAt(j - 1);

            if (pChar == '*') {
                memo[0][j] = memo[0][j - 2];
            }
        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                char pChar = p.charAt(j - 1);

                if (pChar == '*') {

                    if (p.charAt(j - 2) == s.charAt(i - 1)
                            || p.charAt(j - 2) == '.') {

                        memo[i][j] =
                                memo[i - 1][j] ||
                                memo[i][j - 2];

                    } else {

                        memo[i][j] = memo[i][j - 2];
                    }

                } else {

                    if (pChar == s.charAt(i - 1)
                            || pChar == '.') {

                        memo[i][j] = memo[i - 1][j - 1];

                    } else {

                        memo[i][j] = false;
                    }
                }
            }
        }

        return memo[m][n];
    }
}