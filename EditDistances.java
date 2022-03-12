// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode :

public class EditDistances {

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];

        //top row
        for(int j = 1; j < dp[0].length; j++){
            dp[0][j] = j;
        }

        //first col
        for(int i = 1; i < dp.length; i++){
            dp[i][0] = i;
        }

        for(int i = 1; i < dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                //if char matches from both the words, its dont do anything case
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    //take min(update, delete, add) operations + 1
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],
                            Math.min(dp[i][j-1], dp[i-1][j]));
                }
            }
        }
        return dp[m][n];
    }
}
