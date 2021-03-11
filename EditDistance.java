
/*
at each step, we have 3 ooptions: I, U, D

brute force method: would be to explore all the paths. 
this will have repeated subproblems and therefore can use dp

method2:
use 2d dp array to save  number of steps
the above value will give insert, diaagonla will give update and left will give delete.
At each step we shud choose min among these 3 and add 1 to it. 

TC: O(m*n)
SC :O(m*n)

*/
class Solution {
    public int minDistance(String word1, String word2) {
        
        int n = word1.length();
        int m = word2.length();
        
        int[][] dp = new int[m+1][n+1];
        
        // System.out.println(l1);
        // System.out.println(l2);
        
        for(int j = 0; j <= n; j++){
            dp[0][j] = j;
        }
        for(int i = 0;i <= m;i++){
            dp[i][0] = i;
        }
        
        for(int i = 1; i <= m;i++){
            for(int j= 1; j <= n; j++){
                if(word1.charAt(j-1) != word2.charAt(i-1)){
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                }else{
                    dp[i][j] = dp[i-1][j-1];
                }
                
            }
        }
        return dp[m][n];
    }
}