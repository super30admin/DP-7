## Problem1 Edit Distance (https://leetcode.com/problems/edit-distance/)
In this we have to find out the minimum number of operations required to convert one string to another
so if we go exhaustive then there are repeated subproblems so we will go for DP based solution in which we will create a 2-D matrix in which
we will store the minimum no of operations required to form that string. That is how we will get the minimum operations to cnvert.
We are allowed to do Insert, Update, Delete so if we want to convert h to r if we replace it then "" == "" and the answer is in the diagonal
if we add r then after removing both r then we are left with ""=h and the answer is to the left cell and same for the delete the answer lies in the above cell

Intuition:
if the character matches then directly the diagonal element is the answer
if it does not matches then we will take minimum of all 3 left,above and diagonal and add 1 to it

Time Complexity : O(mn)
Space Complexity : O(mn)

class Solution {
    public int minDistance(String word1, String word2) {
        if(word1.equals(word2)){
            return 0;
        }
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i=1;i<=n;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<=m;i++){
            dp[i][0] = i;
        }

        for(int i=1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],(Math.min(dp[i][j-1],dp[i-1][j])));
                } 
            }
        }
        return dp[m][n];
    }
}