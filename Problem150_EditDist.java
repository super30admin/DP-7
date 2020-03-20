//Time Complexity: O(mn)

public class Solution {    
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();        
        //consider a dp[][] of length word1+1 and word2+1
        int[][] dp = new int[m + 1][n + 1];
        //fill the 0th row and col with initial values
        for(int i = 0; i <= m; i++)
            dp[i][0] = i;
        for(int i = 1; i <= n; i++)
            dp[0][i] = i;
        //for the entire dp matrix
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                //case1: if character of both words is equal
                //take diagonally up value
                if(word1.charAt(i) == word2.charAt(j))
                    dp[i + 1][j + 1] = dp[i][j];
                else {
                    //case2: if character of 2 words not same;
                    //take min of 3 values + 1
                    dp[i + 1][j + 1] = Math.min(dp[i][j+1],Math.min(dp[i][j], dp[i+1][j])) + 1;
                }
            }
        }
        return dp[m][n];
    }
}