//TC: O(m*n) m-> length of word1 , n -> length of word2
//SC: O(m*n)

//CODE:

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[n+1][m+1];
        
        //filling first row
        for(int j=1;j<dp[0].length;j++){
            dp[0][j]= j;   //filling with 1,2,3,4,5....so on
        }
        
        //filling first column
        for(int i=1;i<dp.length;i++){
            dp[i][0] = i;  //1,2,3,4....so on
        }
        
        //filling rest of the matrix
        for(int i=1;i<dp.length;i++){
            for(int j=1;j<dp[0].length;j++){
                //if the character of word1 matches with word2 then just take diagonal
                if(word1.charAt(j-1) == word2.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    //if last character of word1 does not match with last ch of word2
                    //then take min of all the three cases -> update,delete,insert and add 1 to it.
                    //update -> diagonal 
                    //delete -> 1 step back
                    //insert -> just above
                    dp[i][j] = 1+ Math.min(dp[i-1][j-1] , Math.min(dp[i][j-1] , dp[i-1][j]));
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];                                 
    }
}
