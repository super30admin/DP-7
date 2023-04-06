//Time Complexity : O(N1 * N2)
//Space Complexity : O(N1 * N2)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Use dp boolean matrix to store sub problem results. If both strings reach
 * end, then return true. If only j returns to the end, then return false. Then
 * check if each char is matching in both strings or if the current char in
 * pattern is as ., if so, check for the next element in the pattern for *, if
 * it is *, then check if ignoring these two chars from pattern leads to a
 * matching or moving the char to its next in the string to be matched. return
 * the result from there. If next char is not a *, then move the current chars
 * to next positions in each string and repeat the process.
 *
 */
class Solution {
	public boolean isMatch(String s, String p) {
		Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];
		return helper(s, p, dp, 0, 0);
	}

	public boolean helper(String s, String p, Boolean[][] dp, int i, int j) {
		if (i >= s.length() && j >= p.length())
			return true;
		if (j >= p.length())
			return false;
		if (dp[i][j] != null)
			return dp[i][j];

		boolean first = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
		if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
			return dp[i][j] = helper(s, p, dp, i, j + 2) || (first && helper(s, p, dp, i + 1, j));
		}
		if (first) {
			return dp[i][j] = helper(s, p, dp, i + 1, j + 1);
		}
		return false;
	}
}
