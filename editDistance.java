// Time Complexity : O(mn)
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

class Main {
    // approch 1 using dp matrix
    public static int minDistance(String word1, String word2) {
        // match case
        if (word1.equals(word2))
            return 0;
        // empty string case
        if (word2.length() == 0)
            return word1.length();
        // if(word1.length() == 0) return word2.length();
        int m = word1.length();
        int n = word2.length();
        // dp matrix
        int[][] dp = new int[m + 1][n + 1];
        // populate first colmn
        for (int i = 1; i <= n; i++) {
            dp[0][i] = i;
        }
        // populate first cols
        for (int j = 1; j <= m; j++) {
            dp[j][0] = j;
        }
        // populate other index and at the end
        // we found our ans at last row last col
        // here we have 3 operations
        // add
        // delete
        // update
        // delete -> previous row + 1
        // update -> diagonally + 1
        // add -> previous colm + 1
        // if char both char is same -> diagonally + 1
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char c1 = word1.charAt(i - 1);
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        System.out.println(minDistance(word1, word2));
    }
}