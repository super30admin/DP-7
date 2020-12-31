public class EditDistance {
	public int minDistance(String word1, String word2) {

		int row = word1.length();
		int col = word2.length();

		int[][] dp = new int[row + 1][col + 1];
		// fill first row
		for (int i = 1; i < dp.length; i++) {
			dp[i][0] = i;
		}
		// fill first col
		for (int i = 1; i < dp[0].length; i++) {
			dp[0][i] = i;
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 1; j < dp[0].length; j++) {

				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1]; // take it from diagonal
				} else {// 1+ min of all three operations (update,delete,add)
					dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
				}

			}
		}
		return dp[row][col];
	}

	public static void main(String[] args) {
		EditDistance dst = new EditDistance();
		dst.minDistance("horse", "ros");
	}
}
