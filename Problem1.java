72. Edit Distance

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[] dp = new int[n + 1];
        for(int i = 0; i < n + 1; i++){
            dp[i] = i;
        }
        int prev, temp;
        
        for(int i = 1; i < m + 1; i++){
            
            prev = dp[0];
            dp[0] = i;
            for(int j = 1; j < n + 1; j++){
                temp = dp[j];
                if(word1.charAt(j - 1) == word2.charAt(i - 1)) dp[j] = prev;
                else dp[j] = 1 + Math.min(dp[j - 1], Math.min(prev, dp[j]));
                prev = temp;
            }
        }
        return dp[n];
    }
}