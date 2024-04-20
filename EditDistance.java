package week12.day4;
//TC - O(m*n)
//SC - O(m*n)
public class EditDistance {
	public static int minDistance(String word1, String word2) {
		int r = word1.length();
		int c = word2.length();
		int[][] dp = new int[r + 1][c + 1];
		dp[0][0] = 0;
		for (int j = 1; j <= c; j++) {
			dp[0][j] = dp[0][j-1] + 1;
		}
		print(dp);
		for (int i = 1; i <= r; i++) {
			dp[i][0] = dp[i-1][0] + 1;
		}
		for (int i = 1; i <= r; i++) {
			for (int j = 1; j <= c; j++) {
				if(word1.charAt(i-1)==word2.charAt(j-1)) {
					dp[i][j] = dp[i-1][j-1];
				}
				else {
					dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]))+1;
				}
			}
		}
		print(dp);
		return dp[r][c];

	}
	public static void print(int[][] matrix) {
		int row = matrix.length;
		int col = matrix[0].length;
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				System.out.print(matrix[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word1 = "horse";
		String word2 = "ros";
		System.out.println(minDistance(word1, word2));
	}

}
