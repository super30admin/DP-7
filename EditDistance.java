// TC: O(m * n) as all the characters in word2 is traversed for each character in word1
// TC: O(m * n) dp array is of the size m * n which is the length of word1 and word2 respectively

// Runs successfully on leetcode
// if the character matches, then the diagonal number is the answer
// else the minimum of the add update delete operation is considered 
// which is stored in the previous cells. Below is the formula mentioned
public class EditDistance {
    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros")); // 3
        System.out.println(minDistance("intention", "execution")); // 5
    }

    public static int minDistance(String word1, String word2) {
        if (word1.equals(word2))
            return 0;
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        // manage first row
        for (int j = 1; j < n + 1; j++) {
            dp[0][j] = j;
        }
        // manage first column
        for (int i = 1; i < m + 1; i++) {
            dp[i][0] = i;
        }
        // manage rest of the cells
        // add: 1 + dp[i][j-1];
        // update: 1 + dp[i-1][j-1];
        // delete: 1 + dp[i-1][j];
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j]));
                }
            }
        }
        return dp[m][n];
    }
}