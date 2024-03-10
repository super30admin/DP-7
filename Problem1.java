class Solution {
    //TC: O(m*n)
    //SC: O(n)
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m = word1.length(); int n = word2.length();

        if(m < n) return minDistance(word2, word1);
        int [] dp = new int[n+1];
        //top row
        for(int j = 0; j <= n; j++){
            dp[j] = j;
        }

        for(int i = 1; i <= m; i++){
            int diagUp = dp[0];
            dp[0] = i;
            for(int j = 1; j <= n; j++){
                int temp = dp[j];
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[j] = diagUp;
                }else{
                    dp[j] = 1 + Math.min(dp[j-1], Math.min(diagUp, dp[j])); 
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
}
