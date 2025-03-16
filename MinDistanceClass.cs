public class Solution {
    // TC => O(m*n)
    // SC => O(m*n)
    public int MinDistance(string word1, string word2) {
        if(word1.Length == 0 || word2.Length == 0){
            return word1.Length > word2.Length ? word1.Length : word2.Length;
        }

        if(word1 == word2){
            return 0;
        }

        int m = word1.Length;
        int n = word2.Length;
        int[][] dp = new int[m+1][];

        for(int i = 0; i< m+1;i++){
            dp[i] = new int[n+1];
            dp[i][0] = i;
        }

        for(int j = 1; j < n+1; j++){
            dp[0][j] = j;
        }

        for(int i = 1; i < m+1; i++){
            for(int j = 1; j < n+1; j++){
                if(word1[i-1] == word2[j-1]){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1 + Math.Min(dp[i-1][j-1], Math.Min(dp[i-1][j], dp[i][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}