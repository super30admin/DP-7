//Approach 1:
// TC: O(m*n)
// SC: O(m*n) 
//Approach: Use result of smaller sub problem to solve bigger problem.
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();

        if(m < n){
            minDistance(word2, word1);
        }   
        
        int[][] dp = new int[m+1][n+1];
        //top row
        for(int i = 0; i <= n ; i++){
            dp[0][i] = i;
        }

        for(int i = 1 ; i <= m ; i++){
            dp[i][0] = i; //first column
            for(int j = 1; j <= n ; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{ //min of insert, delete and replace subtrees
                    dp[i][j] = 1 + Math.min(dp[i][j-1],Math.min(dp[i-1][j-1],dp[i-1][j]));
                }
                
            }
        }

        return dp[m][n];

    }
}

//approach 2 :

// TC: O(m*n)
// SC: O(n) 

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)) return 0;
        int m = word1.length();
        int n = word2.length();

        if(m < n){
            minDistance(word2, word1);
        }   
        
        int[] dp = new int[n+1];
        //top row
        for(int i = 0; i <= n ; i++){
            dp[i] = i;
        }

        for(int i = 1 ; i <= m ; i++){
            int diagUp = dp[0];
            dp[0] = i; //first column
            for(int j = 1; j <= n ; j++){
                int temp = dp[j];
                if(word1.charAt(i-1) == word2.charAt(j-1)){
                    dp[j] = diagUp;
                }
                else{ //min of insert, delete and replace subtrees
                    dp[j] = 1 + Math.min(dp[j-1],Math.min(diagUp,dp[j]));
                }
                diagUp = temp;
            }
        }

        return dp[n];

    }
}