class Solution {
public int minDistance(String word1, String word2) {
        int m = word1.length();
    int n = word2.length();
    int dp[][] = new int[m + 1][n + 1];
    for (int i = 0; i <= m; i++) {
        for (int j = 0; j <= n; j++) {
            if (i == 0) {
                dp[i][j] = j;
            } else if (j == 0) {
                dp[i][j] = i;
            } else {
                int e = 0;     
                if (word1.charAt(i-1)==word2.charAt(j-1)) {
                    e = dp[i-1][j-1];
                }
                else e = dp[i-1][j-1]+1; 
                int insertI =  dp[i][j-1] + 1;
                int insertJ = dp[i-1][j] + 1;
                dp[i][j] = Math.min(e,Math.min(insertJ, insertI));
            }
        }
    }
    return dp[m][n];
}
}
//Time complexity : O(M*N)
//Space complexity : O(M*N)
