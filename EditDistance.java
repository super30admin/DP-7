// tc: O(mn) m: length of word1 and n: length of word2
// sc: o(n)

class Solution {

    public int minDistance(String word1, String word2) {

        int m = word2.length();

        int n = word1.length();

        int[][] dp = new int[m + 1][n + 1];

        // top row

        for (int j = 0; j <= n; j++) {

            dp[0][j] = j;

        }

        // first col

        for (int i = 0; i <= m; i++) {

            dp[i][0] = i;

        }

        for (int i = 1; i <= m; i++) {

            for (int j = 1; j <= n; j++) {

                if (word1.charAt(j - 1) == word2.charAt(i - 1)) {

                    dp[i][j] = dp[i - 1][j - 1];

                } else {

                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));

                }

            }

        }

        return dp[m][n];

    }

}
