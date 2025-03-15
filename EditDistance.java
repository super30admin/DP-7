//TC: O(m*n) where m=word1 length, n=word2 length
//SC: O(m*n)
//approach: dynamic programming

public class EditDistance {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        for(int i=0;i<=word1.length();i++){
            dp[i][0]= i;
        }
        for(int i=1;i<=word2.length();i++){
            dp[0][i]=i;
        }

        for(int i=1; i<=word1.length();i++){
            for(int j=1;j<=word2.length();j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.min(dp[i][j-1], Math.min(dp[i-1][j-1], dp[i-1][j]))+1;
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }



    //TC: O(m*n)
    //SC: O(n)
    //approach: keeping only previous row values in dp, not full 2d dp array matrix
    public int minDistanceOptimized(String word1, String word2) {
        int[] dp = new int[word2.length()+1];
        for(int i=0;i<=word2.length();i++){
            dp[i]=i;
        }
        for(int i=1; i<=word1.length();i++){
            int[] newDp = new int[word2.length()+1];
            newDp[0] = i;
            for(int j=1;j<=word2.length();j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    newDp[j] = dp[j-1];
                }else{
                    newDp[j] = Math.min(newDp[j-1], Math.min(dp[j-1], dp[j]))+1;
                }
            }
            dp = newDp;
        }
        return dp[word2.length()];
    }

}
