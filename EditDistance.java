// Time: O(MN) | Space: O(MN)

// we are maintain a dp matrix of word2 in the row and word1 chars in the column
// we have two cases, if character matches, we take diagonal up value - because for the curr char no changes needed + till prev substring how many changes were needed
// incase of character doesn't match we have three cases, add, edit and delete

class Solution {
    public int minDistance(String word1, String word2) {
        int w1l = word1.length();
        int w2l = word2.length();
        int[][] dp = new int[w2l+1][w1l+1];
        for(int i=1;i<dp[0].length;i++) {
            dp[0][i] = i;
        }
        for(int i=1;i<dp.length;i++) {
            dp[i][0] = i;
        }
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++) {
                if(word1.charAt(j-1) == word2.charAt(i-1))
                    dp[i][j] = dp[i-1][j-1];
                else
                    dp[i][j] = 1+Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1]));
            }

        }
        return dp[w2l][w1l];
    }
}