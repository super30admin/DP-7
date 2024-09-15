// Time complexity: o(mn)
// space complexity: o(mn)
// Did it run on leetcode: yes

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for(int i=0;i<dp.length-1;i++) {
            dp[i][0] = i;
        }
        for(int i=1;i<=dp[0].length-1;i++) {
            dp[0][i] = i;
        }
        for(int i=0;i<dp.length-1;i++) {
            for(int j=0;j<dp[0].length-1;j++) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    dp[i+1][j+1] = dp[i][j]; 
                }
                else {
                    dp[i+1][j+1] = 1 + Math.min((Math.min(dp[i][j],dp[i][j+1])), dp[i+1][j]);
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}