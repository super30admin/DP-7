// Time Complexity : O(m * n) because each pair of indices is computed once
// Space Complexity : O(m * n) for memoization and recursion stack
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No


// Your code here along with comments explaining your approach in three sentences only
// We use DFS with memoization where helper(i, j) checks whether s from index i matches p from index j.
// firstMatch checks if the current characters match directly or through '.'.
// If the next pattern character is '*', we either skip the star pattern or use it if the current character matches.

class Solution {
    Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        this.memo = new Boolean[s.length() + 1][p.length() + 1];
        return helper(s, p, 0, 0);
    }

    private boolean helper(String s, String p, int i, int j) {

        if(i == s.length() && j == p.length()) return true;
        if(j == p.length()) return false;

        if(memo[i][j] != null) return memo[i][j];

        boolean firstMatch = i < s.length() &&
                (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        boolean result = false;

        if(j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // skip '*' pattern or use it once
            result = helper(s, p, i, j + 2) ||
                     (firstMatch && helper(s, p, i + 1, j));
        } else {
            if(firstMatch) {
                result = helper(s, p, i + 1, j + 1);
            }
        }

        memo[i][j] = result;
        return result;
    }
}