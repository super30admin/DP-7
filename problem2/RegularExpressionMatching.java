// Time Complexity : O(m*n), m -> Length of string s, n -> Length of string p
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem2;

public class RegularExpressionMatching {
	public boolean isMatch(String s, String p) {
		if (s.equals(p) || p.equals(".*")) {
			return true;
		}

		int sl = s.length();
		int pl = p.length();

		boolean[][] dp = new boolean[sl + 1][pl + 1];
		dp[0][0] = true;

		// First Row
		for (int j = 1; j <= pl; j++) {
			if (p.charAt(j - 1) == '*') {
				dp[0][j] = dp[0][j - 2];
			}
		}

		for (int i = 1; i <= sl; i++) {
			for (int j = 1; j <= pl; j++) {
				// Not a *
				if (p.charAt(j - 1) != '*') {
					if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					}
				}
				// *
				else {
					dp[i][j] = dp[i][j - 2];
					if (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1)) {
						dp[i][j] = dp[i - 1][j] || dp[i][j];
					}
				}
			}
		}
		return dp[sl][pl];
	}

	public static void main(String[] args) {
		RegularExpressionMatching obj = new RegularExpressionMatching();
		String s = "aa";
		String p = "a*";

		System.out.println("Do the strings match? " + (obj.isMatch(s, p) ? "Yes" : "No"));
	}

}
