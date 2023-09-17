// Time Complexity : O(m*n)
// Space Complexity :O(m*n)
// Did this code successfully run on Leetcode :yes
// Any problem you faced while coding this :no


// Your code here along with comments explaining your approach

class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        //Fill the first row
        for(int j=0; j<word2.length()+1; j++){
            dp[0][j] = j;
        }

        for(int i=1; i< word1.length()+1; i++){
            for(int j=0; j<word2.length()+1; j++){
                //If it's first column
                if(j == 0){
                    dp[i][j] = i;
                }
                //If incoming characters match, get the diagonal up left
                else if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];

                }//Else get the min and add 1
                 else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                }
            }


        }

        return dp[word1.length()][word2.length()];
    }
}