package DP-7;
// TC: O(n^2)
// SC: O(n)
public class Problem1 {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[] dp = new int[n+1];
        dp[0] = 0;
        for(int j=0;j<=n;j++){
            dp[j]=j;
        }
        int diagUp=0;
        for(int i=1;i<=m;i++){
            for(int j=0;j<=n;j++){
                int temp=dp[j];
                if(j==0){
                    dp[j]=i;
                }
                else{
                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[j] = diagUp;
                    }
                    else{
                        dp[j] = 1+Math.min(dp[j-1], Math.min(dp[j],diagUp));
                    }
                }
                diagUp=temp;
            }
        }
        return dp[n];
    }
}
