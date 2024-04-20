package week12.day4;
//TC - O(n)
//SC - O(n)
//https://leetcode.com/problems/regular-expression-matching/
class Solution1 {
	public boolean isMatch(String s, String p) {
		int row = s.length();
		int col = p.length();
		boolean[] dp = new boolean[col + 1];
		dp[0] = true;
		for (int i = 1; i <= col; i++) {
			if (p.charAt(i - 1) == '*')
				dp[i] = dp[i - 2];
		}
		print(dp);
		dp[0] = false;
        boolean diagUp = true;
        boolean temp = false;
		for (int i = 1; i <= s.length(); i++) {
			diagUp = dp[0];
			for (int j = 1; j <= p.length(); j++) {
                temp = dp[j];
				if (p.charAt(j - 1) == '*') {
					// 0th case will be there for sure
					// 1 th case will be available only if characters before * match
					if (p.charAt(j - 2)=='.' || p.charAt(j - 2) == s.charAt(i - 1)) // checking prior char to *
						dp[j] = dp[j] || dp[j - 2];
					else
						dp[j] = dp[j - 2];
				} else {
					if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)) {
						dp[j] = diagUp; // take it from diagonal
					}
					else {
                        dp[j] = false; // No match, set dp[j] to false
                    }
				}
                diagUp = temp;
			}
			print(dp);
		}
		return dp[col];
	}

	public static void print(boolean[] matrix) {
		int row = matrix.length;
		for (int i = 0; i < row; i++) {
				System.out.print(matrix[i] + " ");
			}
			System.out.println();
		}
}

public class RegularExpressionMatching_optimized {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Solution1 ob = new Solution1();
		//String s = "aab";
		//String p = "c*a*b";
		boolean result = ob.isMatch("aa", "a");
		System.out.println(result);
	}

}
//"mississippi", "mis*is*p*."
//s ="mississippi" p ="mis*is*ip*."