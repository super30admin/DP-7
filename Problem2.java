/*
Edit Distance
approach: at each character we have 3 choices, use dp
time & space: O(mxn)
 */
public class Problem2 {
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for (int i=0;i<m+1;i++) {
            dp[0][i] = i;
        }
        for (int i=0;i<n+1;i++) {
            dp[i][0] = i;
        }

        for (int i=1;i<m+1;i++) {
            for (int j=1;j<n+1;j++) {
                char c1 = word1.charAt(j-1);
                char c2 = word2.charAt(i-1);

                if (c1==c2) {
                    dp[i][j] = dp[i-1][j-1];
                }
                else {
//                    check all the 3 possibilities
//                    i.e. update or delete or add a character
                    dp[i][j] = 1+Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
                }
            }
        }
        return dp[m][n];
    }
}
