time;O(m * n)
space;O(n)

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // If word1 is shorter than word2, swap them to minimize space usage
        if (m < n) return minDistance(word2, word1);
        
        // dp array to store the edit distances
        int[] dp = new int[n + 1];
        
        // Initialize the dp array for the first row (base case)
        for (int j = 0; j <= n; j++) {
            dp[j] = j;
        }
        
        // Fill the dp array
        for (int i = 1; i <= m; i++) {
            // Store the value for the top-left diagonal (diagUp)
            int diagUp = dp[0];
            // Update dp[0] for the current row (base case)
            dp[0] = i;
            
            for (int j = 1; j <= n; j++) {
                int prev = dp[j];  // Store the previous dp[j] for the next diagonal element
                
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[j] = diagUp;  // If characters match, take the diagonal value (no operation)
                } else {
                    dp[j] = 1 + Math.min(dp[j], Math.min(diagUp, dp[j - 1]));  // Min of insertion, deletion, or replacement
                }
                
                diagUp = prev;  // Update the diagonal for the next iteration
            }
        }
        
        return dp[n];  // The final answer is in dp[n]
    }
}