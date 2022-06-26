class editDistance1 {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        
        int m = word1.length();
        int n = word2.length();
        if(m < n) return minDistance(word2, word1);
        int[] dp = new int[m + 1];
        
        for(int i = 0; i <= n; i++){
            dp[i] = i;
        }
        
        for(int i = 1; i <= m; i++){
            int temp = dp[0];
            dp[0] = i;
            for(int j = 1; j <= n; j++){
                int prev = dp[j];
                if(word2.charAt(j - 1) == word1.charAt(i - 1)){
                    dp[j] = temp;
                }
                else{
                    dp[j] = Math.min(dp[j], Math.min(dp[j - 1], temp)) + 1;
                }
                temp = prev;
            }
        }
        
        return dp[n];
    }
}

//time complexity O(m * n) where m and n are the length of given strings
//space complexity O(n)