//time complexity- O(m*n)
//space complexity- O(m*n)
public class EditDistance {
    static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(
                            dp[i - 1][j],
                            Math.min(dp[i][j - 1], dp[i - 1][j - 1])
                    );
                }
            }
        }

        return dp[m][n];
    }
    public static void main(String[] args) {
        String word1 = "horse", word2 = "ros";
        System.out.println("Word1 - " + word1 + ",   Word2 - " + word2);
        System.out.println("Minimum steps to word1 to word2 is " + minDistance(word1, word2));
    }
}
