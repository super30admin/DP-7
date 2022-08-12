// Time Complexity : O(MN)
// Space Complexity : O(MN)

class Solution {

    int[][] dp;
    public boolean dfs(String s, String p, int i, int j) {
        if (i >= s.length() && j >= p.length()) return true;
        if (j >= p.length()) return false;

        if (dp[i][j] != -1) return dp[i][j] == 1 ? true : false;
        boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
            boolean test_1 = (dfs (s, p , i, j + 2) || (match && dfs (s, p, i + 1, j)));
            dp[i][j] = test_1 == true ? 1 : 0;
            return test_1;
        }
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