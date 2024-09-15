// Time Complexity :o(mn)
// Space Complexity :o(mn)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :


// Your code here along with comments explaining your approach
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp=new int[word1.length()+1][word2.length()+1];
        
        for(int i=0;i<= word1.length();i++){
            dp[i][0] = i;
        }
        for(int j=0;j<=word2.length();j++){
            dp[0][j] = j;
        }
        
        for(int m=1;m<=word1.length();m++){
            for(int n=1;n<=word2.length();n++){
                if(word1.charAt(m-1)==word2.charAt(n-1)){
                dp[m][n]=dp[m-1][n-1];
                    }
                    else{
                        dp[m][n]=1+Math.min(Math.min(dp[m-1][n],dp[m][n-1]),dp[m-1][n-1]);
                    }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}