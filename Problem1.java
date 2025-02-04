class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        dp[0][0] = 0;
        for(int i=0;i<=m;i++){
            for(int j=0;j<=n;j++){
                if(i==0){
                    if(j!=0){
                        dp[i][j] = dp[i][j-1]+1;
                    }
                }
                else if(j==0){
                    dp[i][j] = i;
                }
                else{
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
    dp[i][j] = dp[i - 1][j - 1]; // No operation needed if characters match
} else {
    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1])) + 1;
}
                }

                System.out.println("dp"+i+j+dp[i][j]);
            }

        }

        return dp[m][n];
        
    }
}