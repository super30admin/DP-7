//Time Complexity : O(m*n) m = word1 length, n = word2 length
//Space Complexity : O(m*n)
//Did this code successfully run on Leetcode :yes
//Any problem you faced while coding this : no
class Solution {
    public int minDistance(String word1, String word2) {
        
        if(word1.length() == 0){
            
            return word2.length();
        }
         if(word2.length() == 0){
            
            return word1.length();
        }
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        
        for(int j = 0; j < dp[0].length; j ++){
            
            dp[0][j] = j;
        }
        
       for(int i = 0; i < dp.length; i ++){
            
           dp[i][0] = i;
        }
        
        
        for(int i = 1; i < dp.length; i ++){
            
            for(int j = 1; j < dp[0].length; j ++){
                
                
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    
                    dp[i][j] = dp[i-1][j-1];
                }
                
                else{
                    
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]));
                }
                
                
            }
        }
        
        return dp[word1.length()][word2.length()];
    }
}