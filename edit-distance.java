//TC: O(m*n), where m and n are the lengths of the given strings
//SC: O(m*n)
//running on leetcode: yes

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int n=word1.length();
        int m= word2.length();
        int[][] dp = new int[m+1][n+1];
        //fill in first row
        for(int j=0; j<n+1; j++){
            dp[0][j]=j;
        }
        //fill values in first column
        for(int i=0; i<m+1; i++){
            dp[i][0]=i;
        }
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(word1.charAt(j-1) == word2.charAt(i-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j]=Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1]))+1;
                }
            }
        }
        return dp[m][n];
    }
}
