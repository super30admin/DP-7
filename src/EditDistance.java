// Time Comlpexity:  O(m*n)
// Space Complexity: O(n)

class Solution {
    int[] dp;
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();
        if(m==0 || n==0) return m==0 ? n : m;
        dp = new int[n+1];
        for(int j=0; j<=n; j++) dp[j] = j;
        for(int i=1; i<=m; i++) {
            int diagUp = dp[0];
            dp[0] = i;
            for(int j=1; j<=n; j++) {
                int temp = dp[j];
                if(word1.charAt(i-1) == word2.charAt(j-1)) dp[j] = diagUp;
                else dp[j] = 1 + Math.min(Math.min(diagUp, dp[j]), dp[j-1]);
                diagUp = temp;
            }
        }
        return dp[n];
    }
}


// // Time Comlpexity:  O(m*n)
// // Space Complexity: O(m*n)

// class Solution {
//     int[][] dp;
//     public int minDistance(String word1, String word2) {
//         if(word1.equals(word2)) return 0;
//         int m = word1.length();
//         int n = word2.length();
//         if(m==0 || n==0) return m==0 ? n : m;
//         dp = new int[m+1][n+1];
//         for(int i=1; i<=m; i++) dp[i][0] = i;
//         for(int j=1; j<=n; j++) dp[0][j] = j;
//         for(int i=1; i<=m; i++) {
//             for(int j=1; j<=n; j++) {
//                 if(word1.charAt(i-1) == word2.charAt(j-1)) dp[i][j] = dp[i-1][j-1];
//                 else dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
//             }
//         }
//         return dp[m][n];
//     }
// }
