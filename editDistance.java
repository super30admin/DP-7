// Time Complexity : O(MN)
// Space Complexity : O(min(M,N))
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None


public class editDistance {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return -1;

        int insertCost = 1;
        int deleteCost = 1;
        int replaceCost = 1;
        int l1 = word1.length();
        int l2 = word2.length();

        if (l1 == 0) {
            return l2 * insertCost;
        }
        if (l2 == 0) {
            return l1 * deleteCost;
        }
        
        if (l1 > l2) {
            return minDistance(word2, word1);
        }

        int[] dp = new int[l1 + 1];
        
        for (int i = 1; i <= l1; i++) {
            dp[i] = dp[i - 1] + deleteCost;
        }

        for (int j = 1; j <= l2; j++) {
            int prev = dp[0];
            dp[0] += insertCost;
            char c2 = word2.charAt(j - 1);
            for (int i = 1; i <= l1; i++) {
                char c1 = word1.charAt(i - 1);
                int temp = dp[i];
                
                if (c1 == c2) {
                    dp[i] = prev;
                } else {
                    dp[i] = Math.min(prev + replaceCost, Math.min(dp[i - 1] + deleteCost, dp[i] + insertCost));
                }
                prev = temp;
            }
        }

        return dp[l1];
    }
}
