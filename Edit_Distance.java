class Solution {
    public int minDistance(String word1, String word2) {
        int first = word1.length();
        int second = word2.length();
        int [][]dp = new int [first+1][second+1];
        dp[0][0] = 0;

        for(int j =1; j<= second;j++) {
            dp[0][j] = j;
        }

        for(int i = 1; i<=first;i++) {
            dp[i][0] = i;
        }

        for(int i =1; i<=first;i++) {
            for(int j = 1; j<=second;j++) {
                if(word1.charAt(i-1) != word2.charAt(j-1)) {
                dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i][j-1], dp[i-1][j]))+1;
                } else {
                    dp[i][j] = dp[i-1][j-1];
                }
            
            }
        }
        return dp[first][second];
    }
}