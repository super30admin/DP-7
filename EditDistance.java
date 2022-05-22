//Time Complexity : O(m*n); where m and n are lengths of word1 and word2
//Space Complexity : O(m)
public class EditDistance {  
	/**Approach: DP Array**/	
	public int minDistance(String word1, String word2) {
		int m = word1.length();
		int n = word2.length();

		int[] dp = new int[m + 1];
		// fill first row
		for (int i = 0; i < dp.length; i++) {
			dp[i] = i;
		}

		// fill dp row
		for (int i = 1; i <= n; i++) {
			int diagonal = dp[0];
			for (int j = 0; j <= m; j++) {
				if (j == 0) {
					dp[j] = i;
				} else {
					int temp = dp[j];
					if (word1.charAt(j - 1) == word2.charAt(i - 1)) {
						dp[j] = diagonal;
					} else { //1 + min of (diag up, up, one step back)
						dp[j] = 1 + Math.min(diagonal, Math.min(dp[j - 1], dp[j]));
					}
					diagonal = temp;
				}
			}
		}
		return dp[m];
	}
    /**Approach1: DP Matrix | Time O(m*n) | Space O(m*n)**/	
	/*public int minDistance(String word1, String word2) {
        int m= word1.length();
        int n= word2.length();
        
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        //fill first row
        for(int i=1; i<dp[0].length; i++){
            dp[0][i] = i;
        }
        //fill first col
        for(int i=1; i<dp.length; i++){
            dp[i][0] = i;
        }
        
        //fill rest dp matrix
        for(int i=1; i<dp.length; i++){
            for(int j=1; j<dp[0].length; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = 1+ Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }
        
        return dp[m][n];
    }*/
	   
	// Driver code to test above
	public static void main (String[] args) {	
		EditDistance ob  = new EditDistance();			
		String word1 = "horse"; 
		String word2 = "ros";				
		System.out.println("Min number of operations to convert '"+word1+"' to '"+word2+"' are: "+ob.minDistance(word1, word2));
	}
}
