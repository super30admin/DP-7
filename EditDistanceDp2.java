/*
Algorithm (Bottom-up DP using 2D table):
- Build a (m+1) x (n+1) DP table where dp[i][j] is the min edits to convert first i chars of word1 to first j chars of word2.
- dp[0][j] = j (insert all), dp[i][0] = i (delete all).
- If chars match dp[i][j] = dp[i-1][j-1], else 1 + min(replace, insert, delete).

Time Complexity: O(m * n) where m = word1.length(), n = word2.length().
Space Complexity: O(m * n) for the DP table.
LeetCode: https://leetcode.com/problems/edit-distance/
*/

public class EditDistanceDp2 {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        /*if(m<n)
            minDistance(word2, word1);*/

        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= m; i++) {
            dp[i][0] = i;
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        System.out.println(new EditDistanceDp2().minDistance("horse", "ros"));
    }
}
