//Time complexity:- 0(nm).
//space complexity:-0(nm).
//Approach is given in comments.


class Solution {
    public int minDistance(String word1, String word2) {
        int m=word1.length();
        int n=word2.length();
        int[][] dp=new int[n+1][m+1];
        for(int i=0;i<n+1;i++){
            dp[i][0]=i;
        }
        for(int j=0;j<m+1;j++){
            dp[0][j]=j;
        }
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(word1.charAt(j-1)==word2.charAt(i-1)){//if character is same, just making previous characters to required will only be minimum for that checking diagnol element.
                    dp[i][j]=dp[i-1][j-1];
                }
                else{
                    dp[i][j]=1+ Math.min(dp[i-1][j-1],Math.min(dp[i-1][j],dp[i][j-1]));
                    //we need to take minimum of three choices in which updation will be diagnol,removal will be left,addition will be from up.
                }
            }
        }
        return dp[n][m];
    }
}
