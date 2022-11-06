/*
Time Complexity:O(mn)
Space Complexity:O(mn)
*/
class Solution {
public int minDistance(String word1, String word2) {

    int m = word1.length();
    int n = word2.length();
    
    int[][] dp = new int[m+1][n+1];
    
    for(int i = 0; i<=m; i++){
        for(int j = 0; j<=n; j++){
            
            if(i==0){
                dp[i][j] = j;
            }else if(j==0){
                dp[i][j] = i;
            }else{
                
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else{
                    int v1 = dp[i-1][j-1];
                    int v2 = dp[i-1][j];
                    int v3 = dp[i][j-1];
                    
                    dp[i][j] = 1 + Math.min(v1, Math.min(v2,v3));
                }
            }
        }
    }
    
    return dp[m][n];

}
}