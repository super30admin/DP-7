//Time complexity is O(M*N)
//Space complexity is O(M*N)
//This solution is submitted on leetcode

public class BigN149RegularExpressionMatching {
	class Solution {
		public boolean isMatch(String s, String p) {
			// edge case
			if (p == null || s == null)
				return false;
			// dp matrix
			boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
			int row = dp.length;
			int column = dp[0].length;
			dp[0][0] = true;
			for (int i = 1; i < column; i++) {
				if (p.charAt(i - 1) == '*') {
					dp[0][i] = dp[0][i - 2];
				}
			}
			for (int i = 1; i < row; i++) {
				for (int j = 1; j < column; j++) {
					if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
						dp[i][j] = dp[i - 1][j - 1]; 
					} else if (p.charAt(j - 1) == '*') {
						dp[i][j] = dp[i][j - 2];
						if (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') {
							if (dp[i - 1][j] || dp[i][j])
								dp[i][j] = true;
						}
					}
				}
			}
			return dp[row - 1][column - 1];
		}
	}
}