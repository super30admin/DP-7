package week12.day4;
//TC - O(n)
//SC - O(n)
//https://leetcode.com/problems/regular-expression-matching/
class Solution {
	public boolean isMatch(String s, String p) {
		int row = s.length();
		int col = p.length();
		boolean[][] dp = new boolean[row + 1][col + 1];
		dp[0][0] = true;
		for (int i = 1; i <= col; i++) {
			if (p.charAt(i - 1) == '*')
				dp[0][i] = dp[0][i - 2];
		}
		for (int i = 1; i <= s.length(); i++) {
			for (int j = 1; j <= p.length(); j++) {
				if (p.charAt(j - 1) == '*') {
					// 0th case will be there for sure
					// 1 th case will be available only if characters before * match
					if (p.charAt(j - 2)=='.' || p.charAt(j - 2) == s.charAt(i - 1)) // checking prior char to *
						dp[i][j] = dp[i - 1][j] || dp[i][j - 2];
					else
						dp[i][j] = dp[i][j - 2];
				} else {
					if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
						dp[i][j] = dp[i - 1][j - 1]; // take it from diagonal
					}
				}
			}
		}
		return dp[row][col];
	}

	public static void print(boolean[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}
}

public class RegularExpressionMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution ob = new Solution();
		String s = "aab";
		String p = "c*a*b";
		//boolean result = ob.isMatch("mississippi", "mis*is*p*.");
		boolean result = ob.isMatch("aa", "a");
		System.out.println(result);
	}

}
