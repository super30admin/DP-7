//TC - O(M x N)
//SC - O(M x N)
class Solution1 {
	public boolean isMatch(String s, String p) {
		int sl = s.length();
		int pl = p.length();
		boolean dp[][] = new boolean[sl + 1][pl + 1];
		dp[0][0] = true;
		// top row
		for (int i = 1; i < dp[0].length; i++) {
			if (p.charAt(i - 1) == '*') {
				dp[0][i] = dp[0][i - 2];
			}
		}
		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {
				if (p.charAt(j - 1) != '*') {
					if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
						dp[i][j] = dp[i - 1][j - 1];
					}
				} else {
					// zero case
					dp[i][j] = dp[i][j - 2];
					// one case
					// preceding char before star in p should match with char in s
					if (j > 1 && (p.charAt(j - 2) == '.' || p.charAt(j - 2) == s.charAt(i - 1))) {
						if (dp[i - 1][j]) {
							dp[i][j] = true;
						}
					}
				}
			}
		}
		return dp[sl][pl];
	}
}