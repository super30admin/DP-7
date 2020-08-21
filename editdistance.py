# Using dynamic programming
# Time complexity - O(nm)
# Space complexity - O(nm)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n, m = len(word1), len(word2)
        dp = [[0 for _ in range(m+1)] for _ in range(n+1)]

        for i in range(n+1):
            dp[i][0] = i
        
        for i in range(m+1):
            dp[0][i] = i
            
        for i in range(1, n+1):
            for j in range(1, m+1):
                if word1[i-1] == word2[j-1]: # if the character in word 1 is the same as the character in word 2. The value will be the same as previous character as the current character is not required to be updated.
                    dp[i][j] = dp[i-1][j-1]
                else: # check the minimum value for insert, update or delete and add one more operation for the current character.
                    dp[i][j] = 1 + min(dp[i-1][j-1], min(dp[i-1][j], dp[i][j-1]))
        
        return dp[n][m]


# Brute force method
# time complexity - O(3^nm)
# space complexity - O(max(n, m)) -- stack space 
# did this solution run on leetcode? - no (TLE)
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        n, m = len(word1), len(word2)
        self.min_ops = float('inf')
        
        def rec(idx1, idx2, ops):
            if idx1 == n and idx2 == m:
                self.min_ops = min(self.min_ops, ops)
            elif idx1 < n and idx2 < m:
                if word1[idx1] == word2[idx2]:
                    rec(idx1+1, idx2+1, ops)
                else:
                    rec(idx1, idx2+1, ops+1)    # insert
                    rec(idx1+1, idx2, ops+1)    # delete
                    rec(idx1+1, idx2+1, ops+1)  # update
            elif idx1 < n:
                rec(idx1+1, idx2, ops+1)    # delete
            elif idx2 < m:
                rec(idx1, idx2+1, ops+1)    # insert
                
        rec(0, 0, 0)
        return self.min_ops