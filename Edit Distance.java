//Time Complexity : O(m*n)
//Space Complexity : O(m*n)
// Did this code successfully run on Leetcode :yes
// Your code here along with comments explaining your approach
class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        //use a dp array to store the result of repeated subproblems
        int[][] dp = new int[n+1][m+1];
        //the first cell i.e, 0th row and 0th col value(which is a '_' will always match hence 0.
        dp[0][0] = 0;
        //update the first row : base case
        for(int i=1;i<m+1;i++){
            //the number of operations(inorder to match with '_') to be done for each char in word2 is updated
            dp[0][i] = i;
        }
        //update the first col : base case
        for(int i=1;i<n+1;i++){
            //the number of operations(inorder to match with '_') to be done for each char in word1 is updated
            dp[i][0] = i;
        }
        //fill the matrix
        for(int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                //if no operations is to be done or if the characters match, then get the value for the current char from the diagonal from the current position
                if(word1.charAt(j-1)==word2.charAt(i-1)){
                    dp[i][j] = dp[i-1][j-1];
                }
                //otherwise, take the minimum among the three operations
                else{
                    dp[i][j] = 1 + Math.min(dp[i-1][j-1],Math.min(dp[i][j-1],dp[i-1][j]));
                }
            }
        }
        return dp[n][m];
        /* at the end of computation, the dp array will be as below
        _| |0|1|2|3|4|5
           |-|h|o|r|s|e
        0|-|0|1|2|3|4|5
        1|r|1|1|2|2|3|4
        2|0|2|2|1|2|3|4
        3|s|3|3|2|2|2|3
        
        */
    }
}