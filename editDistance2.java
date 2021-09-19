//Time: O(MN)
//Space: O(N)

//This approach uses a DP array
class editDis {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2))
            return 0;
        
        int m = word2.length();
        int n = word1.length();
        
        //we optimize by keeping the smaller size word length for the array
        if(m > n)
            return minDistance(word2, word1);
        
        int[] dp = new int[n + 1];
        
        dp[0] = 0;
        
        //first row
        for(int j = 1; j <= n; j++)
            dp[j] = j;
        
        int temp, temp2;
        
        for(int i = 1; i <= m; i++){
            temp = dp[0];
            dp[0] = i;
            for(int j = 1; j <= n; j++){
                
                //chars are not equal
                temp2 = dp[j];
                if(word1.charAt(j - 1) != word2.charAt(i - 1)){
                    dp[j] = Math.min(dp[j - 1], Math.min(dp[j], temp)) + 1;
                }
                //equal
                else{
                    dp[j] = temp;
                }
                temp = temp2;
            }
        }
        
        return dp[n];
    }
}