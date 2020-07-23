//time o(mn)
//space o(mn)
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        
        // fill first row
        for(int i=0; i<dp[0].length; i++) {
            dp[0][i] = i;
        }
        
        //fill first column
        for(int i=0; i<dp.length; i++) {
            dp[i][0] = i;
        }
        
        for(int i=1; i<m+1; i++) {
            for(int j=1; j<n+1; j++) {
                if(word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1]));
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}