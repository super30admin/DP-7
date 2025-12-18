/*
Algorithm (Bottom-up DP with optimized 1D array):
- Use a 1D array dp of length n+1 (n = word2.length()).
- dp[j] represents the min edits to convert first i chars of word1 to first j chars of word2 for current i.
- Keep a variable diagUp to store dp[j-1] from previous row (the previous i-1 iteration) to compute replacements.
- Update dp in place while iterating j from 1..n.

Time Complexity: O(m * n) where m = word1.length(), n = word2.length().
Space Complexity: O(n) for the 1D dp array.
LeetCode: https://leetcode.com/problems/edit-distance/
*/

public class EditDistanceDp3 {

    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[] dp = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            dp[i] = i;
        }

        for (int i = 1; i <= m; i++) {
            dp[0] = i;
            int diagUp = i - 1;
            for (int j = 1; j <= n; j++) {
                int temp = dp[j];
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = diagUp;
                } else {
                    dp[j] = 1 + Math.min(diagUp, Math.min(dp[j], dp[j - 1]));
                }
                diagUp = temp;
            }
        }

        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(new EditDistanceDp3().minDistance("horse", "ros"));
    }
}
