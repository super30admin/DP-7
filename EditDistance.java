// Time Complexity : O(mn) where m is the number of rows and n is the number of columns in the matrix
// Space Complexity : O(mn) dp matrix
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : None
/* Your code here along with comments explaining your approach: We have three options in each step. We would maintin a dp matrix to avoid repeated subproblems
of doing the same operations. If we get introduced to a new character for any of the strings, check if the additional characters for both the strings
are same, then you can use the previous diaognoal result as it creates a same scenario if the strings are without that character else take the min
of up, left and diagonal and add 1 to it (addition of the new char). Min because we want to perform min number of edits.
*/
class Solution {
    public int minDistance(String word1, String word2) {
        if(word1 == null && word2 == null) return 0;
        else if(word1 == null) return word2.length();                                                           // Edge cases
        else if(word2 == null) return word1.length();
        int[][] dp = new int[word1.length()+1][word2.length()+1];                                                   // dp matrix
        for(int i = 0; i < dp.length; i++){
            dp[i][0]= i;                                                                                // If empty string, rows will have add operations
        }
        for(int i = 0; i < dp[0].length; i++){
            dp[0][i]= i;                                                                                    // If empty string, column will have add operations
        }
        
        for(int i =1; i< dp.length; i++){
            for(int j = 1; j < dp[0].length; j++){
                if(word1.charAt(i-1) == word2.charAt(j-1)){                                                             // If same character occurs
                    dp[i][j] = dp[i-1][j-1];                                                            // Use the diagonal result as it doesnt make a diff
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], Math.min(dp[i-1][j-1], dp[i][j-1])) + 1;                                // Use the min of the three opeartions (del, add, update) and add 1 for the additional character
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];                                                                 // Return the optimal result
    }
}