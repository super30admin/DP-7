// Time Complexity : O(MN)
// Space Complexity : O(MN)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach

class Solution {

    int[][] dp;
    // e.g ==> aab || c * a * b
    public boolean dfs(String s, String p, int i, int j) {

        // base case
        // if both goes out of bound that means string is matched
        if (i >= s.length() && j >= p.length()) return true;

        // if only pattern goes oob means String is yet to be matched fully
        if (j >= p.length()) return false;

        if (dp[i][j] != -1) return dp[i][j] == 1 ? true : false;

        // logic
        boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

        // lets first check the "*" -> this is root of everything
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            // we have 2 options :: not choose  or  choose (only if match happens)
            boolean test_1 = (dfs (s, p , i, j + 2) || (match && dfs (s, p, i + 1, j)));
            dp[i][j] = test_1 == true ? 1 : 0;
            return test_1;
        }

        // if its not * -> normal char and matched move forward
        if (match) {
            boolean test2 = dfs(s, p, i+1, j+1);
            dp[i][j] = test2 == true ? 1 : 0;
            return test2;
        }

        dp[i][j] = 0;
        return false;

    }

    public boolean isMatch(String s, String p) {

        if (s.equals(p)) return true;
        this.dp = new int[s.length() + 1][p.length() + 1];
        for (int[] i : dp) Arrays.fill(i, -1);
        return dfs(s, p, 0, 0);
    }
}