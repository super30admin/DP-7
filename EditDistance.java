// Time Complexity :O(w1len*w2len)
// Space Complexity :O(w1len*w2len)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


class Solution {
    public int minDistance(String word1, String word2) {
        int w1len=word1.length();
        int w2len=word2.length();
        if(w1len==0) return w2len;
        
        //word2 chars as row as table header
        int[][] dp=new int[w1len+1][w2len+1];
        //- conversion to - need 0 steps
        dp[0][0] =0;
        
        //first row - to word takes word length steps
        for(int col=1;col<=w2len;col++){
            dp[0][col]=col;
        }
        
         //first col word to - takes word length steps
        for(int row=1;row<=w1len;row++){
            dp[row][0]=row;
        }
        
        for(int row=1;row<=w1len;row++){
            for(int col=1;col<=w2len;col++){
                
                //chars are same copy diagnoal up
                if(word1.charAt(row-1)==word2.charAt(col-1)){
                    dp[row][col]=dp[row-1][col-1];
                }else{
                    dp[row][col]=1+Math.min(dp[row-1][col-1],Math.min(dp[row-1][col],dp[row][col-1]));
                }
            }
        }
        
        return dp[w1len][w2len];
        
    }
}