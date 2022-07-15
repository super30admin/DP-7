TC:O(m*n)
SC:O(m*n) - dp array

there are repeated subproblems in it. hence used DP Approach

class Solution {
    public int minDistance(String word1, String word2) { //create a dp array
        int n = word1.length(),m=word2.length();
        
        int [][]dp = new int[m+1][n+1];
        
        for(int j=0;j<=n;j++){ //fix the top row and first column
            
            dp[0][j] = j;
            
        }
        for(int i=0;i<=m;i++){
            dp[i][0] = i;
            
        }
        for(int i=1;i<=m;i++){ //traverse through the dp array by choosing multiple possibilities at every index
            for(int j=1;j<=n;j++){
                if(word2.charAt(i-1) == word1.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = Math.min(dp[i][j-1],Math.min(dp[i-1][j-1],dp[i-1][j]))+1;
                }
                //System.out.print(dp[i][j]);
            }
           // System.out.println(" ");
        }
        return dp[m][n];
    }
}