// Time Complexity : 
// Space Complexity : 
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No
package problem1;

public class EditDistance {
	/********************* 2D Dynamic Programming *********************/
	// Time Complexity : O(m*n), m -> Length of word1, n -> Length of word2
	// Space Complexity : O(m*n)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int minDistance2DDP(String word1, String word2) {
		if (word1.equals(word2)) {
			return 0;
		}

		int m = word1.length();
		int n = word2.length();
		if (m == 0) {
			return n;
		}
		if (n == 0) {
			return m;
		}

		int[][] dp = new int[m + 1][n + 1];

		for (int i = 0; i <= m; i++) {
			dp[i][0] = i;
		}
		for (int j = 0; j <= n; j++) {
			dp[0][j] = j;
		}

		for (int i = 1; i <= m; i++) {
			for (int j = 1; j <= n; j++) {
				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					dp[i][j] = dp[i - 1][j - 1];
				} else {
					dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
				}
			}
		}

		return dp[m][n];
	}

	/********************* 1D Dynamic Programming *********************/
	// Time Complexity : O(m*n), m -> Length of word1, n -> Length of word2
	// Space Complexity : O(n)
	// Did this code successfully run on Leetcode : Yes
	// Any problem you faced while coding this : No
	public int minDistance(String word1, String word2) {
		if (word1.equals(word2)) {
			return 0;
		}

		int m = word2.length();
		int n = word1.length();

		if (m == 0) {
			return n;
		}

		if (n == 0) {
			return m;
		}

		if (m > n) {
			return minDistance(word2, word1);
		}

		int[] dp = new int[n + 1];

		for (int j = 1; j <= n; j++) {
			dp[j] = j;
		}

		int temp1;
		int temp2;

		for (int i = 1; i <= m; i++) {
			temp1 = dp[0];
			dp[0] = i;

			for (int j = 1; j <= n; j++) {
				temp2 = dp[j];
				if (word2.charAt(i - 1) == word1.charAt(j - 1)) {
					dp[j] = temp1;
				} else {
					dp[j] = 1 + Math.min(Math.min(dp[j], dp[j - 1]), temp1);
				}
				temp1 = temp2;
			}
		}

		return dp[n];
	}

	public static void main(String[] args) {
		EditDistance obj = new EditDistance();
		String word1 = "intention";
		String word2 = "execution";

		System.out.println("Minimum distance to obtain \'" + word2 + "\' from \'" + word1 + "\' = "
				+ obj.minDistance(word1, word2));
	}

}
