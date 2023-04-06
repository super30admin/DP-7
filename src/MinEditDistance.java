import java.util.Arrays;
//Time Complexity : O(N1 * N2)
//Space Complexity : O(N1 * N2)
//Did this code successfully run on Leetcode : Yes
//Any problem you faced while coding this : No

/**
 * Maintain a dp matrix of word1, word2 size. Start comparing each character
 * from the first index of both words. If both chars are equal, update dp matrix
 * of that index with dp index of previous char of each string. Else update it
 * with 1 + min of dp index of previous chars of two words, dp index of previous
 * char of 1st word and current char of 2nd word and dp index of previous char
 * of 2nd word and current char of 1st word. Finally return the last index of dp
 * to get the result.
 *
 */
class Solution {
	public int minDistance(String word1, String word2) {
		int n1 = word1.length();
		int n2 = word2.length();
		// int[][] dp = new int[n1+1][n2+1];
		// for(int i=0;i<dp.length;i++)
		// dp[i][0] = i;
		// for(int j=0;j<dp[0].length;j++)
		// dp[0][j] = j;

		// for(int i=1;i<dp.length;i++) {
		// for(int j=1;j<dp[0].length;j++) {
		// if(word1.charAt(i-1) == word2.charAt(j-1))
		// dp[i][j] = dp[i-1][j-1];
		// else
		// dp[i][j] = 1 + Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
		// }
		// }
		// return dp[n1][n2];
		int[][] dp = new int[n1][n2];
		for (int i = 0; i < dp.length; i++)
			Arrays.fill(dp[i], -1);
		return helper(word1, word2, dp, 0, 0);
	}

	public int helper(String word1, String word2, int[][] dp, int n1, int n2) {
		if (n1 >= word1.length() && n2 >= word2.length())
			return 0;
		if (n1 >= word1.length())
			return word2.length() - n2;
		if (n2 >= word2.length())
			return word1.length() - n1;
		if (dp[n1][n2] != -1)
			return dp[n1][n2];
		if (word1.charAt(n1) == word2.charAt(n2))
			dp[n1][n2] = helper(word1, word2, dp, n1 + 1, n2 + 1);
		else
			dp[n1][n2] = 1 + Math.min(helper(word1, word2, dp, n1 + 1, n2 + 1),
					Math.min(helper(word1, word2, dp, n1 + 1, n2), helper(word1, word2, dp, n1, n2 + 1)));
		return dp[n1][n2];
	}
}
