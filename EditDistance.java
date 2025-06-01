/*
 * Time Complexity: O(m*n)
 * We traverse the m*n matrix.
 * Space Complexity: O(m*n)
 * We store dp solutions in a m*n matrix.
 */

class EditDistance {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];

        //Set the first row to default values of j. We need to keep adding the characters
        for(int j=1; j<=n; j++){
            dp[0][j] = j;
        }

        for(int i=1; i<=m; i++){
            for(int j=0; j<=n; j++){
                if(j == 0){
                    //Now, we make the first column to default values
                    dp[i][j] = i;
                }

                else{
                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[i][j] = dp[i-1][j-1];
                    }
                    else{
                        //Min of the previous sub problems + 1 for one operation.
                        dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    }
                }
            }
        }
        return dp[m][n];
    }
}


/*Instead of a 2d matrix to store solutions, we can use a 1d Array itself. */
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[] dp = new int[n+1];

        //Set the first row to default values of j. We need to keep adding the characters
        for(int j=1; j<=n; j++){
            dp[j] = j;
        }

        for(int i=1; i<=m; i++){
            int diagUp = dp[0];
            for(int j=0; j<=n; j++){
                int temp = dp[j];
                if(j == 0){
                    //Now, we make the first column to default values
                    dp[j] = i;
                }

                else{
                    if(word1.charAt(i-1) == word2.charAt(j-1)){
                        dp[j] = diagUp;
                    }
                    else{
                        //Min of the previous sub problems + 1 for one operation.
                        dp[j] = Math.min(dp[j-1], Math.min(dp[j], diagUp)) + 1;
                    }
                }
                diagUp = temp;
            }
        }
        return dp[n];
    }
}