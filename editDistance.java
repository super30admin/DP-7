i//TC: O(n*m)
//SC: O(n*m)
/*Approach: DP (tabulation)
If (the character in word 1 matched with char in word 2,then we take the diagnol elemnet, otherwise, the minimum of the left,top and top left elements in dp array)
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();
        int[][] dp = new int[n+1][m+1];
        for(int i=0;i<n+1;i++)  dp[i][0]=i;
        for(int j=0;j<m+1;j++)  dp[0][j]=j;
        dp[0][0]=0;
        
        for(int i=1;i<n+1;i++){
            char w1 = word1.charAt(i-1);
            for(int j=1;j<m+1;j++){
                char w2 = word2.charAt(j-1);
                if(w1==w2) dp[i][j]=dp[i-1][j-1];
                else
                    dp[i][j]=Math.min(Math.min(dp[i-1][j],dp[i][j-1]),dp[i-1][j-1])+1;
                //System.out.println(" i is "+i+" j is "+j+" dp=> "+dp[i][j]);
            }
        }
        return dp[n][m];
    }
}
