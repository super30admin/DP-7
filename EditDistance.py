"""
Time Complexity : O(n) + O(max element) 
Space Complexity : O(max element)
Did this code successfully run on Leetcode : Yes
Any problem you faced while coding this : No
"""
class Solution:
    def minDistance(self, word1: str, word2: str) -> int:
        if word1 == word2: return 0
        n = len(word1)
        m = len(word2)
        if n > m:
            return self.minDistance(word2, word1)
        dp = [0]*(n+1)
        print(dp)
        # Fill the first row values
        for j in range(n + 1):
            dp[j] = j
            
        # Fill the first col values
        """for i in range(m + 1):
            dp[i][0] = i"""
        # Approach 1
        # Fill the remaining matrix. We calculate the each cell values by the 
        # following rules. If the both the characters match in both the words we just
        # copy over the previous diagonal value. If they not match we take minimum
        # of Update(diagonal value), Add(Just above current) and Delete(on the leftof
        # current) and add one to it. At the end we return the last element of last 
        # row which would be our minimum operations.
        
        """
        Approach 2
        We now fill the list with smallest word size. We then do similar things that 
        is store update, add and delete operations in temp variables to save the             space from approach 1. 
        """
        for i in range(1, m+1):
            temp = dp[0]
            dp[0] = i
            for j in range(1, n+1):
                temp2 = dp[j]
                if word1[j - 1] == word2[i - 1]:
                    dp[j] = temp
                else:
                    dp[j] = min(temp, dp[j], dp[j - 1]) + 1
                temp = temp2
        return dp[-1]
            