// Time Complexity : O(mn) m and n are length of words
// Space Complexity : O(mn)
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None

class EditDistance {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m = word2.length();
        int n = word1.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=0;i<=n;i++){
            dp[0][i]=i;
        }
        for(int i=0;i<=m;i++){
            dp[i][0]=i;
        }
        //System.out.println(Arrays.deepToString(dp));
        for(int i=1;i<=m;i++){
            for(int j=1;j<=n;j++){
                if(word1.charAt(j-1)==word2.charAt(i-1)){
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j]=1+Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1]));
                }
            }
        }

        return dp[m][n];
    }
}