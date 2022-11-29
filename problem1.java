package DP-7;

public class problem1 {
    //TC:- O(m * n)
    //SC:- O(n)
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        
        int m = word1.length();
        int n = word2.length();
        
        if(n > m){
            return minDistance(word2, word1);
        }
        
        int[] dp = new int[n + 1];
        
        for(int j = 0; j < n + 1; j++){
            dp[j] = j;
        }
        
        for(int i = 1; i < m + 1; i++){
            int prev = dp[0];
            dp[0] = i;
            for(int j = 1; j < n + 1; j++){
                int temp = dp[j];
                if(word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[j] = prev;
                }
                else {
                    dp[j] = Math.min(dp[j - 1], Math.min(prev, temp)) + 1;
                }
                prev = temp;
            }
        }
        
        return dp[n];
    }
}
