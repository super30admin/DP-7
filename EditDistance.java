// Time Complexity : O(m*n)
// Space Complexity : O(m*n)
// m is the length of word1 , n is the length of word2
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


// Your code here along with comments explaining your approach

//Space - Optimised Solution
//TC: O(m*n)
//SC: O(n)
// n is the length of word2

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if(m == 0 && n == 0) return 0;

        int[] dp = new int[n+1];

        //fill the first row
        for(int j = 0; j < dp.length ; j++){
            dp[j] = j;
        }
        for(int i = 1 ; i <= m ; i++){
            int diagonal = dp[0];
            for(int j = 0 ; j <= n ;j++){
                if(j == 0){
                    dp[j] = i;
                }
                else{
                    int temp = dp[j];
                    if(word2.charAt(j-1) == word1.charAt(i-1)){
                        dp[j] = diagonal;
                    }
                    else{
                        dp[j] = 1+ Math.min(diagonal,Math.min(dp[j],dp[j-1]));
                    }
                    diagonal = temp;
                }
            }
        }
        for(int i = 0 ; i < n ; i++){
            System.out.println("value of i : " + i  + " is " + dp[i]);
        }
        return dp[n];
    }
}

//TC: O(m*n)
//SC: O(m*n)
// m is the length of word1 , n is the length of word2
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();

        if(m == 0 && n == 0) return 0;

        int[][] dp = new int[n+1][m+1];

        dp[0][0] = 0;
        //fill the first row
        for(int j = 0; j <= m ; j++){
            dp[0][j] = j;
        }

        //fill the first columns
        for(int i = 0 ; i <=  n ; i++){
            dp[i][0] = i;
        }

        for(int i = 1 ; i <= n ; i++){
            for(int j = 1 ; j <= m ; j++){
                if(word2.charAt(i-1) == word1.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1+ Math.min(Math.min(dp[i-1][j-1],dp[i][j-1]),dp[i-1][j]);
                }
            }
        }
        for(int i = 0; i<= n ; i++){
            for(int j = 0 ; j <= m ; j++){
                System.out.println("value of i : " + i + "value of j : " + j + " -->" + dp[i][j]);
            }
        }
        return dp[n][m];
    }
}