// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach: Our code implements the regular expression matching algorithm using dynamic programming. It uses a 2D boolean array dp to store whether substrings of s match patterns of p. It iterates through both strings, considering different cases for characters and the * wildcard, updating the dp array accordingly. The final result indicates if the entire s matches the pattern p.

public class EditDistance {
    public int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // TOP Row
        for (int j = 1; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // Filling first column of all rows
                if (j == 0) {
                    dp[i][j] = i;
                } else {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i - 1][j - 1], dp[i][j - 1]));
                    }
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        EditDistance editDistance = new EditDistance();

        String word1 = "horse";
        String word2 = "ros";
        int result1 = editDistance.minDistance(word1, word2);
        System.out.println("Edit distance between \"" + word1 + "\" and \"" + word2 + "\": " + result1);

        String word3 = "intention";
        String word4 = "execution";
        int result2 = editDistance.minDistance(word3, word4);
        System.out.println("Edit distance between \"" + word3 + "\" and \"" + word4 + "\": " + result2);
    }
}
