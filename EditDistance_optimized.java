package week12.day4;
//TC - O(m*n)
//SC - O(m) m if m is smaller or n
public class EditDistance_optimized {
	public static int minDistance(String word1, String word2) {
		int r = word1.length();
		int c = word2.length();
		if(r<c)  // c should be smaller
			minDistance(word2, word1);
		int[] dp = new int[c+1];
		dp[0] = 0;
		for (int j = 1; j <= c; j++) {
			dp[j] = dp[j-1] + 1;
		}
		
		int diagUp = dp[0];
		int temp =0;
		for (int i = 1; i <= r; i++) {
			for (int j = 0; j <= c; j++) {
				temp = dp[j];
				if(j==0)
				{
					dp[j] = dp[j] + 1;
					diagUp = temp;
					continue;
				}
				if(word1.charAt(i-1)==word2.charAt(j-1)) {
					dp[j] = diagUp;
				}
				else {
					dp[j] = Math.min(dp[j], Math.min(diagUp, dp[j-1]))+1;
				}
				diagUp = temp;
			}
			print1(dp);
			
		}
		return dp[c];

	}
	public static void print1(int[] matrix) {
		int row = matrix.length;
		for (int i = 0; i < row; i++) {
				System.out.print(matrix[i]	 + " ");
			}
			System.out.println();
		}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String word1 = "intention";
		String word2 = "execution";
		System.out.println(minDistance(word1, word2));
	}

}
