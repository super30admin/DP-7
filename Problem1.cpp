
/* Problem Statement: 
Verified on LeetCode
72. Edit Distance
Hard

Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

    Insert a character
    Delete a character
    Replace a character

Example 1:

Input: word1 = "horse", word2 = "ros"
Output: 3
Explanation: 
horse -> rorse (replace 'h' with 'r')
rorse -> rose (remove 'r')
rose -> ros (remove 'e')

Example 2:

Input: word1 = "intention", word2 = "execution"
Output: 5
Explanation: 
intention -> inention (remove 't')
inention -> enention (replace 'i' with 'e')
enention -> exention (replace 'n' with 'x')
exention -> exection (replace 'n' with 'c')
exection -> execution (insert 'u')




 * Best Solution : DYNAMIC PROGRAMMING, 
 * Time Complexity : O(n1*n2) where n1 and n2 is size of strings
 * Space Complexity : O(n1*n2) for dp array 
 */

class Solution {
public:
    int minDistance(string word1, string word2) {
        int wlen1 = word1.length();
        int wlen2 = word2.length();
        int i,j;
        
        int dp[wlen1 + 1][wlen2 + 1];
        
        /* DP solution :: 
        * if characters dont match then 
        * if we insert then we take the value dp[i][j-1] considering that the element needs to be added hence just copy it from the previous column and same row and add 1 to it
        * for delete : dp[i-1][j] + 1
        * for replace : dp[i-1][j-1] as no new operation is needed. 
        *    '' m a r c h
        * ''  0 1 2 3 4 5
        *  c  1 1 2 3 3 4
        *  a  2 2 1 2 3 4
        *  r  3 3 2 1 2 3
        *  t  4 4 3 2 2 3 
        */
        for ( i = 0; i <= wlen1 ; i++) {
            for ( j = 0; j <= wlen2; j++) {
                
                /* base case fill */
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    /* important to note */
                    if (word1[i-1] == word2[j-1]) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] = min(dp[i][j-1], min(dp[i-1][j], dp[i-1][j-1])) + 1;
                    }
                }
            }
        }

        return dp[wlen1][wlen2];
        
    }
};
