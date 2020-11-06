# Time Complexity : O(nm)
# Space Complexity : O(nm)
# Did this code successfully run on Leetcode :  Yes
# Any problem you faced while coding this : No

# Approach:
# Brute Force: At every letter, there are three options: update(U), add(A) and remove(R) to make word1 into word2. We can use BFS, return the level when we get the word2 as we are required to return the min. #of steps.
# Optimal with DP: Make a dp matrix with ixj matrix where j(word1) = _ h o r s e and i(word2) = _ r o s. The values in matrix represent number of steps that need to be taken to form x to y.
# To: _h -> _r, take minimum of below three choices:
# R : _ -> _r -> 1 (from the cell on the immediate left of current cell) + 1(for removing h) = 2
# U : _ -> _ -> 0 (from the diagonal) +1 = 1
# A : (_h -> _) + r -> 1(from the cell right above) + 1(to add r) = 2 
# Case when _hor -> _r, last char is equal to curr char, we need to only check if _ho -> _, hence don't do anything and grab the answer from the diagonal

class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        
        n = len(word2)+1
        m = len(word1)+1
        
        dp = [[0 for _ in range(m)] for _ in range(n)]
        
        for i in range(m):
            dp[0][i] = i
        
        for i in range(n):
            dp[i][0] = i 

        for i in range(1, n):
            for j in range(1, m):
                if word1[j-1] == word2[i-1]:
                    dp[i][j] = dp[i-1][j-1]
                else:
                    dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1])+1
                    
        return dp[-1][-1]
        