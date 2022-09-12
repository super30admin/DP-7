// TC: O(m*n)
// SC: O(n)

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        
        
        
        int m=word1.length();
        int n=word2.length();
        
        if(m<n) return minDistance(word2,word1);
        
        int[] dp = new int[n+1];
        // fill first row
        for(int j=0;j<n+1;j++){
            dp[j]=j;
        }
        
        // fill first column
        for(int i=1;i<m+1;i++){
            int temp = dp[0];
            dp[0]=i;
            for(int j=1;j<n+1;j++){
                int prev = dp[j];
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[j] = temp;
                }else{
                    dp[j] = 1+Math.min(dp[j-1],Math.min(temp,dp[j]));
                }
                temp = prev;
            }
        }
        
        return dp[n];
    }
}
