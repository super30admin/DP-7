//Time complexity is O(M*N)
//Space complexity is O(M*N)
//This solution is submitted on leetcode

public class BigN148EditDistanceUsingDP {
	class Solution {
		public int minDistance(String word1, String word2) {
			// edge case
			if (word1 == null || word2 == null)
				return 0;
			int[][] dp = new int[word1.length() + 1][word2.length() + 1];
			int row = dp.length;
			int column = dp[0].length;
			for (int i = 1; i < column; i++) {
				dp[0][i] = i;
			}
			for (int i = 1; i < row; i++) {
				dp[i][0] = i;
			}

			for (int i = 1; i < row; i++) {
				for (int j = 1; j < column; j++) {
					if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
						dp[i][j] = dp[i - 1][j - 1];
					} else {
						int min = Math.min(dp[i - 1][j - 1], dp[i][j - 1]);
						dp[i][j] = Math.min(dp[i - 1][j], min) + 1;
					}
				}
			}
			return dp[row - 1][column - 1];
		}
	}
}