"""
// Time Complexity : O(len(s1)*len(s2))
// Space Complexity : O(len(s1)*len(s2))
// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

// Your code here along with comments explaining your approach
Algorithm Explanation
Given below - DP
"""
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        """
        We have three operations, update, delete, add, we essentially need to minimize the number of ways the s1(substring of word1) can be transformed to s2(substring of word2)
        We can see there are overlapping subproblems with respect to resultant string formed by transformed string at different stages
        
        Tabulation method
        - Consider word1 as row  and word2 as column and follow the following rules
        - Create a dp array dp[word1.length][word2.length+1] 
        - Fill the zeroth column and zeroth row with values from i to n-1 (corresponding to blank string)
        - For row in 1 to dp.length
            For col in 1 to dp[0].length
                if word1[row-1] == words[col-1]: # character is matched, so we need to pull down the last transformation value (top left diagonal)
                else:
                    dp[row][col] = min(dp[row-1][col],dp[row][col-1],dp[row-1][col-1]+1)
        - return dp[dp.length][dp[0].length]
        """
        dp = [[0 for _ in range(len(word2)+1)] for _ in range(len(word1)+1)]
        for i in range(len(word1)+1):
            dp[i][0] = i
        
        for i in range(len(word2)+1):
            dp[0][i] = i
        
        for i in range(1,len(dp)):
            for j in range(1,len(dp[0])):
                if word1[i-1] == word2[j-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i-1][j-1],dp[i][j-1],dp[i-1][j])+1
        return dp[len(dp)-1][len(dp[0])-1]